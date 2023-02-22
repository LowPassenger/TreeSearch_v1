package org.productenginetest;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {
    private static final String ROOT_NODE_REGEX = "^/|(/[a-zA-Z0-9_]+)+$";
    private static Scanner scanner = new Scanner(System.in);
    private static final TreeTrackman trackman = new TreeTrackmanImpl();
    private static final SearchEngine search = new SearchEngineImpl();

    public static void main(String[] args) {
        System.out.println("Please enter the root folder for search: ");
        String rootPath = readLineFromKeyboard();
        while (rootPath.matches(ROOT_NODE_REGEX) || !new File(rootPath).exists()) {
            System.out.println("Bed root path or directory doesn't exist. ");
            tryAgain();
        }
        System.out.println("Please enter the positive integer depth for search: ");
        String searchDepthString = readLineFromKeyboard();
        while (!isNumeric(searchDepthString) && (Integer.valueOf(searchDepthString) < 0
                || Integer.valueOf(searchDepthString) > 10000)) {
            tryAgain();
        }
        int searchDepth = Integer.valueOf(searchDepthString);
        System.out.println("Please enter the mask for search: ");
        String searchMask = readLineFromKeyboard();
        while (searchMask == null) {
            tryAgain();
        }

        long trackTimeStamp = System.currentTimeMillis();
        ConcurrentHashMap<String, String> fileTree = trackman.treeTraversal(rootPath, searchDepth);
        log.info("Collection for treeTrack {}, time for searching {}, search params: "
                        + "rootPath {}, search depth {}, search mask {}",
                fileTree.getClass(), System.currentTimeMillis() - trackTimeStamp, rootPath,
                searchDepth, searchMask);
        trackTimeStamp = System.currentTimeMillis();
        List<String> searchResult = search.searchMatcher(fileTree, searchMask);
        log.info("Collection for treeSearch {}, time for searching {}, search params: "
                        + " search mask {}",
                searchResult.getClass(), System.currentTimeMillis() - trackTimeStamp, searchMask);
        if (searchResult.isEmpty()) {
            System.out.println("Result: none");
        } else {
            System.out.println("Results: ");
            Stream.of(searchResult).forEach(System.out::println);
        }
    }

    private static String readLineFromKeyboard() {
        String line = scanner.nextLine();
        return line;
    }

    private static String tryAgain() {
        System.out.print("Please try again! " + System.lineSeparator());
        String line = scanner.nextLine();
        return line;
    }

    private static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double parseDouble = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}
