package org.example.treeComponents;

import java.util.List;
import java.util.ArrayList;

public class FolderNode extends Node {
    private List<Node> children = new ArrayList<>();

    public FolderNode(String name) {
        super(name);
    }

    public void add(Node node) {
        children.add(node);
    }

    public List<Node> getChildren() {
        return children;
    }
}
