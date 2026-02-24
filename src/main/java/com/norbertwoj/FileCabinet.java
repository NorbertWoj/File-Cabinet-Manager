package com.norbertwoj;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

    public FileCabinet(List<Folder> folders) {
        this.folders = List.copyOf(folders);
    }

    private void walkFolders(List<Folder> folders, Consumer<Folder> consumer) {
        for (Folder folder : folders) {
             consumer.accept(folder);
             if (folder instanceof MultiFolder) {
                 List<Folder> children = ((MultiFolder) folder).getFolders();
                 walkFolders(children, consumer);
             }
        }
    }

    @Override
    public Optional<Folder> findFolderByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Folder> findFoldersBySize(String size) {
        return List.of();
    }

    @Override
    public int count() {
        AtomicInteger counter = new AtomicInteger();

        walkFolders(folders, folder -> counter.incrementAndGet());

        return counter.get();
    }
}
