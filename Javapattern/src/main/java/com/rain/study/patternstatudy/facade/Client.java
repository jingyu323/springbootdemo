package com.rain.study.patternstatudy.facade;

/**
 * 门面模式
 * 门面模式是一种结构型设计模式，它提供了一个简单的接口来访问复杂系统中的子系统。
 * 在门面模式中，我们定义了一个门面类，该类包含了客户端需要使用的所有方法，并且可以将这些方法转换为适合实际子系统的调用。
 * 门面模式的目的是简化客户端和子系统之间的交互，并提高代码的可维护性和可读性。
 *
 * 门面模式通常包括以下角色：
 *
 * 门面(Facade)：定义了客户端可以访问的所有方法，并将这些方法转换为适合实际子系统的调用。
 * 子系统(SubSystem)：实现了门面所定义的接口，并负责实际处理请求。
 * 适用场景
 * 门面模式通常用于以下场景：
 *
 * 当需要简化复杂系统的操作时，例如在大型软件系统中，可能存在多个子系统，每个子系统都有自己的接口和实现方式。
 * 如果客户端需要访问多个子系统，那么就需要编写大量的代码来处理各个子系统之间的交互，这时候就可以使用门面模式来简化操作。
 * 当需要解耦客户端和子系统之间的依赖关系时，例如在测试阶段，可能需要替换或模拟子系统的某些组件。
 * 门面模式的一个经典例子是，当我们需要访问多个Web服务时，可以使用门面模式将其封装成一个简单的接口。
 * 例如，我们可以定义一个WebServiceFacade类，并将所有的Web服务都封装到这个类中。
 * 然后对于客户端来说，只需要调用WebServiceFacade类的方法即可完成与各个Web服务的交互，从而避免了直接访问多个Web服务所带来的复杂性
 */

// 定义一个门面类，提供简单的接口给客户端使用
// 子系统1
class Subsystem1 {
    public void operation1() {
        System.out.println("子系统1的操作");
    }
}

// 子系统2
class Subsystem2 {
    public void operation2() {
        System.out.println("子系统2的操作");
    }
}

// 子系统3
class Subsystem3 {
    public void operation3() {
        System.out.println("子系统3的操作");
    }
}


class Facade {
    private Subsystem1 subsystem1;
    private Subsystem2 subsystem2;
    private Subsystem3 subsystem3;

    public Facade() {
        subsystem1 = new Subsystem1();
        subsystem2 = new Subsystem2();
        subsystem3 = new Subsystem3();
    }

    // 提供一个简单的接口给客户端使用，隐藏了子系统的复杂性
    public void operation() {
        subsystem1.operation1();
        subsystem2.operation2();
        subsystem3.operation3();
    }
}

public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.operation();
    }
}

/**
 *
 * 把复杂的接口再包装
 */
