package com.coolpeng.grpc.register.api.innerimpl.forclient;

import com.coolpeng.grpc.register.api.GrpcServiceServer;
import com.coolpeng.grpc.register.api.model.GrpcServiceModel;
import com.coolpeng.grpc.register.api.model.GrpcServiceStatus;
import com.coolpeng.grpcapiproto.registercenter.GetServiceRequest;
import com.coolpeng.grpcapiproto.registercenter.GetServiceResponse;
import com.coolpeng.grpcapiproto.registercenter.GrpcRegisterCenterGrpc;
import com.coolpeng.grpcapiproto.registercenter.GrpcServicePB;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class GetRegisterredGrpcServiceInnerImpl {
    private final ManagedChannel channel;
    private final GrpcRegisterCenterGrpc.GrpcRegisterCenterBlockingStub blockingStub;


    public GetRegisterredGrpcServiceInnerImpl(String registerCenterHost, int registerCenterPort) {

        channel = ManagedChannelBuilder.forAddress(registerCenterHost, registerCenterPort)
                .usePlaintext(true)
                .build();
        blockingStub = GrpcRegisterCenterGrpc.newBlockingStub(channel);
    }


    public List<GrpcServiceModel> getServicesList(String serviceName) {

        GetServiceRequest.Builder builder = GetServiceRequest.newBuilder();
        builder.setServiceName(serviceName);

        GetServiceRequest req = builder.build();
        GetServiceResponse res = blockingStub.getService(req);

        List<GrpcServicePB> pbList = res.getServicesList();


        List<GrpcServiceModel> result = new ArrayList<GrpcServiceModel>();
        if (pbList != null && !pbList.isEmpty()) {
            for (GrpcServicePB pb : pbList) {
                GrpcServiceModel model = new GrpcServiceModel();

                model.setServiceName(pb.getServiceName());
                model.setIp(pb.getIp());
                model.setPort(pb.getPort());
                model.setStatus(GrpcServiceStatus.OK);

                result.add(model);
            }
        }

        return result;

    }
}
