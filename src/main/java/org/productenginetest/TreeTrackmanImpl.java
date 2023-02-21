package org.productenginetest;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class TreeTrackmanImpl implements TreeTrackman {
    private static final String PLUG = "/<>:/|Yu$MMy-Du#MMy";
    private File content;
    private File[] contents;
    private List<File> allContents = new ArrayList<>();

    @Override
    public ConcurrentLinkedDeque<String> treeTrack(String rootFolder, int depth) {
        ConcurrentLinkedDeque<String> fileTree = new ConcurrentLinkedDeque<>();
        fileTree.push(PLUG);
        fileTree.push(rootFolder);
            while (depth < 0 || !fileTree.peek().equals(PLUG)) {
                while (!fileTree.peek().equals(PLUG)) {
                    content = new File(fileTree.poll());
                    contents = (content).listFiles();
//                    Collections.addAll(allContents, contents);
                    for (File content : contents) {
                        String contentName = content.getName();
                        if (content.isFile()) {
                            fileTree.offer(contentName);
                        }
                        if (content.isDirectory()) {
                            fileTree.push(String.valueOf(content));
                            fileTree.offer(contentName);
                        }
                    }
                }

                depth--;
//                allContents.clear();
            }
        return fileTree;
    }
}
