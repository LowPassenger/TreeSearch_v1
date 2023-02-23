package org.productenginetest;

import java.io.File;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

public class TreeTrackmanImpl implements TreeTrackman {
    private static final String PLUG = "empty";
    private ConcurrentHashMap<String, String> fileTree = new ConcurrentHashMap(16, 0.75F, 1);
    private LinkedList<File> elements = new LinkedList<>();
    private LinkedList<File> levelElements = new LinkedList<>();
    private File[] contents;

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
                    contents = element.listFiles();
                    if (contents != null) {
                        for (File content : contents) {
                            commonElements.add(content);
                        }
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
