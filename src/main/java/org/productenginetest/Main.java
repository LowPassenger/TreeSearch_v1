package org.productenginetest;

import java.io.File;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static final TreeTrackman trackman = new TreeTrackmanImpl();
    private static final SearchEngine search = new SearchEngineImpl();

    public static void main(String[] args) {
        System.out.println("Please enter the root folder for search: ");
        String rootPath = readLineFromKeyboard();
        while (!new File(rootPath).exists()) {
            System.out.println("Bed root path or directory doesn't exist. ");
            rootPath = tryAgain();
        }
        int searchDepth = 0;
        while (true) {
            readDepth:
            {
                System.out.println("Please enter the positive integer depth (0...50) for search: ");
                try {
                    searchDepth = Integer.valueOf(readLineFromKeyboard());
                    if (searchDepth < 0 || searchDepth > 50) {
                        break readDepth;
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println(("Wrong number format, you should input a number"));
                }
            }
        }
        System.out.println("Please enter the mask for search: ");
        String searchMask = readLineFromKeyboard();
        while (searchMask == null || searchMask.isEmpty()) {
            searchMask = tryAgain();
        }
        scanner.close();

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
}
