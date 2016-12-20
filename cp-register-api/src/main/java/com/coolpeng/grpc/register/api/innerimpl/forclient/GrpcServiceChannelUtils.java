//package com.coolpeng.grpc.register.api.innerimpl.forclient;
//
//import com.coolpeng.grpc.register.api.model.GrpcServiceChannel;
//import com.coolpeng.grpc.register.api.model.GrpcServiceModel;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Created by luanhaipeng on 16/10/31.
// */
//public class GrpcServiceChannelUtils {
//
//
//    public static void getServiceManagedChannelList(List<GrpcServiceChannel> managedChannelList, GetRegisterredGrpcServiceInnerImpl mGetRegisterredGrpcServiceInner, String serviceName) {
//
//        List<GrpcServiceModel> serviceModelList = mGetRegisterredGrpcServiceInner.getServicesList(serviceName);
//        if (serviceModelList.isEmpty()){
//            return;
//        }
//
//        //处理新增的
//        for (GrpcServiceModel serviceModel : serviceModelList) {
//            if (!hasGrpcServiceChannel(managedChannelList, serviceModel)) {
//                managedChannelList.add(createGrpcServiceChannel(serviceModel));
//            }
//        }
//
//        //处理删除的
//        Iterator<GrpcServiceChannel> it = managedChannelList.iterator();
//        while(it.hasNext()){
//            GrpcServiceChannel tempobj = it.next();
//            if(!hasGrpcServiceModel(serviceModelList, tempobj.getGrpcServiceModel())){
//                //移除当前的对象
//                it.remove();
//            }
//        }
//    }
//
//
//    private static boolean hasGrpcServiceModel(List<GrpcServiceModel> serviceModelList, GrpcServiceModel grpcServiceModel) {
//
//        //处理新增的
//        for (GrpcServiceModel serviceModel : serviceModelList) {
//            if (grpcServiceModel.equals(serviceModel)){
//                return true;
//            }
//        }
//
//        return false;
//    }
//
//
//    private static GrpcServiceChannel createGrpcServiceChannel(GrpcServiceModel serviceModel) {
//
//        String host = serviceModel.getIp();
//        int port = serviceModel.getPort();
//
//        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
//                .usePlaintext(true)
//                .build();
//
//        GrpcServiceChannel result = new GrpcServiceChannel();
//        result.setGrpcServiceModel(serviceModel);
//        result.setManagedChannel(channel);
//        return result;
//    }
//
//
//    private static boolean hasGrpcServiceChannel(List<GrpcServiceChannel> managedChannelList, GrpcServiceModel serviceModel) {
//        for (GrpcServiceChannel grpcServiceChannel : managedChannelList) {
//
//            GrpcServiceModel model = grpcServiceChannel.getGrpcServiceModel();
//            if (model.equals(serviceModel)) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//
//}
