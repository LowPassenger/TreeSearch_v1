package org.productenginetest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListSet;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class TreeTrackManImpl implements TreeTrackMan {
    private TreeSet<File> elements = new TreeSet<>();

    @Override
    public ArrayList<ConcurrentSkipListSet<String>> treeTraversal(String rootFolder, int depth) {
        ArrayList<ConcurrentSkipListSet<String>> fileTree = new FileTree().getFileTree();
        TreeSet<File> commonElements = new TreeSet<>();
        log.info("Start File Tree Track process. Thread info: name {}",
                Thread.currentThread().getName());
        elements.add(new File(rootFolder));
        for (int i = 0; i < depth + 1; i++) {
            commonElements.clear();
            for (File element : elements) {
                if (element.isFile()) {
                    commonElements.add(element);
                }
                if (element.isDirectory()) {
                    commonElements.add(element);
                    File[] contents = element.listFiles();
                    if (contents != null) {
                        commonElements.addAll(Arrays.asList(contents));
                    }
                }
            }
            commonElements.removeAll(elements);
            elements = new TreeSet<>(commonElements);
            ConcurrentSkipListSet<String> levelElements = new ConcurrentSkipListSet<>();
            for (File common : elements) {
                levelElements.add(common.getAbsolutePath());
            }
            log.info("Depth {} complete, total records {} was added", i, levelElements.size());
            fileTree.add(levelElements);
        }
        log.info("File Tree Tracking process is completed.");
        return fileTree;
    }
}
