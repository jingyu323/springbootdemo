package com.rain.study.beheaver;

/**
 * 3.2.9. 策略模式
 * 策略模式（Strategy Pattern）是一种行为型设计模式，它定义了一系列算法，并将每个算法封装起来让它们可以互换使用。策略模式让算法的变化独立于使用算法的用户，用户只需要关注算法的结果，而不必知道算法的实现细节。
 *
 * 定义：
 * 定义了一系列算法，并将每个算法封装起来让它们可以互换。
 *
 * 优点：
 *
 * 解耦：策略模式将算法的使用从算法的实现中分离出来。
 * 可扩展性：新的算法可以很容易地添加到系统中。
 * 可替换：可以很容易地替换或更新现有的算法。
 * 缺点：
 *
 * 增加复杂性：可能会增加系统中策略类的数量。
 * 性能考虑：由于策略模式使用多态，可能会有一些性能开销。
 * 适用场景：
 *
 * 当需要在运行时选择不同的处理方式时。
 * 当一个系统中有多个子系统，而各个子系统有类似的行为，但具体实现不同，可以避免使用条件语句来决定使用哪个子系统时。
 * 原文链接：https://blog.csdn.net/weixin_37519752/article/details/138796630
 */

// 策略接口
interface Strategy {
    void execute();
}

// 具体策略A
class ConcreteStrategyA implements Strategy {
    public void execute() {
        System.out.println("Executing ConcreteStrategyA");
    }
}

// 具体策略B
class ConcreteStrategyB implements Strategy {
    public void execute() {
        System.out.println("Executing ConcreteStrategyB");
    }
}

// 环境类
class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.execute();
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        // 创建环境类，注入策略
        Context context = new Context(new ConcreteStrategyA());

        // 执行策略
        context.executeStrategy();

        // 更换策略
        context.setStrategy(new ConcreteStrategyB());
        context.executeStrategy();
    }
}
