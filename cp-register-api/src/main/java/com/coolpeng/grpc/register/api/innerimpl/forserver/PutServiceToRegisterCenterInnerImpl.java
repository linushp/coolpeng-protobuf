//package com.coolpeng.grpc.register.api.innerimpl.forserver;
//
//import com.coolpeng.grpc.register.api.GrpcServiceServer;
//import com.coolpeng.grpc.register.api.config.GrpcRegisterConfig;
//import com.coolpeng.grpcapiproto.registercenter.GrpcRegisterCenterGrpc;
//import com.coolpeng.grpcapiproto.registercenter.GrpcServicePB;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//
///**
// *  服务启动时，服务Provider向注册中心注册服务。
// */
//public class PutServiceToRegisterCenterInnerImpl {
//
//
//    private final ManagedChannel channel;
//    private final GrpcRegisterCenterGrpc.GrpcRegisterCenterBlockingStub blockingStub;
//
//
//    public PutServiceToRegisterCenterInnerImpl(String registerCenterHost, int registerCenterPort){
//
//        channel = ManagedChannelBuilder.forAddress(registerCenterHost, registerCenterPort)
//                .usePlaintext(true)
//                .build();
//        blockingStub = GrpcRegisterCenterGrpc.newBlockingStub(channel);
//    }
//
//
//    public void unRegisterServer(GrpcServiceServer grpcServiceServer) {
//        GrpcServicePB.Builder requestBuilder = GrpcServicePB.newBuilder();
//
//        requestBuilder.setIp(grpcServiceServer.getIpAddress());
//        requestBuilder.setPort(grpcServiceServer.getPort());
//        requestBuilder.setServiceName(grpcServiceServer.getServiceName());
//
//        GrpcServicePB request = requestBuilder.build();
//        blockingStub.unRegisterService(request);
//
//    }
//
//
//    public void registerServer(GrpcServiceServer grpcServiceServer) {
//
//        GrpcServicePB.Builder requestBuilder = GrpcServicePB.newBuilder();
//
//        requestBuilder.setIp(grpcServiceServer.getIpAddress());
//        requestBuilder.setPort(grpcServiceServer.getPort());
//        requestBuilder.setServiceName(grpcServiceServer.getServiceName());
//
//        GrpcServicePB request = requestBuilder.build();
//        blockingStub.registerService(request);
//    }
//}
