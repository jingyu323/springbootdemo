package com.rain.study.beheaver;


/**
 * 命令模式（Command Pattern）是一种行为型设计模式，它将一个请求封装为一个对象，
 * 从而允许用户使用不同的请求、队列或日志请求来参数化其他对象。
 * 命令模式通常用于解耦请求的发送者和接收者，让发送者和接收者之间通过命令对象进行交互。
 *
 * 定义： 将一个请求封装为一个对象，从而允许用户使用不同的请求来参数化其他对象。
 *
 * 优点：
 *
 * 解耦：命令模式将调用操作的对象与知道如何实现该操作的对象解耦。
 * 扩展性：可以容易地扩展新的命令。
 * 宏命令：可以组合多个命令到一个宏命令中。
 * 缺点：
 *
 * 增加系统复杂性：可能会增加系统中对象的数量。
 * 命令过多：如果系统中命令对象太多，可能会导致管理上的复杂性。
 * 适用场景：
 *
 * 当需要将请求封装为对象，从而可以用不同的请求对客户进行参数化时。
 * 当需要支持取消操作时。
 * 当需要支持事务和日志功能时。
 */

// 命令接口
interface Command {
    void execute();
}

// 接收者，知道如何实施与执行一个请求相关的操作
class Receiver {
    public void action() {
        System.out.println("Receiver performs action");
    }
}

// 具体的命令类，实现execute()方法，负责调用接收者的功能
class ConcreteCommand implements Command {
    private Receiver receiver;

    public ConcreteCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    public void execute() {
        receiver.action();
    }
}

// 调用者，要求命令对象执行请求
class Invoker {
    public void invoke(Command command) {
        command.execute();
    }
}



public class ComandPatern {
    public static void main(String[] args) {
        // 创建接收者对象
        Receiver receiver = new Receiver();
        // 创建具体命令对象，将接收者对象传入
        Command command = new ConcreteCommand(receiver);
        // 创建调用者对象
        Invoker invoker = new Invoker();

        // 通过调用者对象执行命令对象
        invoker.invoke(command);
    }
}
