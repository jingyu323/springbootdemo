package com.rain.study.beheaver;


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
