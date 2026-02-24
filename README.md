# 📁 FileCabinet – Recursive Folder Structure (Java)

[![Java](https://img.shields.io/badge/Java-17+-orange)](#)
[![JUnit](https://img.shields.io/badge/JUnit-5-blue)](#)
[![Status](https://img.shields.io/badge/Status-Completed-brightgreen)](#)

---

## 🚀 Project Overview

This project implements a recursive folder structure where:

- `Folder` represents a simple folder
- `MultiFolder` extends `Folder` and can contain other folders
- `FileCabinet` stores top-level folders and provides search/count operations

The goal was to implement:

```java
Optional<Folder> findFolderByName(String name);
List<Folder> findFoldersBySize(String size);
int count();
```

## 🧩 Analysis of the Problem

The structure forms a **tree-like hierarchy**:

- `Folder` – leaf node  
- `MultiFolder` – composite node that may contain other folders  

This creates a recursive structure of arbitrary depth.

---

## 🧠 Design Decision

To avoid code duplication, I introduced a private helper method inside `FileCabinet`:

```java
private void walkFolders(List<Folder> folders, Consumer<Folder> consumer)
```

This method:

1. Iterates over the provided list.
2. Applies a given operation (`Consumer`) to each folder.
3. If the folder is a `MultiFolder`, it recursively processes its children.

This allows all required operations (`count`, `findFolderByName`, `findFoldersBySize`) to reuse the same traversal logic.

---

## ✅ Implemented Methods

### count()

Counts all folders in the structure (including nested ones).

Implementation:
- Uses `walkFolders`
- Increments a counter for each visited folder

---

### findFolderByName(String name)

Returns any folder with the given name.

Implementation:
- Traverses entire structure
- Stores first matching result
- Returns `Optional.ofNullable(result)`

If no folder matches, returns `Optional.empty()`.

---

### findFoldersBySize(String size)

Returns all folders matching the provided size.

Implementation:
- Traverses structure recursively
- Collects matching folders into a list
- Returns empty list if no matches found

---

## 🧪 Tests

The solution includes unit tests verifying:

- Counting in empty and nested structures
- Searching by name at different depths
- Searching by size across levels
- Correct handling of empty cabinets

The recursive behavior is tested using a five-level nested structure.

---
