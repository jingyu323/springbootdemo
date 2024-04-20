package com.rain.study.patternstatudy.builder;

/***
 * 建造者模式
 * 建造者模式是一种创建型设计模式，它允许您创建复杂的对象，而无需了解其内部构造。
 * 在建造者模式中，我们将一个复杂的对象分解成多个简单的部件，然后依次将这些部件组合起来，从而形成一个完整的对象。
 *
 * 建造者模式通常包含以下角色：
 *
 * 产品(Product)：表示要创建的复杂对象。
 * 抽象建造者(Builder)：定义了构建产品所需的各个部件的接口。
 * 具体建造者(Concrete Builder)：实现了抽象建造者接口，并且负责实际构建产品的各个部件。
 * 指挥者(Director)：负责控制产品的构建流程。
 * 适用场景
 * 建造者模式通常用于以下场景：
 *
 * 当需要创建复杂对象时，例如包含多个部件或配置项的对象。
 * 当需要创建多个不同的产品时，例如在生成器中可以定义不同的生成器子类，每个子类可以创建不同类型的产品。
 * 当需要避免构造函数中有过多的参数或者参数顺序对外部客户端产生侵入性时。
 * 建造者模式的一个经典例子是，在一个游戏中，我们需要构建一个人物角色，该角色包含多个部件，如头、身体、手、脚等，每个部件都可以有不同的属性。
 * 在这种情况下，我们可以使用建造者模式来构建该人物角色，将其分解成多个部件，并逐步地组装它们。
 * 另外，建造者模式还可以用于创建复杂的数据结构或XML文档等
 *
 *
 */

// 定义产品类
class Product {
    private String partA;
    private String partB;
    private String partC;

    public void setPartA(String partA) {
        this.partA = partA;
    }

    public void setPartB(String partB) {
        this.partB = partB;
    }

    public void setPartC(String partC) {
        this.partC = partC;
    }

    public void show() {
        System.out.println("产品部件A：" + partA);
        System.out.println("产品部件B：" + partB);
        System.out.println("产品部件C：" + partC);
    }
}
// 抽象建造者类
abstract class Builder {
    // 创建产品对象
    protected Product product = new Product();

    public abstract void buildPartA();

    public abstract void buildPartB();

    public abstract void buildPartC();

    // 返回产品对象
    public Product getResult() {
        return product;
    }
}


// 具体建造者类1
class ConcreteBuilder1 extends Builder {
    public void buildPartA() {
        product.setPartA("建造者模式 PartA");
    }

    public void buildPartB() {
        product.setPartB("建造者模式 PartB");
    }

    public void buildPartC() {
        product.setPartC("建造者模式 PartC");
    }
}

// 具体建造者类2
class ConcreteBuilder2 extends Builder {
    public void buildPartA() {
        product.setPartA("建造者模式 PartX");
    }

    public void buildPartB() {
        product.setPartB("建造者模式 PartY");
    }

    public void buildPartC() {
        product.setPartC("建造者模式 PartZ");
    }
}

// 指挥者类
class Director {
    // 组装产品
    public void construct(Builder builder) {
        builder.buildPartA();
        builder.buildPartB();
        builder.buildPartC();
    }
}


public class Client {
    public static void main(String[] args) {
        Director director = new Director();
        Builder builder1 = new ConcreteBuilder1();
        Builder builder2 = new ConcreteBuilder2();

        director.construct(builder1);
        Product product1 = builder1.getResult();
        product1.show();

        director.construct(builder2);
        Product product2 = builder2.getResult();
        product2.show();
    }
}
