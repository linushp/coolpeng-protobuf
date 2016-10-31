package com.coolpeng.grpc.register.api.innerimpl;

import com.coolpeng.grpc.register.api.GrpcServiceServer;
import com.coolpeng.grpc.register.api.config.GrpcRegisterConfig;
import com.coolpeng.grpcapiproto.registercenter.GrpcRegisterCenterGrpc;
import com.coolpeng.grpcapiproto.registercenter.GrpcServicePB;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Created by luanhaipeng on 16/10/28.
 */
public class GrpcRegisterCenterClientUtilsImpl {


    private final ManagedChannel channel;
    private final GrpcRegisterCenterGrpc.GrpcRegisterCenterBlockingStub blockingStub;


    public GrpcRegisterCenterClientUtilsImpl(){

        channel = ManagedChannelBuilder.forAddress(GrpcRegisterConfig.REGISTER_CENTER_IP_ADDR, GrpcRegisterConfig.REGISTER_CENTER_PORT)
                .usePlaintext(true)
                .build();
        blockingStub = GrpcRegisterCenterGrpc.newBlockingStub(channel);
    }


    public void unRegisterServer(GrpcServiceServer grpcServiceServer) {
        GrpcServicePB.Builder requestBuilder = GrpcServicePB.newBuilder();

        requestBuilder.setIp(grpcServiceServer.getIpAddress());
        requestBuilder.setPort(grpcServiceServer.getPort());
        requestBuilder.setServiceName(grpcServiceServer.getServerName());

        GrpcServicePB request = requestBuilder.build();
        blockingStub.unRegisterService(request);

    }


    public void registerServer(GrpcServiceServer grpcServiceServer) {

        GrpcServicePB.Builder requestBuilder = GrpcServicePB.newBuilder();

        requestBuilder.setIp(grpcServiceServer.getIpAddress());
        requestBuilder.setPort(grpcServiceServer.getPort());
        requestBuilder.setServiceName(grpcServiceServer.getServerName());

        GrpcServicePB request = requestBuilder.build();
        blockingStub.registerService(request);
    }
}
