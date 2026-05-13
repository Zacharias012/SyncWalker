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

        TreeBuilder treeBuilder = new TreeBuilder(Paths.get(folderSource));
        System.out.println("");
        System.out.println("Printing source");
        System.out.println("");
        treeBuilder.printTree();

        TreeBuilder treeBuilderTarget = new TreeBuilder(Paths.get(folderTarget));
        System.out.println("");
        System.out.println("Printing target");
        System.out.println("");
        treeBuilderTarget.printTree();

        System.out.println("");
        System.out.println("Starting Synchronization...");
        System.out.println("Source: " + folderSource);
        System.out.println("Target: " + folderTarget);
        System.out.println("");

        try {
            treeBuilder.syncToTarget(Paths.get(folderSource), Paths.get(folderTarget));
            System.out.println("");
            System.out.println("Synchronization completed successfully.");
        } catch (Exception e) {
            System.err.println("Error during synchronization: " + e.getMessage());
            e.printStackTrace();
        }
    }
}