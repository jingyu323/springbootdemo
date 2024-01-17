package com.rain.study.patternstatudy.strategy;

/***
 * 策略模式
 * 策略模式是一种行为设计模式，它允许在运行时选择算法的行为。它通过封装一组算法并将其相互替换来实现这一点。策略模式使得算法可独立于使用它们的客户端而变化，从而提高了代码的灵活性和可维护性。
 *
 * 策略模式的核心思想是将不同的算法封装为一个对象，并且这些对象之间可以互相替换。通常情况下，我们会定义一个接口或抽象类来表示算法的行为，并由具体的算法类去实现这个接口或继承这个抽象类，然后在客户端通过创建这些具体的算法对象并将其传递给其他对象来实现某些操作。
 *
 * 适用场景
 * 当需要在不同情况下使用不同的算法时，例如排序、搜索等。
 * 当需要避免条件语句重复出现时，例如大量if语句。
 * 当需要增强代码的可扩展性和可维护性时，例如添加新的算法。
 * 策略模式通常用于以下场景：
 *
 * 消息处理器：根据不同的消息类型执行不同的处理算法。
 * 数据库连接器：根据不同的数据库类型执行不同的数据库连接算法。
 * 订单处理器：根据不同的订单类型执行不同的订单处理算法
 *
 * 核心点在于 定义一个持有类 根据不同的策略实例化不同的类 从而执行不同的方法
 */
  class OperationAdd implements Strategy{
    @Override
    public int doOperation(int num1, int num2) {
        return 0;
    }
}
class OperationSubtract implements Strategy {
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}

class OperationMultiply implements Strategy {
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}

// 定义策略模式的上下文
class Context {
    private Strategy strategy;

    public Context(Strategy strategy){
        this.strategy = strategy;
    }

    public int executeStrategy(int num1, int num2){
        return strategy.doOperation(num1, num2);
    }
}
// 使用策略模式的客户端


// 使用策略模式的客户端
public class StrategyPatternDemo {
    public static void main(String[] args) {
        Context context = new Context(new OperationAdd());
        System.out.println("10 + 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationSubtract());
        System.out.println("10 - 5 = " + context.executeStrategy(10, 5));

        context = new Context(new OperationMultiply());
        System.out.println("10 * 5 = " + context.executeStrategy(10, 5));
    }
}