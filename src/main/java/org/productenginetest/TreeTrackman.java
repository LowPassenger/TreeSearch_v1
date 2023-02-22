package org.productenginetest;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public interface TreeTrackman {
    ConcurrentHashMap<String, String> treeTraversal(String rootFolder, int depth);
}
