package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CopyToBackup {
    public static void main(String[] args) {

        String baseDir = "."; // Путь к папке, в которой нужно искать файлы
        String backupDir = "Backup"; // Название папки, которая будет создана для копирования в неё файлов

        try{
            copyFiles(baseDir, backupDir);
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    /**
     * Создание папки, в которую будут копироваться файлы
     * @param backupDir Название папки
     * @return Возвращает путь к данной папке, чтобы в дальнейшем применить его в методе Files.copy()
     */
    public static String createDirBackup(String backupDir){
        File backupDirectory = new File(backupDir);
        if (!backupDirectory.exists()){
            backupDirectory.mkdir();
            return backupDirectory.getPath();
        }
        return null;
    }

    /**
     * Копирование файлов
     * @param baseDir Папка, в которой идет отбор файлов для копирования
     * @param backupDir Имя папки. Параметр передается в метод createDirBackup(), вызываемый внутри данного метода
     * @throws IOException
     */
    public static void copyFiles(String baseDir, String backupDir) throws IOException {
        File baseDirectory = new File(baseDir);
        File[] allFiles = baseDirectory.listFiles();
        String backupPath = createDirBackup(backupDir);

        for (File file: allFiles) {
            if (file.isFile()){
                Files.copy(file.toPath(), new File( backupPath + "/" + file.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
}