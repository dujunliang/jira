package com.vss.sys.service;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * Created by dujunliang on 17/1/6.
 */
public abstract interface FileService {

    /**
     * 以字节为单位读取文件，常用于读二进制文件，如图片、声音、影像等文件。
     *
     * @param fileName
     */
    public abstract void readFileByBytes(String fileName);

    /**
     * 以字符为单位读取文件，常用于读文本，数字等类型的文件。
     *
     * @param fileName
     */
    public abstract void readFileByChars(String fileName);

    /**
     * 以行为单位读取文件，常用于读面向行的格式化文件 。
     *
     * @param file
     */
    public abstract List<String[]> readFileByLines(File file);

    /**
     * 文件錄入
     * @param file
     * @param list
     * @param wirepath
     * @return
     */
    public abstract Boolean writeCsv(File file,String wirepath, List<String[]> list);

    /**
     * 随机读取文件内容 。
     *
     * @param fileName
     */
    public abstract void readFileByRandomAccess(String fileName);

    /**
     * 文件创建。
     *
     * @param path,fileName
     */
    public abstract void createFile(String path, String fileName);

    /**
     * 文件夹创建。
     *
     * @param path,fileName
     */
    public abstract Boolean createFilePath(String path);

    /**
     * 读取文件夹下所有文件
     *
     * @param path
     * @return
     */
    public abstract List<File> readfile(String path);

    /**
     * 文件名字修改
     *
     * @param file
     * @param newFile
     * @return
     */
    public abstract Boolean renameFile(String file, String newFile);

    /**
     * 文件刪除
     *
     * @param path
     * @return
     */
    public abstract Boolean deleteFolder(String path);

    /**
     * 刪除單個文件
     * @param file
     */
    public Boolean deleteFile(File file);


}
