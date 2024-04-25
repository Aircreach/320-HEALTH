package com.air.health.common.util;

import com.baomidou.mybatisplus.core.conditions.interfaces.Func;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class TreeUtil {

    public static <T, K> List<T> buildTree(List<T> items, Function<T, K> idGetter, Function<T, K> parentIdGetter) throws NoSuchFieldException, IllegalAccessException {
        Map<K, List<T>> map = new HashMap<>();
        for (T item : items) {
            K parentId = parentIdGetter.apply(item);
            map.computeIfAbsent(parentId, k -> new ArrayList<>()).add(item);
        }

        List<T> roots = map.get(null);
        if (roots != null) {
            for (T root : roots) {
                addChildNodes(root, map, idGetter);
            }
        }
        return roots != null ? roots : new ArrayList<>();
    }

    private static <T, K> void addChildNodes(T parent, Map<K, List<T>> map, Function<T, K> idGetter) throws NoSuchFieldException, IllegalAccessException {
        K parentId = idGetter.apply(parent);
        List<T> children = map.get(parentId);
        if (children != null) {
            for (T child : children) {
                addChildNodes(child, map, idGetter);
            }
            // Assuming each object has a children list
            ((List<T>) parent.getClass().getDeclaredField("children").get(parent)).addAll(children);
        }
    }
}
