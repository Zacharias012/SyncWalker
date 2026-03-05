package org.example.treeComponents;

import java.io.IOException;
import java.nio.file.*;

public class TreeBuilder {

    private FolderNode iniFolderNode;

    public TreeBuilder(Path path) throws IOException {
        this.iniFolderNode = buildTree(path);
    }

    private FolderNode buildTree(Path path) throws IOException {
        FolderNode folderNode = new FolderNode(path.getFileName().toString());

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
            for (Path entry : stream) {
                if (Files.isDirectory(entry)) {
                    folderNode.add(buildTree(entry));
                } else {
                    folderNode.add(new FileNode(entry.getFileName().toString()));
                }
            }
        }

        return folderNode;
    }

    public void printTree() {
        printTree(iniFolderNode, "", true);
    }

    private void printTree(Node node, String prefix, boolean isLast) {
        if (node instanceof FolderNode folder) {
            var children = folder.getChildren();

            for (int i = 0; i < children.size(); i++) {
                Node child = children.get(i);
                boolean last = (i == children.size() - 1);

                System.out.println(prefix
                        + (last ? "└── " : "├── ")
                        + child.getName());

                if (child instanceof FolderNode) {
                    printTree(child,
                            prefix + (last ? "    " : "│   "),
                            last);
                }
            }
        }
    }

    public FolderNode getRoot() {
        return iniFolderNode;
    }
}