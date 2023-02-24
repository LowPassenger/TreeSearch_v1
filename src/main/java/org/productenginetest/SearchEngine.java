package org.productenginetest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

public interface SearchEngine {
    List<String> searchMatcher(ArrayList<ConcurrentSkipListSet<String>> fileTree,
                               String searchMask);
}
