package com.pay.aile.meituan;

public class Test {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("innner");
                    throw new RuntimeException("hello");
                } catch (Exception e) {
                    System.out.println("out error");
                }
            }
        }).start();
    }
}
