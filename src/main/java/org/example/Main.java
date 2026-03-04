package org.example;

import org.example.components.*;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws Exception {

        TreeBuilder treeBuilder = new TreeBuilder(Paths.get("C:/TestEnv"));
        treeBuilder.printTree();
    }
}