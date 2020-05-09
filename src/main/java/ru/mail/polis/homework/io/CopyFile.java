package ru.mail.polis.homework.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CopyFile {

    /**
     * Реализовать копирование папки из pathFrom в pathTo. Скопировать надо все внутренности
     * Файлы копировать ручками через стримы.
     * В тесте для создания нужных файлов для первого запуска надо расскоментировать код в setUp()
     * <p>
     * 6 баллов
     */
    public static String copyFiles(String pathFrom, String pathTo) {
        try {
            Path directoryIn = Paths.get(pathFrom);
            Path directoryOut = Paths.get(pathTo);

            if (Files.notExists(directoryIn)) {
                return null;
            }
            if (Files.isRegularFile(directoryIn)) {
                copyFile(directoryIn, directoryOut);
                return null;
            }
            if (Files.notExists(directoryOut)) {
                Files.createDirectories(directoryOut);
            }

            try (DirectoryStream<Path> paths = Files.newDirectoryStream(directoryIn)) {
                for (Path path : paths) {
                    copyFiles(path.toString(), directoryOut.resolve(path.getFileName()).toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void copyFile(Path dirIn, Path dirOut) throws IOException {
        if (Files.notExists(dirOut)) {
            Files.createDirectories(dirOut.getParent());
        }
        Files.createFile(dirOut);

        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(dirIn))) {
            try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(Files.newOutputStream(dirOut))) {
                byte[] buffer = new byte[1024];
                int lengthRead = 0;
                while ((lengthRead = bufferedInputStream.read(buffer)) > 0) {
                    bufferedOutputStream.write(buffer, 0, lengthRead);
                }
            }
        }
    }
}
