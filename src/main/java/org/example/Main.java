package org.example;

import org.example.treeComponents.*;

import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws Exception {

        Properties props = new Properties();
        props.load(new FileInputStream("config.properties"));
        String folderSource = props.getProperty("folder_source");
        String folderTarget = props.getProperty("folder_target");

        TreeBuilder treeBuilderSource = new TreeBuilder(Paths.get(folderSource));
        //treeBuilderSource.printTree();

        TreeBuilder treeBuilderTarget = new TreeBuilder(Paths.get(folderTarget));
        //treeBuilderTarget.printTree();


    }
}