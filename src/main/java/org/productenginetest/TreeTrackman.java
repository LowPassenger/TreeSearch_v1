package org.productenginetest;

import java.util.concurrent.ConcurrentLinkedDeque;

public interface TreeTrackman {
    ConcurrentLinkedDeque<String> treeTrack(String rootFolder, int depth);
}
