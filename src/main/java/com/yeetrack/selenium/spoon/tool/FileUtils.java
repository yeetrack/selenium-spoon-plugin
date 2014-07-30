package com.yeetrack.selenium.spoon.tool;

import java.io.*;

/**
 * Created by xuemeng.wang on 2014/7/29.
 */
public class FileUtils
{
    /**
     * 从InputStream流中将文件写到目标文件中
     * @param ins
     * @param objFolderFile
     * @throws IOException
     */
    public static void copyTextFile(InputStream ins, File objFolderFile)
    {
        if (ins==null) return;


        FileOutputStream outs = null;
        try {
            outs = new FileOutputStream(objFolderFile);

            byte[] buffer = new byte[1024 * 512];
            int length=-1;
            while ((length = ins.read(buffer)) != -1) {
                outs.write(buffer, 0, length);
                outs.flush();
            }
            ins.close();

            outs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从InputStream中复制二进制文件
     */
    public static void copyBinaryFile(InputStream ins, File obFolderFile)
    {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(ins);
            bos = new BufferedOutputStream(new FileOutputStream(obFolderFile));

            int hasRead = 0;
            byte b[] = new byte[10240];
            while((hasRead = bis.read(b)) > 0){
                bos.write(b, 0, hasRead);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(bos != null){
                try {
                    bos.flush();
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bis != null){
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
