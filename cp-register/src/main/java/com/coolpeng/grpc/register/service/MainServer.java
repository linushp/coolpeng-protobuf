package com.coolpeng.grpc.register.service;

import com.coolpeng.grpc.register.api.config.GrpcRegisterConfig;
import com.coolpeng.grpc.register.service.impl.GrpcRegisterCenterServiceImpl;
import com.coolpeng.grpc.register.service.servicemonitor.ServiceMonitor;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class MainServer {


    private static final Logger logger = Logger.getLogger(MainServer.class.getName());
    private Server server;

    private void start() throws IOException {

        int port = GrpcRegisterConfig.REGISTER_CENTER_PORT;

        server = ServerBuilder.forPort(port)
                .addService(new GrpcRegisterCenterServiceImpl())
                .build()
                .start();

        logger.info("Server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                System.err.println("*** shutting down gRPC server since JVM is shutting down");
                MainServer.this.stop();
                System.err.println("*** server shut down");
            }
        });

    }


    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    /**
     *
     * Main launches the server from the command line.
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        ServiceMonitor.startMonitor();

        final MainServer server = new MainServer();
        server.start();
        server.blockUntilShutdown();
    }


}
