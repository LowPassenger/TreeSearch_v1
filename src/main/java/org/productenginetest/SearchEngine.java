package org.productenginetest;

import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

public interface SearchEngine {
    List<String> searchMatcher(ConcurrentSkipListSet<String> fileTree, String searchMask);
}
