package org.productenginetest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngineImpl implements SearchEngine {
    private static final String PLUG = "empty";
    private String raw;
    private String actual;

    @Override
    public void searchMatcher(ConcurrentHashMap<String, String> fileTree, String searchMask) {
        for (Map.Entry<String, String> entry : fileTree.entrySet()) {
            if (entry.getValue().equals(PLUG)) {
                raw = entry.getKey();
            } else {
                raw = entry.getValue();
            }
            String[] split = raw.split("/");
            Pattern pattern = Pattern.compile(maskCorrecter(searchMask));
            Matcher matcher = pattern.matcher(split[split.length - 1]);

            if (matcher.matches()) {
                System.out.println(raw);
            }
        }
    }

    private String maskCorrecter(String searchMask) {
        StringBuilder correctMask = new StringBuilder();
        char[] maskChars = searchMask.toCharArray();
        for (char symbol : maskChars) {
            if (symbol == '*' || symbol == '+'
                    || symbol == '?' || symbol == '^'
                    || symbol == '$') {
                correctMask.append("\\").append("\\w\\*");
            }
            correctMask.append(symbol);
        }
        return correctMask.toString();
    }
}
