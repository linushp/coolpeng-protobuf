package com.coolpeng.grpc.register.service.servicemonitor;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class ServiceMonitorChannelManager {


    private static final Map<String, ManagedChannel> managedChannelMap = new ConcurrentHashMap<String, ManagedChannel>();

    public static String toKey(String host, int port) {
        return host + ":" + port;
    }

    public static ManagedChannel getManagedChannel(String host, int port) {

        String key = toKey(host, port);

        ManagedChannel managedChannel = managedChannelMap.get(key);

        if (managedChannel == null) {

            managedChannel = ManagedChannelBuilder.forAddress(host, port).usePlaintext(true).build();

            managedChannelMap.put(key, managedChannel);

        }

        return managedChannel;
    }
}
