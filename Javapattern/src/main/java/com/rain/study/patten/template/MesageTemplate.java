package com.rain.study.patten.template;

public abstract class MesageTemplate {

    public  void  sendMsg(){
        addHeadeLog();
        httpRequest();
        addFootLog();

    }

    protected   void addFootLog(){
        System.out.println(" addFootLog .... ");
    };

    protected abstract void httpRequest();

    protected   void addHeadeLog(){
        System.out.println(" addHeadeLog .... ");

    };
}
