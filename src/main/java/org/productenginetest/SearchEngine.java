package org.productenginetest;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public interface SearchEngine {
    void searchMatcher(ConcurrentHashMap<String, String> fileTree, String searchMask);
}
