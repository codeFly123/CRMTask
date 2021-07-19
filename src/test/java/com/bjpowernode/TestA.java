package com.bjpowernode;

public class TestA {
    TestA(){
        System.out.println("P");
        this.init();
    }
    void init(){
        System.out.println("Q");
    }


    public static void main(String[] args) {
        TestB testB = new TestB();

    }


}
class TestB extends TestA{
    int i=1;
    TestB(){
        super();
        System.out.println(i+"");
    }

    void init(){
        System.out.println("C");
        this.i=2;
        System.out.println(i+"");
    }
}
