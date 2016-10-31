package com.coolpeng.grpc.register.api;

import com.coolpeng.grpc.register.api.config.GrpcRegisterConfig;
import com.coolpeng.grpc.register.api.innerimpl.forserver.PutServiceToRegisterCenterInnerImpl;
import com.coolpeng.grpc.register.api.innerimpl.forserver.ServicePingPongCheckInnerImpl;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by luanhaipeng on 16/10/28.
 */
public class GrpcServiceServer {

    private static final Logger logger = Logger.getLogger(GrpcServiceServer.class.getName());

    private String ipAddress = "";
    private int port = 0;
    private Server server;
    private String serviceName;
    private List<BindableService> bindableServiceList = new ArrayList<BindableService>();
    private PutServiceToRegisterCenterInnerImpl grpcRegisterCenterClientUtils;

    private String registerCenterHost = GrpcRegisterConfig.REGISTER_CENTER_IP_ADDR; //注册中心IP地址
    private int registerCenterPort = GrpcRegisterConfig.REGISTER_CENTER_PORT;//注册中心端口

    public GrpcServiceServer(int port,String serviceName) throws UnknownHostException {
        this.ipAddress = InetAddress.getLocalHost().getHostAddress();
        this.port = port;
        this.serviceName = serviceName;
    }

    public void start() throws IOException {

        this.grpcRegisterCenterClientUtils = new PutServiceToRegisterCenterInnerImpl(this.registerCenterHost,this.registerCenterPort);

        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);

        serverBuilder.addService(new ServicePingPongCheckInnerImpl());

        addServiceToBuilder(serverBuilder);
        server = serverBuilder.build().start();

        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {

                GrpcServiceServer.this.grpcRegisterCenterClientUtils.unRegisterServer(GrpcServiceServer.this);

                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                GrpcServiceServer.this.stop();
                System.err.println("*** server shut down");
            }
        });

        this.grpcRegisterCenterClientUtils.registerServer(this);
    }



    public void addService(BindableService bindableService){
        bindableServiceList.add(bindableService);
    }


    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    private void addServiceToBuilder(ServerBuilder<?> serverBuilder){
        for (BindableService bindableService:bindableServiceList){
            serverBuilder.addService(bindableService);
        }
    }


    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public void setRegisterCenterHostAndPort(String registerCenterHost,int registerCenterPort) {
        this.registerCenterHost = registerCenterHost;
        this.registerCenterPort = registerCenterPort;
    }

}
