package com.coolpeng.grpc.register.api.model;

import io.grpc.ManagedChannel;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class GrpcServiceChannel {
    private ManagedChannel managedChannel;
    private GrpcServiceModel grpcServiceModel;

    public ManagedChannel getManagedChannel() {
        return managedChannel;
    }

    public void setManagedChannel(ManagedChannel managedChannel) {
        this.managedChannel = managedChannel;
    }

    public GrpcServiceModel getGrpcServiceModel() {
        return grpcServiceModel;
    }

    public void setGrpcServiceModel(GrpcServiceModel grpcServiceModel) {
        this.grpcServiceModel = grpcServiceModel;
    }
}
