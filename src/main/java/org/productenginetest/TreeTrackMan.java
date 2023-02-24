package org.productenginetest;

import java.util.concurrent.ConcurrentSkipListSet;

public interface TreeTrackMan {
    ConcurrentSkipListSet<String> treeTraversal(String rootFolder, int depth);
}
