package com.norbertwoj;

import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {
    private List<Folder> folders;

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
        int counter = 0;

        for (int i = 0; i < folders.size(); i++) {
            counter++;
            Folder folder = folders.get(i);
            if ( folder instanceof MultiFolder ) {
                List<Folder> children = ((MultiFolder) folder).getFolders();

                for ( int j = 0; j < children.size(); j++) {
                    counter++;
                    if ( children.get(j) instanceof MultiFolder) {
                        List<Folder> children2 = ((MultiFolder) children.get(j)).getFolders();

                        for ( int k = 0; k < children2.size(); k++) {
                            counter++;

                            // ...
                        }
                    }
                }

            }
        }

        return counter;
    }
}
