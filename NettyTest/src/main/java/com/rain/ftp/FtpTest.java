package com.rain.ftp;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.IOException;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FtpTest {

    private static final String FTP_IP = "192.168.198.130";
    private static final Integer FTP_PORT = 21;
    private static final String FTP_USERNAME = "ftpserver";
    private static final String FTP_PASSWORD = "111111";
    private static final String FTP_ENCODE = "UTF-8";

    public static FTPClient getConnection() {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setConnectTimeout(60000);
        ftpClient.setDataTimeout(30000);
        ftpClient.setControlEncoding(FTP_ENCODE);// 解决上传文件时文件名乱码
        int reply = 0;
        try {
            ftpClient.connect(FTP_IP, FTP_PORT);
            ftpClient.login(FTP_USERNAME, FTP_PASSWORD);
            /**
             * 不经中转服务器（相对于remote而言），本地被动模式
             */
            ftpClient.enterLocalActiveMode();
            /**
             * 登陆成功，返回码是230
             */
            reply = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ftpClient;
    }

    public static void main(String[] args) {
        /**
         * 并发量
         */
        int size = 210;
        ExecutorService service = Executors.newFixedThreadPool(size);
        for(int i=0;i<size;i++) {
            service.execute(new Runnable() {
                public void run() {
                    FTPClient client = getConnection();
                    try {
                        FTPFile[] files = client.listFiles();
                        System.out.println(files[0].getName());
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            Thread.sleep(1000*600);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        try {
                            if(client.isConnected()) {
                                client.disconnect();
                            }
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        service.shutdown();
    }
}
