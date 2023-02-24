package org.productenginetest;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

public interface TreeTrackMan {
    ArrayList<ConcurrentSkipListSet<String>> treeTraversal(String rootFolder, int depth);
}
