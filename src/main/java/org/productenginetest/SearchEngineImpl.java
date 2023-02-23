package org.productenginetest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SearchEngineImpl implements SearchEngine {
    private static final String PLUG = "empty";
    private String raw;
    private String actual;

    @Override
    public List<String> searchMatcher(ConcurrentHashMap<String,
            String> fileTree, String searchMask) {
        List<String> searchResult = new ArrayList<>();
        Iterator<Map.Entry<String, String>> iterator = fileTree.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = iterator.next();
            if (entry.getValue().equals(PLUG)) {
                raw = entry.getKey();
            } else {
                raw = entry.getValue();
            }
            String[] filePath = raw.split("/");
            String globMask = maskCorrector(searchMask);
            if ((filePath[filePath.length - 1]).matches(globMask)) {
                searchResult.add(raw);
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
