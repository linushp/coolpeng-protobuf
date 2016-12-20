//package com.coolpeng.grpc.register.api;
//
//import com.coolpeng.grpc.register.api.config.GrpcRegisterConfig;
//import com.coolpeng.grpc.register.api.innerimpl.forclient.GetRegisterredGrpcServiceInnerImpl;
//import com.coolpeng.grpc.register.api.innerimpl.forclient.GrpcServiceChannelUtils;
//import com.coolpeng.grpc.register.api.innerimpl.forclient.GrpcServiceMonitorThread;
//import com.coolpeng.grpc.register.api.model.GrpcServiceChannel;
//import io.grpc.Channel;
//import io.grpc.ManagedChannel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class GrpcServiceClient {
//
//    private String serviceName;
//    private final List<GrpcServiceChannel> managedChannelList = new ArrayList<GrpcServiceChannel>();
//    private int index = 0;
//
//
//    public ManagedChannel getManagedChannel() {
//        index = index + 1;
//        index = index % managedChannelList.size();
//        GrpcServiceChannel grpcServiceChannel =  managedChannelList.get(index);
//        return grpcServiceChannel.getManagedChannel();
//    }
//
//
//    /**
//     *
//     * @param registerCenterHost 注册中心的域名
//     * @param registerCenterPort 注册中心的端口
//     * @param serviceName 需要获取的服务
//     */
//    public void init(final String registerCenterHost,final int registerCenterPort, final  String serviceName){
//
//        this.serviceName = serviceName;
//        if (!this.managedChannelList.isEmpty()){
//            return;
//        }
//
//        final List<GrpcServiceChannel> managedChannelList = this.managedChannelList;
//        managedChannelList.clear();
//
//        final GetRegisterredGrpcServiceInnerImpl getRegisterredGrpcServiceInner = new GetRegisterredGrpcServiceInnerImpl(registerCenterHost, registerCenterPort);
//
//        GrpcServiceChannelUtils.getServiceManagedChannelList(managedChannelList, getRegisterredGrpcServiceInner, serviceName);
//
//        Thread thread = new Thread(new GrpcServiceMonitorThread(managedChannelList, getRegisterredGrpcServiceInner, serviceName));
//        thread.start();
//
//    }
//
//
//
//    public void init(String serviceName){
//        this.init(GrpcRegisterConfig.REGISTER_CENTER_IP_ADDR,GrpcRegisterConfig.REGISTER_CENTER_PORT,serviceName);
//    }
//
//
//
//}
