package com.coolpeng.grpc.register.service.impl;

import com.coolpeng.grpc.register.api.model.GrpcServiceModel;
import com.coolpeng.grpc.register.api.model.GrpcServiceStatus;
import com.coolpeng.grpcapiproto.registercenter.GetServiceResponse;
import com.coolpeng.grpcapiproto.registercenter.GrpcRegisterCenterGrpc;
import com.coolpeng.grpcapiproto.registercenter.GrpcServicePB;
import com.coolpeng.grpcapiproto.registercenter.RegisterServiceReply;

import java.util.Collection;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class GrpcRegisterCenterServiceImpl extends GrpcRegisterCenterGrpc.GrpcRegisterCenterImplBase{


    @Override
    public void registerService(com.coolpeng.grpcapiproto.registercenter.GrpcServicePB request,
                                io.grpc.stub.StreamObserver<com.coolpeng.grpcapiproto.registercenter.RegisterServiceReply> responseObserver) {

        GrpcServiceStorage.save(request.getServiceName(),request.getIp(),request.getPort());

        RegisterServiceReply.Builder builder = RegisterServiceReply.newBuilder();
        builder.setMessage("success");
        RegisterServiceReply reply =builder.build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }





    @Override
    public void unRegisterService(com.coolpeng.grpcapiproto.registercenter.GrpcServicePB request,
                                  io.grpc.stub.StreamObserver<com.coolpeng.grpcapiproto.registercenter.RegisterServiceReply> responseObserver) {

        GrpcServiceStorage.remove(request.getServiceName(), request.getIp(), request.getPort());


        RegisterServiceReply.Builder builder = RegisterServiceReply.newBuilder();
        builder.setMessage("success");
        RegisterServiceReply reply =builder.build();

        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }





    @Override
    public void getService(com.coolpeng.grpcapiproto.registercenter.GetServiceRequest request,
                           io.grpc.stub.StreamObserver<com.coolpeng.grpcapiproto.registercenter.GetServiceResponse> responseObserver) {



        String serviceName = request.getServiceName();
        Collection<GrpcServiceModel> allServiceList = GrpcServiceStorage.getAllGrpcServiceModel();


        GetServiceResponse.Builder responseBuilder = GetServiceResponse.newBuilder();

        for (GrpcServiceModel model:allServiceList){
            if (serviceName.equals(model.getServiceName()) && model.getStatus()== GrpcServiceStatus.OK){


                GrpcServicePB.Builder pbBuilder = GrpcServicePB.newBuilder();
                pbBuilder.setServiceName(model.getServiceName());
                pbBuilder.setIp(model.getIp());
                pbBuilder.setPort(model.getPort());

                GrpcServicePB pb = pbBuilder.build();
                responseBuilder.addServices(pb);

            }
        }


        GetServiceResponse reply = responseBuilder.build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
