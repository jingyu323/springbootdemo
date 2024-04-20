package com.rain.study.patternstatudy.cp;


/**
 * 责任链模式是一种行为型设计模式，它允许您将请求沿着处理者链进行传递，直到其中一个处理者处理该请求。
 * 在责任链模式中，我们定义了一个抽象处理者类，该类包含一个指向下一个处理者的引用，并提供了处理请求的方法。
 * 然后我们可以创建多个具体处理者类来实现处理请求的不同方式。每个具体处理者类都可以决定是否处理该请求，或者将其传递给下一个处理者。
 *
 * 责任链模式通常包括以下角色：
 *
 * 抽象处理者(Handler)：定义了处理请求的接口，并且包含一个指向下一个处理者的引用。
 * 具体处理者(Concrete Handler)：实现了抽象处理者接口，并且负责实际处理请求
 *
 * 适用场景
 * 责任链模式通常用于以下场景：
 *
 * 当我们需要处理不同类型的请求，并且每个请求可能需要由不同的处理者来处理时。
 * 当我们需要避免将请求发送给错误的处理者时，例如在电商网站中，如果某个商品已经售完，就应该立即停止对该商品的下单操作，并提示用户其已售罄。
 * 当我们需要动态地指定可以处理请求的对象集合时。
 * 责任链模式的一个经典例子是，当我们需要处理不同级别的日志记录时，可以使用责任链模式。
 * 例如，我们可以定义一个抽象处理者类来表示不同级别的日志记录器，并将其指向下一个处理者。
 * 然后对于每个日志消息，我们可以将其传递给第一个处理者，并且只有当该处理者不能处理该请求时，才会将其传递给下一个处理者。
 * 另外，在Web应用程序中，责任链模式也可以用于处理HTTP请求。
 *
 */

/**
 * 定义一个处理请求的接口
 */
interface Handler {
    void setNextHandler(Handler handler);
    void handleRequest(int request);
}


/**
 * 实现处理请求的接口，具体处理逻辑在子类中实现
 */
abstract class AbstractHandler implements Handler {
    protected Handler nextHandler;

    @Override
    public void setNextHandler(Handler handler) {
        this.nextHandler = handler;
    }

    @Override
    public void handleRequest(int request) {
        if (nextHandler != null) {
            nextHandler.handleRequest(request);
        }
    }
}

/**
 * 具体的处理类，处理请求编号为1的请求
 */
class ConcreteHandler1 extends AbstractHandler {
    @Override
    public void handleRequest(int request) {
        if (request == 1) {
            System.out.println("ConcreteHandler1 处理了请求 " + request);
        } else {
            super.handleRequest(request);
        }
    }
}


/**
 * 具体的处理类，处理请求编号为2的请求
 */
class ConcreteHandler2 extends AbstractHandler {
    @Override
    public void handleRequest(int request) {
        if (request == 2) {
            System.out.println("ConcreteHandler2 处理了请求 " + request);
        } else {
            super.handleRequest(request);
        }
    }
}

/**
 * 具体的处理类，处理请求编号为3的请求
 */
class ConcreteHandler3 extends AbstractHandler {
    @Override
    public void handleRequest(int request) {
        if (request == 3) {
            System.out.println("ConcreteHandler3 处理了请求 " + request);
        } else {
            super.handleRequest(request);
        }
    }
}


public class Client {
    public static void main(String[] args) {
        // 构建责任链
        Handler handler1 = new ConcreteHandler1();
        Handler handler2 = new ConcreteHandler2();
        Handler handler3 = new ConcreteHandler3();
        handler1.setNextHandler(handler2);
        handler2.setNextHandler(handler3);

        // 发送请求
        handler1.handleRequest(1);
        handler1.handleRequest(2);
        handler1.handleRequest(3);
    }
}
