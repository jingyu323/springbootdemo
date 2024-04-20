package com.rain.test.filetransfer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class CopyFileTest {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务端：等待连接");
        Socket accept = serverSocket.accept();
        System.out.println("服务端：" + accept.getRemoteSocketAddress() + "已连接");

        File file = new File("E:\\work\\test0704223322.tar.gz");
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        InputStream bufferedInputStream = accept.getInputStream();
        byte[] bytes = new byte[2048];
        int read;
        while ((read = bufferedInputStream.read(bytes, 0, 2048)) != -1) {
            fileOutputStream.write(bytes);
        }
        OutputStream outputStream = accept.getOutputStream();
        outputStream.write("接收完毕".getBytes());
//        accept.shutdownOutput();

        fileOutputStream.close();
        outputStream.close();
        bufferedInputStream.close();

//        accept.close();


    }

    public static void normal() throws IOException {
        Socket socket = new Socket("127.0.0.1", 8080);
        OutputStream outputStream = socket.getOutputStream();
        InputStream inputStream = socket.getInputStream();

        long start = System.currentTimeMillis();
        File file = new File("E:\\work\\test0704.tar.gz");
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] bytes1 = new byte[2048];
        while (fileInputStream.read(bytes1, 0, 2048) != -1) {
            outputStream.write(bytes1);
        }
        socket.shutdownOutput();
        System.out.println("耗时：" + (System.currentTimeMillis() - start));

        byte[] bytes = new byte[1024];
        String message = "";
        int read;
        while ((read = inputStream.read(bytes)) != -1) {
            message += new String(bytes, 0, read);
        }
        System.out.println("服务端发来消息->" + message);

        inputStream.close();
        outputStream.close();
        socket.close();
    }

    public static void mmp() throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        long start = System.currentTimeMillis();
        Path path = Paths.get("C:\\Users\\Administrator\\Desktop\\222.txt");
        FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
        MappedByteBuffer map = open.map(FileChannel.MapMode.READ_ONLY, 0, open.size());
        socketChannel.write(map);
        socketChannel.shutdownOutput();
        System.out.println("耗时：" + (System.currentTimeMillis() - start));

        ByteBuffer allocate = ByteBuffer.allocate(1024);
        int read = socketChannel.read(allocate);
        if (read > 0) {
            allocate.flip();
            byte[] bytes = new byte[allocate.remaining()];
            allocate.get(bytes);
            System.out.println("服务端发来消息：" + new String(bytes));
        }

        socketChannel.close();
    }

}
