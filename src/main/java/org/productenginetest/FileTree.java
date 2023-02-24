package org.productenginetest;

import java.util.concurrent.ConcurrentSkipListSet;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FileTree {
    private final ConcurrentSkipListSet<String> fileTree = new ConcurrentSkipListSet<>();
}
