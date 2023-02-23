package org.productenginetest;

import java.util.concurrent.ConcurrentHashMap;

public interface TreeTrackman {
    ConcurrentHashMap<String, String> treeTraversal(String rootFolder, int depth);
}
