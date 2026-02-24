package com.norbertwoj;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileCabinetTest {

    @Test
    void shouldCountEmptyMultiFolder() {
        MultiFolder empty = new MultiFolderImpl("Empty", "SMALL", List.of());

        FileCabinet cabinet = new FileCabinet(List.of(empty));

        assertEquals(1, cabinet.count());
    }

    @Test
    void shouldCountFiveNestedMultiFoldersOnly() {
        MultiFolder level5 = new MultiFolderImpl("Level5", "SMALL", List.of());

        MultiFolder level4 = new MultiFolderImpl("Level4", "SMALL", List.of(level5));

        MultiFolder level3 = new MultiFolderImpl("Level3", "MEDIUM", List.of(level4));

        MultiFolder level2 = new MultiFolderImpl("Level2", "MEDIUM", List.of(level3));

        MultiFolder level1 = new MultiFolderImpl("Level1", "LARGE", List.of(level2));

        FileCabinet cabinet = new FileCabinet(List.of(level1));

        int result = cabinet.count();

        assertEquals(5, result);
    }

    @Test
    void shouldCountFiveLevelsStructureWithMultiFoldersAndFolders() {
        Folder folder10 = new FolderImpl("Folder10", "SMALL");
        Folder folder9 = new FolderImpl("Folder9", "LARGE");
        MultiFolder level5 = new MultiFolderImpl("Level5", "SMALL", List.of(folder9, folder10));

        Folder folder8 = new FolderImpl("Folder8", "MEDIUM");
        Folder folder7 = new FolderImpl("Folder7", "SMALL");
        MultiFolder level4 = new MultiFolderImpl("Level4", "MEDIUM", List.of(level5, folder7, folder8));

        Folder folder6 = new FolderImpl("Folder6", "LARGE");
        Folder folder5 = new FolderImpl("Folder5", "MEDIUM");
        MultiFolder level3 = new MultiFolderImpl("Level3", "MEDIUM", List.of(level4, folder5, folder6));

        Folder folder4 = new FolderImpl("Folder4", "SMALL");
        Folder folder3 = new FolderImpl("Folder3", "LARGE");
        MultiFolder level2 = new MultiFolderImpl("Level2", "LARGE", List.of(level3, folder3, folder4));

        Folder folder2 = new FolderImpl("Folder2", "MEDIUM");
        Folder folder1 = new FolderImpl("Folder1", "LARGE");
        MultiFolder level1 = new MultiFolderImpl("Level1", "LARGE", List.of(level2, folder1, folder2));

        Folder folder0 = new FolderImpl("Folder0", "SMALL");
        FileCabinet cabinet = new FileCabinet(List.of(level1, folder0));

        int result = cabinet.count();

        assertEquals(16, result);
    }

    @Test
    void shouldHandleEmptyCabinet() {

        FileCabinet cabinet = new FileCabinet(List.of());

        assertEquals(0, cabinet.count());

        assertTrue(cabinet.findFolderByName("AnyName").isEmpty());

        assertEquals(0, cabinet.findFoldersBySize("SMALL").size());
    }
}