package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.cloner.CollectionCloner;
import com.checkaboy.deepcopy.cloner.ObjectCloner;
import com.checkaboy.deepcopy.cloner.interf.ICollectionCloner;
import com.checkaboy.deepcopy.copyist.CollectionCopyist;
import com.checkaboy.deepcopy.copyist.FieldCopyist;
import com.checkaboy.deepcopy.copyist.ObjectCopyist;
import com.checkaboy.deepcopy.copyist.interf.ICollectionCopyist;
import com.checkaboy.deepcopy.model.pet.EAnimal;
import com.checkaboy.deepcopy.model.pet.Pet;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Taras Shaptala
 */
public class ListCopyTest {

    @Test
    public void primitiveListCopy() {
        List<String> sourceList = listGenerate();
        List<String> cloneList = new ArrayList<>();

        ICollectionCopyist<List<String>, String> collectionCopyist = CollectionCopyist.primitiveCollectionCopyist();

        System.out.println(sourceList);
        System.out.println(cloneList);
        System.out.println(sourceList == cloneList);

        Assert.assertNotEquals(sourceList, cloneList);

        collectionCopyist.copy(sourceList, cloneList);

        System.out.println(sourceList);
        System.out.println(cloneList);
        System.out.println(sourceList == cloneList);

        Assert.assertEquals(sourceList, cloneList);
    }

    @Test
    public void primitiveListClone() {

        List<String> sourceList = listGenerate();

        ICollectionCloner<List<String>, String> collectionCloner = CollectionCloner.primitiveCollectionCloner(ArrayList::new);

        List<String> copyList = collectionCloner.clone(sourceList);

        System.out.println(sourceList);
        System.out.println(copyList);
        System.out.println(sourceList == copyList);

        Assert.assertEquals(sourceList, copyList);
    }

    @Test
    public void objectListClone() {
        List<Pet> sourceList = createFirstPetList();

        CollectionCloner<List<Pet>, Pet> collectionCloner = new CollectionCloner<>(
                ArrayList::new,
                new CollectionCopyist<>(new ObjectCloner<>(
                        Pet::new,
                        new ObjectCopyist<>(Map.ofEntries(
                                Map.entry("nickname", FieldCopyist.simpleCopyist(Pet::getNickname, Pet::setNickname)),
                                Map.entry("age", FieldCopyist.simpleCopyist(Pet::getAge, Pet::setAge)),
                                Map.entry("animal", FieldCopyist.simpleCopyist(Pet::getAnimal, Pet::setAnimal))
                        ))
                ))
        );

        List<Pet> copyList = collectionCloner.clone(sourceList);

        System.out.println("sourceList: " + sourceList);
        System.out.println("copyList:   " + copyList);

        for (int i = 0; i < sourceList.size(); i++) {
            Assert.assertEquals(sourceList.get(i).getNickname(), copyList.get(i).getNickname());
            Assert.assertEquals(sourceList.get(i).getAge(), copyList.get(i).getAge());
            Assert.assertEquals(sourceList.get(i).getAnimal(), copyList.get(i).getAnimal());
        }
    }

    private List<String> listGenerate() {
        return List.of(
                "first",
                "second",
                "third",
                "fourth",
                "fifth"
        );
    }

    private List<Pet> createFirstPetList() {
        List<Pet> list = new ArrayList<>();

        {
            Pet catSimon = new Pet();
            catSimon.setNickname("Simon");
            catSimon.setAge(2);
            catSimon.setAnimal(EAnimal.CAT);
            list.add(catSimon);
        }

        {
            Pet dogRex = new Pet();
            dogRex.setNickname("Rex");
            dogRex.setAge(10);
            dogRex.setAnimal(EAnimal.DOG);
            list.add(dogRex);
        }

        {
            Pet catFluffy = new Pet();
            catFluffy.setNickname("Fluffy");
            catFluffy.setAge(7);
            catFluffy.setAnimal(EAnimal.CAT);
            list.add(catFluffy);
        }

        return list;
    }


}
