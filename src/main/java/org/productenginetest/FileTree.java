package org.productenginetest;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileTree {
    private final ArrayList<ConcurrentSkipListSet<String>> fileTree = new ArrayList<>();
}
