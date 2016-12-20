//package com.coolpeng.grpc.register.api.innerimpl.forclient;
//
//import com.coolpeng.grpc.register.api.model.GrpcServiceChannel;
//
//import java.util.List;
//
///**
// * Created by luanhaipeng on 16/10/31.
// */
//public class GrpcServiceMonitorThread implements Runnable {
//    private List<GrpcServiceChannel> managedChannelList;
//    private GetRegisterredGrpcServiceInnerImpl mGetRegisterredGrpcServiceInner;
//    private String serviceName;
//
//    public GrpcServiceMonitorThread(List<GrpcServiceChannel> managedChannelList, GetRegisterredGrpcServiceInnerImpl mGetRegisterredGrpcServiceInner, String serviceName) {
//        this.managedChannelList = managedChannelList;
//        this.mGetRegisterredGrpcServiceInner = mGetRegisterredGrpcServiceInner;
//        this.serviceName = serviceName;
//    }
//
//    public void run() {
//
//        GrpcServiceChannelUtils.getServiceManagedChannelList(managedChannelList, mGetRegisterredGrpcServiceInner, serviceName);
//
//        threadSleep(1);
//    }
//
//
//    private static void threadSleep(int munite) {
//        try {
//            //一分钟
//            Thread.sleep(1000 * 60 * munite);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
//
//}
