package com.rain.study.beheaver;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式（Observer Pattern）是一种行为型设计模式，它定义了对象之间的一对多依赖关系，当一个对象改变状态时，它的所有依赖者都会收到通知并自动更新。观察者模式常用于实现事件处理系统，如图形用户界面的事件处理。
 *
 * 定义： 当对象间存在一对多关系时，则使用观察者模式。一个被观察的对象变化时，所有依赖它的对象都会得到通知并自动更新

 */


// 观察者接口
interface Observer {
    void update(String message);
}

// 主题接口
interface Subject {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// 具体主题类
class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    public void registerObserver(Observer o) {
        if (!observers.contains(o)) {
            observers.add(o);
        }
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.state);
        }
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    public String getState() {
        return state;
    }
}

// 具体观察者类
class ConcreteObserver implements Observer {
    private ConcreteSubject subject;

    public ConcreteObserver(ConcreteSubject subject) {
        this.subject = subject;
    }

    public void printState() {
        System.out.println("Observer state: " + subject.getState());
    }

    public void update(String message) {
        printState();
    }
}

public class ObserverPattern {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        // 创建观察者并注册到主题
        Observer observer1 = new ConcreteObserver(subject);
        subject.registerObserver(observer1);

        Observer observer2 = new ConcreteObserver(subject);
        subject.registerObserver(observer2);

        // 改变主题状态，观察者收到通知并更新
        subject.setState("New State");
    }
}
