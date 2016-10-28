package com.coolpeng.grpc.register.api;

import com.coolpeng.grpc.register.api.innerimpl.GrpcRegisterCenterServiceImpl;
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
    private List<BindableService> bindableServiceList = new ArrayList<BindableService>();


    public GrpcServiceServer(int port) throws UnknownHostException {
        this.ipAddress = InetAddress.getLocalHost().getHostAddress();
        this.port = port;
    }


    public void start() throws IOException {

        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);

        serverBuilder.addService(new GrpcRegisterCenterServiceImpl());

        addServiceToBuilder(serverBuilder);
        server = serverBuilder.build().start();

        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                GrpcServiceServer.this.stop();
                System.err.println("*** server shut down");
            }
        });



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

}
