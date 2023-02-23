package org.productenginetest;

import java.util.concurrent.ConcurrentHashMap;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@NoArgsConstructor
public class TreeTrackmanThread extends Thread {
    public String rootPath;
    public int searchDepth;
    public ConcurrentHashMap<String, String> fileTree;

    public TreeTrackmanThread(String rootPath, int searchDepth) {
        this.rootPath = rootPath;
        this.searchDepth = searchDepth;
    }

    @Override
    public void run() {
        TreeTrackman trackMan = new TreeTrackmanImpl();
        long trackTimeStamp = System.currentTimeMillis();
        ConcurrentHashMap<String, String> fileTree = trackMan.treeTraversal(rootPath, searchDepth);
        log.info("Thread name {}, thread priority {}, time for searching {}, search params: "
                        + "rootPath {}, search depth {}",
                this.getName(), this.getPriority(), System.currentTimeMillis() - trackTimeStamp, rootPath,
                searchDepth);
    }
}
