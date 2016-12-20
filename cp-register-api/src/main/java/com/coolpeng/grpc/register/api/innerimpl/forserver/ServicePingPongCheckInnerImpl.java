//package com.coolpeng.grpc.register.api.innerimpl.forserver;
//
//
//import com.coolpeng.grpcapiproto.pingpong.*;
//
//
//public class ServicePingPongCheckInnerImpl extends ServicePingPongCheckGrpc.ServicePingPongCheckImplBase{
//
//    @Override
//    public void ping(com.coolpeng.grpcapiproto.pingpong.ServicePingRequest req,
//                     io.grpc.stub.StreamObserver<com.coolpeng.grpcapiproto.pingpong.ServicePongResponse> responseObserver) {
//
//        String clientMsg = req.getMessage();
//
//        String serverMsg = "ok";
//        if ("ping".equals(clientMsg)){
//            serverMsg = "pong";
//        }
//
//        ServicePongResponse reply = ServicePongResponse.newBuilder().setMessage(serverMsg).build();
//        responseObserver.onNext(reply);
//        responseObserver.onCompleted();
//
//    }
//}
