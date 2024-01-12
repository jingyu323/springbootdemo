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

public class CopyFileTestClient {

    public static void main(String[] args) throws IOException {
        outSide();
    }

    /**
     * 耗时：661
     * 耗时：762
     *
     * @throws IOException
     */
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

    /**
     * MMAP
     * MMAP原理就是建立了一个文件映射，划分了一个虚拟空间，往这个空间写数据，少了一次拷贝
     * <p>
     * 缺点：空间有限
     * <p>
     * 耗时：638
     *
     * @throws IOException
     */
    public static void mmp() throws IOException {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        long start = System.currentTimeMillis();
        Path path = Paths.get("E:\\work\\test0704.tar.gz");
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

    /**
     * 原理就是两个通道之间直接传输数据，根据系统支持程度，少了1-2次拷贝
     * <p>
     * 缺点：局限于文件通道
     * <p>
     * 实践案例：Netty、Kafka
     * 耗时：662
     *
     * @throws IOException
     */
    public static void transferTo() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        long start = System.currentTimeMillis();
        Path path = Paths.get("E:\\work\\test0704.tar.gz");
        FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
        System.out.println(open.size());

        long size = open.size();
        long position = 0;
        long total = 0;
        while (position < size) {
            long currentNum = open.transferTo(position, open.size(), socketChannel);
            System.out.println("复制字节数:" + currentNum);
            if (currentNum <= 0) {
                break;
            }
            total += currentNum;
            position += currentNum;
        }


        System.out.println(total);
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
        socketChannel.close();
    }

    /**
     * 堆外内存
     * 原理直接使用堆外内存，少了一次拷贝
     * <p>
     * 缺点：堆外内存开启耗时，此内存不受JVM控制，如垃圾回收等
     * <p>
     * 实践案例：Netty
     * 耗时：729
     * 耗时：706
     *
     * @throws IOException
     */
    public static void outSide() throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));

        long start = System.currentTimeMillis();
        Path path = Paths.get("E:\\work\\test0704.tar.gz");
        FileChannel open = FileChannel.open(path, StandardOpenOption.READ);
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect((int) open.size());
        open.read(byteBuffer);
        byteBuffer.flip();
        socketChannel.write(byteBuffer);
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
