package com.coolpeng.grpc.register.api.innerimpl.forclient;

import com.coolpeng.grpc.register.api.model.GrpcServiceChannel;
import com.coolpeng.grpc.register.api.model.GrpcServiceModel;

import java.util.List;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class GrpcServiceChannelUtils {


    public static void getServiceManagedChannelList(List<GrpcServiceChannel> managedChannelList, GetRegisterredGrpcServiceInnerImpl mGetRegisterredGrpcServiceInner, String serviceName) {

        List<GrpcServiceModel> serviceModelList = mGetRegisterredGrpcServiceInner.getServicesList(serviceName);
        if (serviceModelList.isEmpty()){
            return;
        }

        //处理新增的
        for (GrpcServiceModel serviceModel : serviceModelList) {
            if (!hasGrpcServiceChannel(managedChannelList, serviceModel)) {
                managedChannelList.add(createGrpcServiceChannel(serviceModel));
            }
        }

        //处理删除的
        for(GrpcServiceChannel channel:managedChannelList){
            if (!hasGrpcServiceModel(serviceModelList, channel.getGrpcServiceModel())){
                managedChannelList.remove(channel);
            }
        }

    }

    private static boolean hasGrpcServiceModel(List<GrpcServiceModel> serviceModelList, GrpcServiceModel grpcServiceModel) {

        //TODO
        return false;
    }


    private static GrpcServiceChannel createGrpcServiceChannel(GrpcServiceModel serviceModel) {

        //TODO
        return null;
    }


    private static boolean hasGrpcServiceChannel(List<GrpcServiceChannel> managedChannelList, GrpcServiceModel serviceModel) {
        for (GrpcServiceChannel grpcServiceChannel : managedChannelList) {

            GrpcServiceModel model = grpcServiceChannel.getGrpcServiceModel();
            if (model.equals(serviceModel)) {
                return true;
            }
        }
        return false;
    }


}
