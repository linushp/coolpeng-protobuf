package com.coolpeng.grpc.register.service.servicemonitor;

import com.coolpeng.grpc.register.service.impl.GrpcServiceStorage;
import com.coolpeng.grpc.register.api.model.GrpcServiceModel;
import com.coolpeng.grpc.register.api.model.GrpcServiceStatus;
import com.coolpeng.grpcapiproto.pingpong.ServicePingPongCheckGrpc;
import com.coolpeng.grpcapiproto.pingpong.ServicePingRequest;
import com.coolpeng.grpcapiproto.pingpong.ServicePongResponse;
import io.grpc.ManagedChannel;

import java.util.Collection;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class ServiceMonitorThread implements Runnable {

    public void run() {

        while (true) {

            //1分钟
            threadSleep(1);


            Collection<GrpcServiceModel> modelSet = GrpcServiceStorage.getAllGrpcServiceModel();

            if (modelSet != null && !modelSet.isEmpty()) {

                for (GrpcServiceModel serviceModel : modelSet) {

                    try {

                        ManagedChannel channel = ServiceMonitorChannelManager.getManagedChannel(serviceModel.getIp(), serviceModel.getPort());

                        ServicePingPongCheckGrpc.ServicePingPongCheckBlockingStub blockingStub = ServicePingPongCheckGrpc.newBlockingStub(channel);

                        ServicePingRequest.Builder builder = ServicePingRequest.newBuilder();

                        builder.setMessage("ping");
                        ServicePingRequest request = builder.build();
                        ServicePongResponse response = blockingStub.ping(request);
                        String responseMessage = response.getMessage();

                        if ("pong".equals(responseMessage)) {
                            serviceModel.setStatus(GrpcServiceStatus.OK);
                        } else {
                            serviceModel.setStatus(GrpcServiceStatus.ERROR);
                        }


                    } catch (Throwable e) {

                        serviceModel.setStatus(GrpcServiceStatus.UNKNOWN);
                        e.printStackTrace();
                    }
                }
            }


        }
    }


    private static void threadSleep(int munite) {
        try {
            //一分钟
            Thread.sleep(1000 * 60 * munite);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
