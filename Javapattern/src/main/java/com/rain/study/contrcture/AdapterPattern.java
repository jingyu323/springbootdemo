package com.rain.study.contrcture;

/**
 * 适配器模式（Adapter Pattern）是一种结构型设计模式，用于将不兼容的接口转换为一个可以使用的兼容接口。它允许原本由于接口不兼容而不能一起工作的类可以一起工作，增加了类的可重用性。
 */


// 目标接口，定义客户端使用的特定领域相关的接口
interface Target {
    void request();
}

// 适配者接口，定义了被适配者的一个或多个不兼容的接口
interface Adaptee {
    void specificRequest();
}
// 适配器类，将源接口转换成目标接口
class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        // 调用被适配者的方法
        adaptee.specificRequest();
    }
}

// 被适配者，实现了一个特定的业务逻辑
class ConcreteAdaptee implements Adaptee {
    public void specificRequest() {
        System.out.println("Executing specificRequest in Adaptee");
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        // 创建被适配者
        Adaptee adaptee = new ConcreteAdaptee();
        // 创建适配器，将被适配者和目标接口关联起来
        Target target = new Adapter(adaptee);
        // 使用适配器
        target.request();
    }
}
