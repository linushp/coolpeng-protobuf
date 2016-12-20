//package io.grpc.examples.helloworld;
//
//import com.coolpeng.grpc.register.api.GrpcServiceClient;
//
///**
// * Created by luanhaipeng on 16/10/31.
// */
//public class HelloworldClient2 extends GrpcServiceClient{
//
//    private static HelloworldClient2 instance = new HelloworldClient2();
//    public static HelloworldClient2 getInstance(){
//        return instance;
//    }
//
//
//    public GreeterGrpc.GreeterBlockingStub getGreeterBlockingStub(){
//        return GreeterGrpc.newBlockingStub(super.getManagedChannel());
//    }
//
//
//}
