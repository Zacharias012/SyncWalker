package org.example;

import org.example.treeComponents.*;
import java.util.*;

public class Calculator {

    public void calculateDiff(TreeBuilder source, TreeBuilder target) {
        compareNodes(source.getRoot(), target.getRoot(), "");
    }
    private void compareNodes(Node source, Node target, String path) {

        if (source == null && target == null) return;

        if (source == null) {
            System.out.println("ADDED: " + path + "/" + target.getName());
            return;
        }

        if (target == null) {
            System.out.println("REMOVED: " + path + "/" + source.getName());
            return;
        }

        if (source instanceof FileNode && target instanceof FileNode) {
            return;
        }

        if (source instanceof FolderNode sFolder && target instanceof FolderNode tFolder) {

            Map<String, Node> sourceMap = mapChildren(sFolder);
            Map<String, Node> targetMap = mapChildren(tFolder);

            Set<String> allNames = new HashSet<>();
            allNames.addAll(sourceMap.keySet());
            allNames.addAll(targetMap.keySet());

            for (String name : allNames) {
                compareNodes(
                        sourceMap.get(name),
                        targetMap.get(name),
                        path + "/" + name
                );
            }
            return;
        }

        System.out.println("CHANGED TYPE: " + path + "/" + source.getName());
    }

    private Map<String, Node> mapChildren(FolderNode folder) {
        Map<String, Node> map = new HashMap<>();
        for (Node node : folder.getChildren()) {
            map.put(node.getName(), node);
        }
        return map;
    }
}
