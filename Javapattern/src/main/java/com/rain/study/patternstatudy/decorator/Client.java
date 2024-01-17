package com.rain.study.patternstatudy.decorator;

/**
 *
 *装饰者模式
 * 装饰者模式是一种结构型设计模式，它允许您通过将对象包装在一个装饰器对象中来动态地扩展其功能。在装饰者模式中，我们定义了一个抽象组件类，该类包含了基本的操作方法。然后我们可以创建多个具体组件类，并使用不同的装饰器来为其添加额外的功能。
 *
 * 装饰者模式通常包括以下角色：
 *
 * 抽象组件(Component)：定义了基本操作方法。
 * 具体组件(Concrete Component)：实现了抽象组件，并且可以被装饰器包装。
 * 抽象装饰器(Decorator)：定义了装饰器的接口，并包含一个指向组件的引用。
 * 具体装饰器(Concrete Decorator)：实现了抽象装饰器，并且可以为组件添加额外的功能。
 * 适用场景
 * 装饰者模式通常用于以下场景：
 *
 * 当需要动态地扩展对象的功能时，例如在运行时添加、删除或修改对象的某些属性或方法。
 * 当需要避免使用子类来扩展对象的功能时，例如当存在多个功能组合时，如果每个组合都需要创建一个新的子类，那么就会导致类层次结构的膨胀，
 * 从而增加代码的复杂性和维护成本。
 * 装饰者模式的一个经典例子是，当我们需要为文本编辑器添加新的功能时，可以使用装饰者模式。
 * 例如，我们可以定义一个抽象组件类来表示文本编辑器，并实现基本的编辑功能。
 * 然后我们可以创建多个具体组件类来表示不同类型的文本编辑器（如Word、Notepad等），并使用不同的装饰器来增强其功能（如加粗、斜体、下划线等）。
 * 另外，在Java I/O库中，也广泛使用了装饰者模式。
 *
 * 代理示例
 */


// 定义一个接口，被装饰者和装饰者都要实现该接口
interface Component {
    void operation();
}


// 被装饰者，实现 Component 接口
class ConcreteComponent implements Component {
    public void operation() {
        System.out.println("具体组件的操作");
    }
}
// 装饰者，也实现 Component 接口
class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void operation() {
        component.operation();
    }
}
// 具体的装饰者，扩展 Decorator 类
class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    public void operation() {
        super.operation();
        addedBehavior();
    }

    public void addedBehavior() {
        System.out.println("为具体组件增加额外的操作");
    }
}


public class Client {
    public static void main(String[] args) {
        Component component = new ConcreteComponent();
        Decorator decorator = new ConcreteDecorator(component);
        decorator.operation();
    }
}

/**
 * 装饰模式 李代桃僵  鱼目混珠   再之前的方法中增加   额外的方法
 */
