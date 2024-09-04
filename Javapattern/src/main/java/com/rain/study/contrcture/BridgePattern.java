package com.rain.study.contrcture;

/***
 * 桥接模式（Bridge Pattern）是一种结构型设计模式，用于将抽象部分与其实现部分分离，使它们可以独立地变化。桥接模式主要目的是将接口部分和实现部分分离开来，从而能够独立地对它们进行扩展，避免在任何一部分改变时都要重新生成整体的类层次结构。
 *
 * 定义：
 * 将抽象部分与其实现部分分离，使它们可以独立地变化
 * 原文链接：https://blog.csdn.net/weixin_37519752/article/details/138796630
 */

// 抽象部分
abstract class Abstraction {
    protected Implementor implementor;

    public Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public void operation() {
        implementor.operationImpl();
    }
}

// 实现部分的接口
interface Implementor {
    void operationImpl();
}

// 实现部分的具体实现
class ConcreteImplementorA implements Implementor {
    public void operationImpl() {
        System.out.println("ConcreteImplementorA operation");
    }
}

class ConcreteImplementorB implements Implementor {
    public void operationImpl() {
        System.out.println("ConcreteImplementorB operation");
    }
}
// 具体的抽象部分
class ConcreteAbstraction extends Abstraction {
    public ConcreteAbstraction(Implementor implementor) {
        super(implementor);
    }

    // 可以覆写operation()方法，以扩展行为
    public void operation() {
        // 先调用Implementor的行为
        implementor.operationImpl();
        // 再执行Abstraction的额外行为
        additionalOperation();
    }

    private void additionalOperation() {
        System.out.println("Additional operation in ConcreteAbstraction");
    }
}


public class BridgePattern {
    public static void main(String[] args) {
        // 创建实现部分
        Implementor implementor = new ConcreteImplementorA();
        // 创建抽象部分，绑定实现部分
        Abstraction abstraction = new ConcreteAbstraction(implementor);
        abstraction.operation();
    }
}
