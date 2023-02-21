package org.productenginetest;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SearchEngineImpl implements SearchEngine {
    private static final String PLUG = "/<>:/|Yu$MMy-Du#MMy";

    @Override
    public List<String> searchMatcher(ConcurrentLinkedDeque<String> filesTree, String searchMask) {
        List<String> searchResult = new LinkedList<>();
        while (filesTree.peekLast().equals(PLUG)) {
            String result = filesTree.removeLast();
            if (result.equals(searchMask)) {
                searchResult.add(result);
            }
        }
        return searchResult;
    }
}
