package org.productenginetest;

import java.util.concurrent.ConcurrentHashMap;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class SearchEngineThread extends Thread {
    public String searchMask;
    @Override
    public void run() {
        SearchEngine searchEngine = new SearchEngineImpl();
        TreeTrackmanThread trackmanThread = new TreeTrackmanThread();
        ConcurrentHashMap<String, String> fileTree = trackmanThread.fileTree;
        log.info("Thread name {}, thread priority {}, search params: "
                        + " search mask {}", this.getName(), this.getPriority(), searchMask);
    }
}
