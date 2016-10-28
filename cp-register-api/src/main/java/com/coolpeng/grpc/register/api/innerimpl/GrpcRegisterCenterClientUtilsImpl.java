package com.coolpeng.grpc.register.api.innerimpl;

import com.coolpeng.grpc.register.api.GrpcServiceServer;
import com.coolpeng.grpcapiproto.registercenter.GrpcRegisterCenterGrpc;
import com.coolpeng.grpcapiproto.registercenter.RegisterServiceRequest;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

/**
 * Created by luanhaipeng on 16/10/28.
 */
public class GrpcRegisterCenterClientUtilsImpl {

    private static final String REGISTER_CENTER_IP_ADDR = "127.0.0.1";
    private static final int REGISTER_CENTER_PORT = 10001;



    private final ManagedChannel channel;
    private final GrpcRegisterCenterGrpc.GrpcRegisterCenterBlockingStub blockingStub;


    public GrpcRegisterCenterClientUtilsImpl(){

        channel = ManagedChannelBuilder.forAddress(REGISTER_CENTER_IP_ADDR, REGISTER_CENTER_PORT)
                .usePlaintext(true)
                .build();
        blockingStub = GrpcRegisterCenterGrpc.newBlockingStub(channel);
    }

    public void unRegisterServer(GrpcServiceServer grpcServiceServer) {

        RegisterServiceRequest.Builder requestBuilder = RegisterServiceRequest.newBuilder();

        requestBuilder.setIp(grpcServiceServer.getIpAddress());
        requestBuilder.setPort(grpcServiceServer.getPort());
        requestBuilder.setServerName(grpcServiceServer.getServerName());

        RegisterServiceRequest request = requestBuilder.build();
        blockingStub.registerService(request);

    }

    public void registerServer(GrpcServiceServer grpcServiceServer) {

    }
}
