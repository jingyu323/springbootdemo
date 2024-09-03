package com.rain.study.create;
/**
 * 单例模式确保一个类只有一个实例，并提供一个全局访问点。
 *
 * 优点：
 *
 * 全局访问：提供了一个全局访问点，便于控制实例数量。
 * 资源节约：避免了创建多个对象时的资源浪费。
 * 线程安全：在多线程环境中，可以保证只创建一个实例。
 * 控制实例化：可以控制对象的创建过程。
 * 缺点：
 *
 * 代码耦合：单例模式可能会隐藏一些依赖关系，导致代码耦合。
 * 可测试性差：由于单例模式是全局的，这使得单元测试变得困难。
 * 内存浪费：单例对象在程序的整个生命周期内都占用内存。
 * 滥用：单例模式有时会被滥用，导致程序设计不灵活。
 * 适用场景：
 *
 * 需要确保在整个应用程序中只存在一个实例的情况，如配置管理器、线程池、缓存等。
 */
public class Singleton {


    // 私有构造函数，防止外部实例化
    private Singleton() {
    }

    // 私有静态变量，唯一的实例
    private static Singleton instance;

    // 公有静态方法，提供全局访问点
    public static Singleton getInstance() {
        // 双重检查锁定，确保线程安全
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // 其他业务方法
    public void doSomething() {
        // ...
        System.out.println("doSomething .................");
    }

    public static void main(String[] args) {
        // 获取单例对象
        Singleton singleton = Singleton.getInstance();
        // 使用单例对象
        singleton.doSomething();
    }
}
