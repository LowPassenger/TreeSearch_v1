package org.productenginetest;

import java.util.concurrent.ConcurrentHashMap;

public interface TreeTrackMan {
    ConcurrentHashMap<String, String> treeTraversal(String rootFolder, int depth);
}
