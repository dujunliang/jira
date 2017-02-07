package com.vss.sys.service.impl;

import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import com.vss.sys.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by dujunliang on 17/1/6.
 */
@Service
public class FileServiceImpl implements FileService {

    public String sep = File.separator;

    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    @Override
    public void readFileByBytes(String fileName) {

        File file = new File(fileName);
        InputStream in = null;
        try {
            logger.info("以字节为单位读取文件内容，一次读一个字节：");
            // 一次读一个字节
            in = new FileInputStream(file);
            int tempbyte;
            while ((tempbyte = in.read()) != -1) {
                System.out.write(tempbyte);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        try {
            logger.info("以字节为单位读取文件内容，一次读多个字节：");
            // 一次读多个字节
            byte[] tempbytes = new byte[100];
            int byteread = 0;
            in = new FileInputStream(fileName);
            //ReadFromFile.showAvailableBytes(in);
            // 读入多个字节到字节数组中，byteread为一次读入的字节数
            while ((byteread = in.read(tempbytes)) != -1) {
                System.out.write(tempbytes, 0, byteread);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    @Override
    public void readFileByChars(String fileName) {
        File file = new File(fileName);
        Reader reader = null;
        try {
            logger.info("以字符为单位读取文件内容，一次读一个字节：");
            // 一次读一个字符
            reader = new InputStreamReader(new FileInputStream(file));
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，rn这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉r，或者屏蔽n。否则，将会多出很多空行。
                if (((char) tempchar) != 'r') {
                    System.out.print((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            logger.info("以字符为单位读取文件内容，一次读多个字节：");
            // 一次读多个字符
            char[] tempchars = new char[30];
            int charread = 0;
            reader = new InputStreamReader(new FileInputStream(fileName));
            // 读入多个字符到字符数组中，charread为一次读取字符数
            while ((charread = reader.read(tempchars)) != -1) {
                // 同样屏蔽掉r不显示
                if ((charread == tempchars.length)
                        && (tempchars[tempchars.length - 1] != 'r')) {
                    System.out.print(tempchars);
                } else {
                    for (int i = 0; i < charread; i++) {
                        if (tempchars[i] == 'r') {
                            continue;
                        } else {
                            System.out.print(tempchars[i]);
                        }
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public String pathexp(File file){
        String filepath ="" ;
        if(file != null) {
            String pathParent = file.getParent();
            String filename = file.getName().replace("\\", "");
            filepath = pathParent+sep+filename;
        }
        return filepath;
    }

    @Override
    public List<String[]> readFileByLines(File file) {

        if (file == null) {
            return null;
        }
        CsvReader r = null;
        List<String[]> list = new ArrayList<String[]>();
        try {
            //FileReader fReader = new FileReader(file);
            //CsvReader csvReader = new CsvReader(fReader);
            r = new CsvReader(pathexp(file), ',', Charset.forName("gbk"));
            //读取表头
            r.readHeaders();
            //逐条读取记录，直至读完
            while (r.readRecord()) {
                //读取一条记录
                list.add(r.getValues());
                //按列名读取这条记录的值
//              logger.info(r.get("TYPE"));

            }
            r.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (r != null) {
                try {
                    r.close();
                } catch (Exception e1) {
                }
            }
            return list;
        }
    }

    @Override
    public Boolean writeCsv(File file,String writepath,List<String[]> list){
        CsvWriter wr = null;
        boolean bool = false;
        try {
            DateFormat format1 = new java.text.SimpleDateFormat("yyyyMMdd");

            String s = format1.format(new Date());

            String csvFilePath = writepath+s+".csv";

            deleteFile(file);

            wr =new CsvWriter(csvFilePath,',',Charset.forName("GBK"));

            for (String[] str : list){
                wr.writeRecord(str);
            }
            bool = true;

            wr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(wr!=null){
                wr.close();
            }
        }
        return bool;
    }

    @Override
    public void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
            logger.info("随机读取一段文件内容：");
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            // 读文件的起始位置
            int beginIndex = (fileLength > 4) ? 4 : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
            // 将一次读取的字节数赋给byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    @Override
    public void createFile(String path, String fileName) {
        File file = new File(path + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    @Override
    public Boolean createFilePath(String path) {
        File file = new File(path);
        //如果文件夹不存在则创建
        if (!file.exists()) {
            //file.mkdir();
            return false;
        }
        return true;
    }

    @Override
    public List<File> readfile(String filepath) {
        try {

            List<File> filelistinfo = new ArrayList<File>();
            File file = new File(filepath);
            if (!file.isDirectory()) {
                logger.info("文件");
                logger.info("path=" + file.getPath());
                logger.info("name=" + file.getName());
            } else if (file.isDirectory()) {
                logger.info("文件夹");
                String[] filelist = file.list();
                for (int i = 0; i < filelist.length; i++) {
                    File readfile = new File(filepath + "\\" + filelist[i]);
                    if (!readfile.isDirectory()) {
                        if(readfile.getName().indexOf("csv") > 0){
                            filelistinfo.add(readfile);
                        }
                    } else if (readfile.isDirectory()) {
                        readfile(filepath + "\\" + filelist[i]);
                    }
                }

            }
            return filelistinfo;

        } catch (Exception e) {
            logger.info("readfile()   Exception:" + e.getMessage());
        }
        return null;
    }
    @Override
    public Boolean renameFile(String file, String toFile) {

        boolean bool = false;

        File toBeRenamed = new File(file);
        //检查要重命名的文件是否存在，是否是文件
        if (!toBeRenamed.exists() || toBeRenamed.isDirectory()) {
            logger.info("File does not exist: " + file);
            return bool;
        }
        File newFile = new File(toFile);
        //修改文件名
        if (toBeRenamed.renameTo(newFile)) {
            logger.info("File has been renamed.");
            bool = true;
        } else {
            logger.info("Error renmaing file");
        }
        return bool;
    }

    @Override
    public Boolean deleteFolder(String deletePath) {// 根据路径删除指定的目录或文件，无论存在与否
        String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
        boolean flag = false;
        if (deletePath.matches(matches)) {
            File file = new File(deletePath);
            if (!file.exists()) {// 判断目录或文件是否存在
                return flag; // 不存在返回 false
            } else {

                if (file.isFile()) {// 判断是否为文件
                    return deleteFile(file);// 为文件时调用删除文件方法
                }
            }
        } else {
            logger.info("要传入正确路径！");
            return false;
        }
        return flag;
    }

    @Override
    public Boolean deleteFile(File file1) {// 删除单个文件

        File file = new File(pathexp(file1));
        if (file.isFile() && file.exists()) {// 路径为文件且不为空则进行删除
            file.delete();// 文件删除
            logger.info("已刪除文件PATH："+file.getPath());
            return  true;
        }
        return false;
    }

    /**
     * 显示输入流中还剩的字节数
     *
     * @param in
     */
    private static void showAvailableBytes(InputStream in) {
        try {
            logger.info("当前字节输入流中的字节数为:" + in.available());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
