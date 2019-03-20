package com.ssm.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * servicePath 后期修改 为项目地址
 */
public class UploadImgUtil {
    //D:\ForSoftware\IdeaWorkspace\ssmproject\shop-admin\src\main\webapp\static\imgs\head
    private static final String relPath = "D:/ForSoftware/IdeaWorkspace/ssmproject/shop-admin/src/main/webapp";
    private static final String imgPath = "/static/imgs";//中间路径

    /**
     * @param multipartFile 项目服务器地址
     * @param filePath      服务器内文件夹   "/head/"
     * @return
     */
    public static String uploadImg(MultipartFile multipartFile, String filePath) {
        if (multipartFile == null || multipartFile.isEmpty() || filePath == null) {
            return null;
        }
        //文件是否存在

        //服务器文件夹-存储文件夹路径
        //String relPatchDir = request.getServletContext().getRealPath("/static/imgs/head");
        //relPatchDir=http://127.0.0.1   + /static/imgs  +   /head/

        //项目中 目标文件根目录
        String dirPath = imgPath + filePath;

        //文件夹预处理
        File fileDir = new File(relPath + dirPath);
        // 文件夹是否存在，不存在创建
        if (!fileDir.isDirectory()) {
            fileDir.delete();
            fileDir.mkdirs();
        }

        //文件名处理
        String fileName = FileNameUtil.getNewFileName(multipartFile.getOriginalFilename());

        //文件存储路径-地址加文件名
        String imgUrl = dirPath + fileName;

        //上传指定位置
        try {
            multipartFile.transferTo(new File(relPath + imgUrl));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return imgUrl;


    }


}

