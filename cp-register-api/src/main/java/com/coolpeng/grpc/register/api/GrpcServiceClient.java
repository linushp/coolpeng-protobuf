package com.coolpeng.grpc.register.api;

import io.grpc.Channel;
import io.grpc.ManagedChannel;

import java.util.List;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class GrpcServiceClient {


    private String serviceName;
    private List<ManagedChannel> managedChannelList;

    public GrpcServiceClient(String serviceName){

    }

    public ManagedChannel getManagedChannel() {

    }

    public void init(){
        if (this.managedChannelList!=null && !this.managedChannelList.isEmpty()){
            return;
        }



    }

}
