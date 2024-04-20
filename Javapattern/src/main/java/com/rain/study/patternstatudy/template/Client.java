package com.rain.study.patternstatudy.template;

/**
 * 模板模式
 * 模板模式是一种行为型设计模式，它定义了一个操作中的算法框架，
 * 将一些步骤延迟到子类中实现。在模板模式中，我们会定义一个抽象类，
 * 其中包含一个模板方法，该方法定义了算法的框架，并被其子类重写以提供不同的实现。
 *
 * 模板方法通常由几个基本方法组成，这些方法可以是抽象的、具体的或空的，而实现体现的是这些方法的执行顺序和逻辑。
 * 然后我们可以创建一个具体的子类来实现这些方法，从而提供不同的实现
 *
 * 适用场景
 * 当我们需要定义一个算法框架，并允许其子类提供特定的实现时。
 * 当我们需要实现代码重用时，例如在父类中定义了一些公共方法，可以被多个子类使用。
 * 当我们需要控制算法中某些步骤的执行顺序或者流程时，例如按照固定的流程进行处理。
 * 模板模式通常用于以下场景：
 *
 * 数据库ORM框架：根据数据库模型生成SQL语句、执行SQL语句等。
 * 测试框架：定义测试用例的执行流程，如初始化、执行测试用例、清理等。
 * 游戏开发：游戏引擎中的游戏循环框架、资源加载框架等。
 * 总之，当需要定义一个算法框架，并允许其子类提供特定的实现时，可以使用模板模式。
 *
 * 代码示例
 */


// 定义一个抽象类，其中包含一个模板方法，该方法定义了算法的基本骨架
abstract class AbstractClass {
    public void templateMethod() {
        // 调用基本方法
        primitiveOperation1();
        primitiveOperation2();
    }

    // 基本方法（由子类实现）
    abstract void primitiveOperation1();

    abstract void primitiveOperation2();
}
// 具体子类实现基本方法以完成算法中与特定子类相关的步骤
class ConcreteClass extends AbstractClass {
    void primitiveOperation1() {
        System.out.println("具体类A方法1实现");
    }

    void primitiveOperation2() {
        System.out.println("具体类A方法2实现");
    }
}


public class Client {
    public static void main(String[] args) {
        AbstractClass abstractClass = new ConcreteClass();
        abstractClass.templateMethod();
    }
}
