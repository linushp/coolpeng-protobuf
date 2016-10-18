package com.coolpeng.proto.test;

import io.grpc.examples.animal.Dog;
import io.grpc.examples.helloworld.HelloRequest;

import java.net.Socket;


public class AnimalTest {


    public static void main(String[] args) throws Exception {

        Dog.Builder builder = Dog.newBuilder();
        builder.setSex("男");
        builder.setAge(32);
        builder.setName("abc");
        Dog obj1 = builder.build();


        byte[] arr1 = obj1.toByteArray();
        System.out.println(arr1);

        Dog obj2 = Dog.parseFrom(arr1);

        System.out.println(obj2.getName());

    }

}
