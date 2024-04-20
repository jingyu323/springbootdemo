package com.rain.study.patternstatudy.factory;

/**
 * 工厂模式是一种创建型设计模式，它提供了一种将对象的创建过程封装起来的方法。
 * 在工厂模式中，我们通过定义一个抽象工厂接口或者抽象类来表示对象的创建过程，
 * 然后由具体的工厂类去实现这个接口或继承这个抽象类，并负责具体的对象创建。客户端只需要知道这个抽象工厂即可，不需要关心具体的实现
 */

// 定义一个接口
interface Product {
    void operation();
}

// 实现接口的具体类
class ConcreteProductA implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProductA operation");
    }
}
class ConcreteProductB implements Product {
    @Override
    public void operation() {
        System.out.println("ConcreteProductB operation");
    }
}

// 工厂类
class Factory {
    public static Product createProduct(String type) {
        if (type.equals("A")) {
            return new ConcreteProductA();
        } else if (type.equals("B")) {
            return new ConcreteProductB();
        } else {
            return null;
        }
    }
}



public class FactoryMain {
    public static void main(String[] args) {
        Product productA = Factory.createProduct("A");
        productA.operation();

        Product productB = Factory.createProduct("B");
        productB.operation();
    }
}
