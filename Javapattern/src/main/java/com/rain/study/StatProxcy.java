package com.rain.study;

public class StatProxcy implements IHello {
    private  IHello  hello;


    public StatProxcy( IHello  hello){
        super();
        this.hello = hello;

    }

    @Override
    public void sayHello(String str) {
        Logger.start();
        hello.sayHello(str);
        Logger.end();

    }
}
