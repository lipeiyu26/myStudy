package com.lpyu.zs;


import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VSFTPD1 {
    public static void main(String args[])  throws  IOException{
        FTPClient ftp = new FTPClient();
        //设置ip和端口号，卸载用户名和密码上面
        ftp.connect("192.168.139.131",21);
        //设置用户名和密码
        ftp.login("ftpuser","ftpuser");
        //设置文件类型
        ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
        InputStream IS =new FileInputStream("E:/time.jpg");
        //储存时名称
        ftp.storeFile("abc.jpg",is);
        ftp.logout();

    }
}
