package org.example.treeComponents;

import java.io.IOException;
import java.nio.file.*;

public class TreeBuilder {

    private FolderNode iniFolderNode;
    private java.util.Set<String> allowedExtensions;

    public TreeBuilder(Path path) throws IOException {
        this.iniFolderNode = buildTree(path);
        this.allowedExtensions = new java.util.HashSet<>();
    }

    public void setAllowedExtensions(java.util.List<String> extensions) {
        this.allowedExtensions = new java.util.HashSet<>(extensions);
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

    /**
     * Synchronisiert den erkannten Baum in ein Zielverzeichnis.
     * Fehlende Ordner und Dateien werden erstellt/kopiert.
     * Bestehende Dateien werden nicht überschrieben oder gelöscht.
     *
     * @param sourceBase Der Pfad der Quelle
     * @param targetBase Der Pfad des Zielverzeichnisses
     * @throws IOException Wenn Fehler beim Kopieren auftreten
     */
    public void syncToTarget(Path sourceBase, Path targetBase) throws IOException {
        syncNode(iniFolderNode, sourceBase, targetBase);
    }

    private void syncNode(Node node, Path sourceBase, Path targetBase) throws IOException {
        // Berechne den relativen Pfad vom Quell-Wurzelverzeichnis
        if (node instanceof FolderNode folder) {
            for (Node child : folder.getChildren()) {
                syncRecursive(child, Paths.get(""), targetBase, sourceBase);
            }
        }
    }

    private void syncRecursive(Node node, Path relativePath, Path targetBase, Path sourceBase) throws IOException {
        Path currentRelativePath = relativePath.resolve(node.getName());
        Path targetPath = targetBase.resolve(currentRelativePath);
        Path sourcePath = sourceBase.resolve(currentRelativePath);

        if (node instanceof FolderNode folder) {
            // Erstelle Verzeichnis im Ziel, falls nicht vorhanden
            if (!Files.exists(targetPath)) {
                Files.createDirectories(targetPath);
                System.out.println("Ordner erstellt: " + targetPath);
            }

            // Rekursion für Kinder
            for (Node child : folder.getChildren()) {
                syncRecursive(child, currentRelativePath, targetBase, sourceBase);
            }
        } else if (node instanceof FileNode) {
            // Prüfe Dateiendung, falls Filter gesetzt sind
            if (!allowedExtensions.isEmpty()) {
                boolean isAllowed = allowedExtensions.stream()
                        .anyMatch(ext -> node.getName().toLowerCase().endsWith(ext.toLowerCase()));
                if (!isAllowed) {
                    return; // Datei überspringen
                }
            }

            // Kopiere Datei, falls im Ziel nicht vorhanden
            if (!Files.exists(targetPath)) {
                Files.copy(sourcePath, targetPath, StandardCopyOption.COPY_ATTRIBUTES);
                System.out.println("Datei kopiert: " + targetPath);
            }
        }
    }
}