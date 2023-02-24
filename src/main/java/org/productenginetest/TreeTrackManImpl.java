package org.productenginetest;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentSkipListSet;

public class TreeTrackManImpl implements TreeTrackMan {
    private final LinkedList<File> elements = new LinkedList<>();
    private final LinkedList<File> levelElements = new LinkedList<>();

    @Override
    public ConcurrentSkipListSet<String> treeTraversal(String rootFolder, int depth) {
        ConcurrentSkipListSet<String> fileTree = new FileTree().getFileTree();
        levelElements.add(new File(rootFolder));
        LinkedList<File> commonElements = new LinkedList<>();
        for (int i = -1; i < depth; i++) {
            commonElements.clear();
            for (File element : levelElements) {
                if (element.isFile()) {
                    elements.add(element);
                }
                if (element.isDirectory()) {
                    elements.add(element);
                    File[] contents = element.listFiles();
                    if (contents != null) {
                        commonElements.addAll(Arrays.asList(contents));
                    }
                }
            }
            elements.addAll(commonElements);
            levelElements.clear();
            levelElements.addAll(commonElements);
        }
        for (File element : elements) {
            fileTree.add(element.getAbsolutePath());
        }
        return fileTree;
    }
}
