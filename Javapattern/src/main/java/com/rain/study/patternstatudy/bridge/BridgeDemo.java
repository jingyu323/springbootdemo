package com.rain.study.patternstatudy.bridge;

/**
 * 桥接模式
 * 桥接模式是一种结构型设计模式，它将抽象部分和实现部分分离开来，以便两者可以独立地变化。
 * 在桥接模式中，我们使用一个抽象类或接口来表示抽象部分，并用另一个实现类来表示实现部分。
 * 然后我们将抽象部分和实现部分通过一个桥接对象连接起来，从而使它们可以独立地变化。
 *
 * 桥接模式中包含四个角色：
 *
 * 抽象部分(Abstraction)：定义了抽象部分的接口。
 * 实现部分(Implementor)：定义了实现部分的接口。
 * 具体抽象部分(Refined Abstraction)：继承自抽象部分，并且扩展了抽象部分的接口。
 * 具体实现部分(Concrete Implementor)：实现了实现部分的接口。
 *
 *
 * 适用场景
 * 桥接模式通常用于以下场景：
 *
 * 当需要将一个类分成抽象部分和实现部分时，并且需要让它们可以独立地变化时。
 * 当需要使用多个维度来扩展一个类时，例如颜色、尺寸等。
 * 当需要在运行时动态地将实现部分替换为另一个实现时。
 * 桥接模式的一个经典例子是，当我们需要绘制一个形状时，可以使用桥接模式来避免创建多个类。
 * 我们可以定义一个抽象的Shape类，并将其连接到一个实现类DrawingAPI上。然后我们可以在具体子类中实现不同的形状并调用DrawingAPI对象来绘制它们。
 * 另外，桥接模式还可以用于处理不同操作系统之间的差异，例如将GUI组件与底层操作系统分开处理
 */


// 定义实现类的接口
interface Color {
    public void applyColor();
}

// 创建实现Color接口的具体实现类
class RedColor implements Color {
    public void applyColor() {
        System.out.println("红色.");
    }
}

class BlueColor implements Color {
    public void applyColor() {
        System.out.println("蓝色.");
    }
}


// 使用Color接口创建抽象类Shape
abstract class Shape {
    protected Color color;

    public Shape(Color color) {
        this.color = color;
    }

    abstract public void applyColor();
}


// 创建扩展Shape抽象类的具体类
class Square extends Shape {
    public Square(Color color) {
        super(color);
    }

    public void applyColor() {
        System.out.print("正方形填充颜色 ");
        color.applyColor();
    }
}


class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }

    public void applyColor() {
        System.out.print("圆形填充颜色 ");
        color.applyColor();
    }
}

public class BridgeDemo {
    public static void main(String[] args) {
        Shape square = new Square(new RedColor());
        square.applyColor();

        Shape circle = new Circle(new BlueColor());
        circle.applyColor();
    }
}
