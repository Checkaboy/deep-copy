package com.checkaboy.deepcopy.context.key;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author Taras Shaptala
 */
public class ClassKey {

    private final Class<?>[] classes;

    public ClassKey(Class<?>... classes) {
        this.classes = classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassKey classKey = (ClassKey) o;
        return Objects.deepEquals(classes, classKey.classes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(classes);
    }

}
