package com.pay.aile.meituan;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //        System.out.println("**********");
        //        String json = "{\"name\":\"a\",\"list\":\"[{\"age\":\"18\"}]\"}";
        //        A a = JSONObject.parseObject(json, A.class);
        //        System.out.println(a);

        A a = new A();
        a.setName("a");
        List<B> list = new ArrayList<B>();
        B b = new B();
        b.setAge("1");
        list.add(b);
        a.setList(list);
        String json = JSON.toJSONString(a);
        System.out.println(json);
        A a2 = JSONObject.parseObject(json, A.class);
        System.out.println(a2);
    }

    public static class A {
        private String name;
        private List<B> list;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<B> getList() {
            return list;
        }

        public void setList(List<B> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "A [name=" + name + ", list=" + list + "]";
        }
    }

    public static class B {
        private String age;

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "B [age=" + age + "]";
        }

    }

}
