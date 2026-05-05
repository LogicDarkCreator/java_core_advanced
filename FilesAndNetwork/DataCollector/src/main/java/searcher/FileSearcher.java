package searcher;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearcher {

    public List<File> findFiles(String rootPath, String extension) {
        List<File> foundFiles = new ArrayList<>();
        File rootDir = new File(rootPath);

        if (!rootDir.exists() || !rootDir.isDirectory()) {
            System.out.println("Указанный путь не существует или не является директорией: " + rootPath);
            return foundFiles;
        }

        searchFiles(rootDir, extension, foundFiles);
        return foundFiles;
    }

    private void searchFiles(File directory, String extension, List<File> foundFiles) {
        File[] files = directory.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                searchFiles(file, extension, foundFiles);
            } else if (file.getName().toLowerCase().endsWith(extension.toLowerCase())) {
                foundFiles.add(file);
            }
        }
    }

    public void printFoundFiles(String rootPath) {
        System.out.println("\n--- ПОИСК ФАЙЛОВ В ПАПКЕ: " + rootPath + " ---");

        List<File> jsonFiles = findFiles(rootPath, ".json");
        System.out.println("Найдено JSON файлов: " + jsonFiles.size());
        for (File file : jsonFiles) {
            System.out.println("  - " + file.getAbsolutePath());
        }

        List<File> csvFiles = findFiles(rootPath, ".csv");
        System.out.println("Найдено CSV файлов: " + csvFiles.size());
        for (File file : csvFiles) {
            System.out.println("  - " + file.getAbsolutePath());
        }
    }
}