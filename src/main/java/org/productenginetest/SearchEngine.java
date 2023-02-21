package org.productenginetest;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public interface SearchEngine {
    List<String> searchMatcher(ConcurrentLinkedDeque<String> filesTree, String searchMask);
}
