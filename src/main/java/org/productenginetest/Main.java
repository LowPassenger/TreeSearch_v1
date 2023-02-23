package org.productenginetest;

import java.io.File;
import java.util.Scanner;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {
    private static final Scanner scanner = new Scanner(System.in);

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
                    if (searchDepth < 0 || searchDepth > 50) break readDepth;
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

        TreeTrackmanThread trackManThread = new TreeTrackmanThread(rootPath, searchDepth);
        trackManThread.start();
        SearchEngineThread search = new SearchEngineThread(searchMask);
        search.start();
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
