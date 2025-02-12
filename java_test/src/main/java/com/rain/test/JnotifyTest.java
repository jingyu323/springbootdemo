package com.rain.test;

import net.contentobjects.jnotify.JNotify;
import net.contentobjects.jnotify.JNotifyAdapter;
import net.contentobjects.jnotify.JNotifyException;

public class JnotifyTest extends JNotifyAdapter {



    //可以写到配置文件中
    private static final String REQUEST_BASE_PATH = "D:\\zhujiatun\\message";
    /**
     * 被监视的目录
     */
    String path = REQUEST_BASE_PATH;
    /**
     * 关注目录的事件
     */
    int mask =JNotify.FILE_CREATED |JNotify.FILE_DELETED| JNotify.FILE_RENAMED| JNotify.FILE_MODIFIED;
    /**
     * 是否监视子目录，即级联监视
     */
    boolean watchSubtree = true;
    /**
     * 监听程序Id
     */
    public int watchID;

    /**
     * 容器启动时启动监视程序
     */
    public void beginWatch() {
        /**
         * 添加到监视队列中
         */
        try {
            this.watchID = JNotify.addWatch(path, mask, watchSubtree, this);
            System.err.println("jnotify -----------启动成功2-----------");
        } catch (JNotifyException e) {
            e.printStackTrace();
        }
        /**
         * 死循环，线程一直执行，休眠一分钟后继续执行，主要是为了让主线程一直执行 休眠时间和监测文件发生的效率无
         * （就是说不是监视目录文件改变一分钟后才监测到，监测几乎是实时的，调用本地系统库）
         *
         * 不加死循环 执行完成后推出
         */
//        while (true) {
//            try {
//                //主要缓和主线程的执行效率，
//                Thread.sleep(600);
//            } catch (InterruptedException e) {// ignore it
//            }
//        }
    }

    /**
     * 文件创建
     * @param wd 监听程序Id 初始为1，多个监控程序以此加1
     * @param rootPath 目录名
     * @param name 文件名
     */

    @Override
    public void fileCreated(int wd, String rootPath, String name) {

        System.err.println(wd+"----->文件被创建, 创建位置为： " + rootPath + "\\" + name);
    }

    /**
     * 删除文件
     * @param wd 监听程序Id 初始为1，多个监控程序以此加1
     * @param rootPath 目录名
     * @param name 文件名
     */
    @Override
    public void fileDeleted(int wd, String rootPath, String name) {
        System.err.println(wd+"----->文件被删除, 被删除的文件名为：" + rootPath + name);
    }

    /**
     * 文件修改 (文件内容被修改和文件夹被修改都可以检测到)
     * @param wd 监听程序Id 初始为1，多个监控程序以此加1
     * @param rootPath 目录名
     * @param name 文件名
     */
    @Override
    public void fileModified(int wd, String rootPath, String name) {
        System.err.println(wd+"----->文件内容被修改, 文件名为：" + rootPath + "\\" + name);
    }

    /**
     * 文件重命名
     * @param wd 监听程序Id 初始为1，多个监控程序以此加1
     * @param rootPath 目录名
     * @param oldName 修改前目录名
     * @param newName 修改后目录名
     */
    @Override
    public void fileRenamed(int wd, String rootPath, String oldName, String newName) {
        System.err.println(wd+"----->文件被重命名, 原文件名为：" + rootPath + "\\" + oldName
                + ", 现文件名为：" + rootPath + "\\" + newName);
    }
}
