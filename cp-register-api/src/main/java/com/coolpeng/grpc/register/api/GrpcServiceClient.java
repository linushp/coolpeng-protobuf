package com.coolpeng.grpc.register.api;

import io.grpc.Channel;
import io.grpc.ManagedChannel;

import java.util.List;


public class GrpcServiceClient {


    private String serviceName;
    private List<ManagedChannel> managedChannelList;

    public GrpcServiceClient(String serviceName){
        //TODO
    }

    public ManagedChannel getManagedChannel() {
        //TODO
        return null;
    }

    public void init(){
        if (this.managedChannelList!=null && !this.managedChannelList.isEmpty()){
            return;
        }


        //TODO

    }

}
