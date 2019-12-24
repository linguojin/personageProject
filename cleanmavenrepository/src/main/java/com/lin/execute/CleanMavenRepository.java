package com.lin.execute;

import java.io.File;

/**
 * @author Lin
 * @Descriprion: 清理Maven仓库错误文件或文件夹
 * @Version 1.0.0
 * @Date 2019/12/24 11:52
 */
public class CleanMavenRepository {
    private static String REPOSITORY_RUL = "E:\\repository";

    public static void main(String[] args) {
        File file = new File(REPOSITORY_RUL);
        File[] files = file.listFiles();
        if (files!=null&&files.length>0){
            for (File f:files){
                clearRepository(f);
            }
        }
    }

    private static Boolean clearRepository(File file) {
        Boolean isHaveJar =true;
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File aFile : files) {
                // 判断是否为jar文件
                if (aFile.getName().endsWith(".jar")) {
                    isHaveJar = false;
                }
                // 判断是否为文件夹
                if (aFile.isDirectory()) {
                    // 是文件夹则判断其内部是否包含jar文件
                    boolean isNextHaveJar = clearRepository(aFile);
                    if (isNextHaveJar) {
                        isHaveJar = false;
                    }
                }
            }
        }
        if (isHaveJar) {
            System.out.println("Delete The File: " + file.getName());
            delete(file);
        }
        return isHaveJar;
    }

    private static void delete(File file) {
        File[] files = file.listFiles();
        if (files != null && files.length > 0) {
            for (File aFile : files) {
                if (aFile.isDirectory()) {
                    delete(aFile);
                }
                aFile.delete();
            }
        } else {
            file.delete();
        }
    }
}
