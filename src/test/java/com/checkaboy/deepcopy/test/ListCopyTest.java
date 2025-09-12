package com.checkaboy.deepcopy.test;

import com.checkaboy.deepcopy.filler.model.general.CollectionFiller;
import com.checkaboy.deepcopy.filler.model.general.FieldFiller;
import com.checkaboy.deepcopy.filler.model.general.ObjectFiller;
import com.checkaboy.deepcopy.filler.model.interf.ICollectionFiller;
import com.checkaboy.deepcopy.model.pet.EAnimal;
import com.checkaboy.deepcopy.model.pet.Pet;
import com.checkaboy.deepcopy.transformer.model.CollectionTransformer;
import com.checkaboy.deepcopy.transformer.model.ObjectTransformer;
import com.checkaboy.deepcopy.transformer.model.interf.ICollectionTransformer;
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

        ICollectionFiller<List<String>, String, List<String>, String> collectionCopyist = CollectionFiller.primitiveCollectionFiller();

        System.out.println(sourceList);
        System.out.println(cloneList);
        System.out.println(sourceList == cloneList);

        Assert.assertNotEquals(sourceList, cloneList);

        collectionCopyist.fill(null, sourceList, cloneList);

        System.out.println(sourceList);
        System.out.println(cloneList);
        System.out.println(sourceList == cloneList);

        Assert.assertEquals(sourceList, cloneList);
    }

    @Test
    public void primitiveListClone() {

        List<String> sourceList = listGenerate();

        ICollectionTransformer<List<String>, String, List<String>, String> collectionCloner = CollectionTransformer.primitiveCollectionTransformer(ArrayList::new);

        List<String> copyList = collectionCloner.transform(null, sourceList);

        System.out.println(sourceList);
        System.out.println(copyList);
        System.out.println(sourceList == copyList);

        Assert.assertEquals(sourceList, copyList);
    }

    @Test
    public void objectListClone() {
        List<Pet> sourceList = createFirstPetList();

        CollectionTransformer<List<Pet>, Pet, List<Pet>, Pet> collectionCloner = new CollectionTransformer<>(
                ArrayList::new,
                new CollectionFiller<>(new ObjectTransformer<>(
                        Pet::new,
                        new ObjectFiller<>(Map.ofEntries(
                                Map.entry("nickname", FieldFiller.simpleFieldFiller(Pet::getNickname, Pet::setNickname)),
                                Map.entry("age", FieldFiller.simpleFieldFiller(Pet::getAge, Pet::setAge)),
                                Map.entry("animal", FieldFiller.simpleFieldFiller(Pet::getAnimal, Pet::setAnimal))
                        ))
                ))
        );

        List<Pet> copyList = collectionCloner.transform(null, sourceList);

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
