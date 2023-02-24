package org.productenginetest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;

public class SearchEngineImpl implements SearchEngine {
    @Override
    public List<String> searchMatcher(ArrayList<ConcurrentSkipListSet<String>> fileTree,
                                      String searchMask) {
        List<String> searchResult = new ArrayList<>();
        for (ConcurrentSkipListSet<String> levelElements : fileTree) {
            for (String element : levelElements) {
                String[] filePath = element.split("/");
                String globMask = maskCorrector(searchMask);
                if ((filePath[filePath.length - 1]).matches(globMask)) {
                    searchResult.add(element);
                }
            }
        }
        return searchResult;
    }

    private String maskCorrector(String searchMask) {
        StringBuilder correctMask = new StringBuilder();
        correctMask.append("^");
        for (int i = 0; i < searchMask.length(); ++i) {
            char symbol = searchMask.charAt(i);
            switch (symbol) {
                case '*': correctMask.append(".*");
                break;
                case '?': correctMask.append('.');
                break;
                case '.': correctMask.append("\\.");
                break;
                case '\\': correctMask.append("\\\\");
                break;
                default : correctMask.append(symbol);
            }
        }
        correctMask.append('$');
        return correctMask.toString();
    }
}
