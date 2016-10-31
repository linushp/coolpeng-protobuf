package com.coolpeng.grpc.register.service.servicemonitor;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class ServiceMonitor {

    public static void startMonitor() {
        Thread thread = new Thread(new ServiceMonitorThread());
        thread.start();
    }
}
