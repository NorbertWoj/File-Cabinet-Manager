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
        Folder[] found = new Folder[1];

        walkFolders(folders, folder -> {
            if (found[0] == null && folder.getName().equals(name)) {
                found[0] = folder;
            }
        });

//        if (found[0] != null) {
//            return Optional.of(found[0]);
//        } else {
//            return Optional.empty();
//        }
        return Optional.ofNullable(found[0]);
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
