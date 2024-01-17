package com.rain.study.patternstatudy.builder;


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
