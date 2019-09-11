package com.hz.controller;

/**
 * Created by Administrator on 2019/9/5.
 */
public class MarryCompany implements Marry {
    private You you;
    public MarryCompany(You you) {
        this.you = you;
    }
    public void marry() {
        before();
        you.marry();
        after();
    }

    private void after() {
        System.out.println("get after work done");
    }

    private void before() {
        System.out.println("get ready for marry");
    }


    public static  void main( String [] args ){
        You you = new You();
        you.marry();
        MarryCompany marryCompany = new MarryCompany(you);
        marryCompany.marry();
    }
}
