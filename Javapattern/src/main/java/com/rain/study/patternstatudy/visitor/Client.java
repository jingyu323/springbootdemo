package com.rain.study.patternstatudy.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问者模式
 * 访问者模式是一种行为设计模式，它允许你定义一个独立于对象结构的操作。通过这种方式，可以在不改变对象结构的前提下向对象添加新操作。
 *
 * 该模式涉及到两个核心概念：访问者和被访问者。被访问者是一个包含许多元素的对象结构，而访问者是在这些元素上执行操作的对象。
 *
 * 在访问者模式中，被访问者的元素都实现了一个公共接口，以便访问者可以在不知道具体对象类型的情况下访问每个元素。访问者可以根据自己的需要定义不同的操作，这些操作将在被访问者的所有元素上执行。
 *
 * 适用场景
 * 访问者模式适用于以下场景：
 *
 * 当你需要对一个复杂对象结构中的元素进行很多不同的操作时，但是这些操作可能会随着时间的推移而发生改变。
 * 当你需要在不改变对象结构的情况下向对象添加新操作时。
 * 当你希望分离算法和对象结构时，从而避免让它们彼此混合。
 * 例如，假设您正在编写一个应用程序，该应用程序需要处理图形对象。这些对象可以是圆形、矩形、三角形等。
 * 您需要对这些对象执行许多不同的操作，例如计算面积、打印对象信息等。
 * 您可以使用访问者模式来定义一个访问者，它可以在这些图形对象上执行这些操作，而无需知道特定的对象类型。
 * 这使得将来添加新的图形对象变得更加容易，并且可以轻松地添加新的操作，而不必修改对象结构。
 */

// 定义具体元素类B
class ConcreteElementB extends Element {
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }
}

// 定义抽象访问者类
abstract class Visitor {
    public abstract void visitConcreteElementA(ConcreteElementA elementA);
    public abstract void visitConcreteElementB(ConcreteElementB elementB);
}

// 定义具体访问者类
class ConcreteVisitor1 extends Visitor {
    public void visitConcreteElementA(ConcreteElementA elementA) {
        System.out.println("具体访问者1访问-->" + elementA.getClass().getSimpleName());
    }

    public void visitConcreteElementB(ConcreteElementB elementB) {
        System.out.println("具体访问者1访问-->" + elementB.getClass().getSimpleName());
    }
}

// 首先定义一个抽象元素类
abstract class Element {
    public abstract void accept(Visitor visitor);
}

// 定义具体元素类A
class ConcreteElementA extends Element {
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }
}

// 定义具体访问者类
class ConcreteVisitor2 extends Visitor {
    public void visitConcreteElementA(ConcreteElementA elementA) {
        System.out.println("具体访问者2访问-->" + elementA.getClass().getSimpleName());
    }

    public void visitConcreteElementB(ConcreteElementB elementB) {
        System.out.println("具体访问者2访问-->" + elementB.getClass().getSimpleName());
    }
}

// 定义对象结构类
class ObjectStructure {
    private List<Element> list = new ArrayList<Element>();

    public void accept(Visitor visitor) {
        for (Element element : list) {
            element.accept(visitor);
        }
    }

    public void add(Element element) {
        list.add(element);
    }

    public void remove(Element element) {
        list.remove(element);
    }
}

public class Client {
    public static void main(String[] args) {
        ObjectStructure os = new ObjectStructure();
        os.add(new ConcreteElementA());
        os.add(new ConcreteElementB());

        Visitor visitor1 = new ConcreteVisitor1();
        Visitor visitor2 = new ConcreteVisitor2();

        os.accept(visitor1);
        System.out.println("------------------------");
        os.accept(visitor2);
    }
}
