package com.ssm.utils;

import java.util.UUID;

public class FileNameUtil {
    /**
     *
     * @param fileName
     * @return
     */
    public static String getNewFileName(String fileName) {
        fileName = fileName.substring(fileName.lastIndexOf("."));
        //上传后文件名
       // fileName = UUID.randomUUID().toString().replace("-", "") + fileName;
        return UUID.randomUUID().toString().replace("-", "") + fileName;
    }
}
