package com.example.lesson6.util;


import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Collections.emptyList;
import static java.util.Collections.emptySet;

/**
 * @author Mikhail Boyandin
 *
 * Created on 07.02.2023
 */
public class CollectionUtil {

    public static <V> List<V> replaceContents(List<V> collection, List<V> replacement) {
        if (collection == null) {
            return Objects.requireNonNullElse(replacement, emptyList());
        }

        if (CollectionUtils.isEmpty(replacement)) {
            if (!collection.isEmpty()) {
                collection.clear();
            }

            return collection;
        }

        collection.retainAll(replacement);

        List<V> newElements = new ArrayList<>(replacement);
        newElements.removeAll(collection);

        collection.addAll(newElements);

        return collection;
    }

    public static <V> Set<V> replaceContents(Set<V> collection, Set<V> replacement) {
        if (collection == null) {
            return Objects.requireNonNullElse(replacement, emptySet());
        }

        if (CollectionUtils.isEmpty(replacement)) {
            if (!collection.isEmpty()) {
                collection.clear();
            }

            return collection;
        }

        collection.retainAll(replacement);

        List<V> newElements = new ArrayList<>(replacement);
        newElements.removeAll(collection);

        collection.addAll(newElements);

        return collection;
    }
}

