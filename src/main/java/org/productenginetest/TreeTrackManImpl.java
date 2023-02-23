package org.productenginetest;

import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class TreeTrackManImpl implements TreeTrackMan {
    private static final String PLUG = "empty";
    private final ConcurrentHashMap<String, String> fileTree = new ConcurrentHashMap<>(16, 0.75F, 1);
    private final LinkedList<File> elements = new LinkedList<>();
    private final LinkedList<File> levelElements = new LinkedList<>();

    @Override
    public ConcurrentHashMap<String, String> treeTraversal(String rootFolder, int depth) {
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
            if (element.isDirectory()) {
                fileTree.put(element.getAbsolutePath(), PLUG);
            }
            if (element.isFile()) {
                fileTree.put(element.getParent(), element.getAbsolutePath());
            }
        }
        return fileTree;
    }
}
