package com.rain.study.patternstatudy.adapter;

/**
 *
 *适配者模式
 * 适配器模式是一种结构型设计模式，它允许将一个类的接口转换为客户端所期望的另一个接口。适配器模式通常用于将一些现有的类与其他类集成在一起，以实现新的功能或者适应新的系统环境。
 *
 * 适配器模式中包含三个角色：
 *
 * 目标(Target)：客户端所期望的接口，它定义了客户端可以调用的方法。
 * 适配器(Adapter)：将源接口转化为目标接口的适配器，它继承自目标接口，并包含一个源接口对象作为其属性。
 * 源(Adaptee)：需要被适配的源接口，它定义了客户端不期望使用的方法
 * 适用场景
 * 适配器模式通常用于以下场景：
 *
 * 当我们需要使用一些现有的类，并且这些类的接口与我们所需的接口不匹配时。
 * 当我们要复用一些现有的类，并且这些类的实现不能修改时。
 * 当我们需要创建一个可以复用的类库，该类库需要与多个不同的系统或框架进行集成时。
 * 适配器模式的一个经典例子是，在一个软件系统中，我们需要将一些现有的类库集成到我们的系统当中，但是这些类库的接口与我们所需的接口不匹配，
 * 这时候就可以使用适配器模式来实现。另外，适配器模式也可以用于将一些不同系统中的组件进行集成，例如将不同的支付方式集成到一个电商网站中。
 */


// 适配者接口
interface Adaptee {
    void specificRequest();
}

// 适配者实现类
class ConcreteAdaptee implements Adaptee {
    public void specificRequest() {
        System.out.println("适配者代码被调用！");
    }
}

// 目标接口
interface Target {
    void request();
}

// 适配器类
class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    public void request() {
        adaptee.specificRequest();
    }
}

public class Client {
    public static void main(String[] args) {
        Adaptee adaptee = new ConcreteAdaptee();
        Target target = new Adapter(adaptee);
        target.request();
    }
}

/**
 * 移花接木  类中嵌套调用 目标类的方法
 *
 * 与策略模式的区别 ：策略模式是针对不同的实现类 进行归一的处理
 *
 * 而 策略模式就是为了适配不同类的方法调用整合而设计
 */