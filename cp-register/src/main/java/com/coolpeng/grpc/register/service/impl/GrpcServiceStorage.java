package com.coolpeng.grpc.register.service.impl;

import com.coolpeng.grpc.register.api.model.GrpcServiceModel;
import com.coolpeng.grpc.register.api.model.GrpcServiceStatus;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class GrpcServiceStorage {


    private static final Map<String,GrpcServiceModel> serviceModelMAP = new ConcurrentHashMap<String, GrpcServiceModel>();

    private static String toModelKey(String serverName, String ip, int port){
        return serverName+ "---" + ip +":"+port;
    }

    public static void save(String serverName, String ip, int port) {
        String key = toModelKey(serverName,ip,port);
        GrpcServiceModel model = new GrpcServiceModel(serverName, ip, port);
        model.setStatus(GrpcServiceStatus.OK);
        serviceModelMAP.put(key, model);
    }

    public static void remove(String serverName, String ip, int port) {
        String key = toModelKey(serverName,ip,port);
        serviceModelMAP.remove(key);
    }


    public static Collection<GrpcServiceModel> getAllGrpcServiceModel() {
        return serviceModelMAP.values();
    }
}
