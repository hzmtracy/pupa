package com.cmy.basis.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipUtil {
    /**
     * 解压到指定目录
     * 
     * @param zipPath
     * @param descDir
     * @author isea533
     */
    public static void unZipFiles(String zipPath, String descDir)
            throws IOException {
        unZipFiles(new File(zipPath), descDir);
        
    }

    /**
     * 解压文件到指定目录
     * 
     * @param zipFile
     * @param descDir
     * @author isea533
     */
    @SuppressWarnings("rawtypes")
    public static void unZipFiles(File zipFile, String descDir)
            throws IOException {
        File pathFile = new File(descDir);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
        ZipFile zip = new ZipFile(zipFile);
//        entries
        for (Enumeration entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir + zipEntryName).replaceAll("\\*", "/");
            ;
            // 判断路径是否存在,不存在则创建文件路径
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
            // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
            if (new File(outPath).isDirectory()) {
                continue;
            }
            // 输出文件路径信息
            System.out.println(outPath);

            OutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        zip.close();
        System.out.println("******************解压完毕********************");
    }
//    
//    public static boolean unZipFiles(File zipFile, String descDir,String a) {
//        // 检查创建指定目录文件
//        File pathFile = new File(descDir);
//        if (!pathFile.exists()) {
//            pathFile.mkdirs();
//        }
//        
//        // 创建待解压文件
//        ZipFile zip = null;
//        try {
//            zip = new ZipFile(zipFile, Charset.forName("GBK"));
//        } catch (IOException e1) {
//            return false;
//        }
//        try {
//            for (Enumeration<? extends ZipEntry> entries = zip.entries(); 
//                    entries.hasMoreElements();) {
//                ZipEntry entry = entries.nextElement();
//                String zipEntryName = entry.getName();
//                // 每一个待解压文件的读取流
//                InputStream in = zip.getInputStream(entry);
//                String outPath = (descDir + zipEntryName)
//                        .replaceAll("\\*", "/");
//                ;
//                // 判断路径是否存在,不存在则创建文件路径
//                File file = new File(outPath.substring(0,
//                        outPath.lastIndexOf('/')));
//                if (!file.exists()) {
//                    file.mkdirs();
//                }
//                // 判断文件全路径是否为文件夹,如果是上面已经上传,不需要解压
//                if (new File(outPath).isDirectory()) {
//                    continue;
//                }
//
//                OutputStream out = new FileOutputStream(outPath);
//                byte[] buf1 = new byte[1024];
//                int len;
//                while ((len = in.read(buf1)) > 0) {
//                    out.write(buf1, 0, len);
//                }
//                in.close();
//                out.close();
//            }
//            zip.close();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
}
