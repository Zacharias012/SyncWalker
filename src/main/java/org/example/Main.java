package org.example;

import org.example.treeComponents.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Properties;

public class Main {

    public static String folderSource;
    public static String folderTarget;

    public static void main(String[] args) throws Exception {

        try {
            loadProperties();
        } catch (Exception e){
            System.out.println("No config.properties file found!");
            return;
        }

        TreeBuilder treeBuilderSource = new TreeBuilder(Paths.get(folderSource));
        //treeBuilderSource.printTree();
        TreeBuilder treeBuilderTarget = new TreeBuilder(Paths.get(folderTarget));
        //treeBuilderTarget.printTree();
    }

    public static void loadProperties() throws IOException {
        Properties props = new Properties();
        props.load(new FileInputStream("config.properties"));
        folderSource = props.getProperty("folder_source");
        folderTarget = props.getProperty("folder_target");
    }
}