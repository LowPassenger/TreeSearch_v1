package org.productenginetest;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface SearchEngine {
    List<String> searchMatcher(ConcurrentHashMap<String, String> fileTree, String searchMask);
}
