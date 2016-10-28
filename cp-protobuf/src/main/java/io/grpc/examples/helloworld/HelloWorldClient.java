
package io.grpc.examples.helloworld;

import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.concurrent.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A simple client that requests a greeting from the {@link HelloWorldServer}.
 */
public class HelloWorldClient {
    private static final Logger logger = Logger.getLogger(HelloWorldClient.class.getName());

    private final ExecutorService cachedThreadPool = Executors.newFixedThreadPool(10);

    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;
    private final GreeterGrpc.GreeterFutureStub futureStub;


    /** Construct client connecting to HelloWorld server at {@code host:port}. */
    public HelloWorldClient(String host, int port) {

//        GRPCServerCenter.getService("helloworld");

        channel = ManagedChannelBuilder.forAddress(host, port)
                // Channels are secure by default (via SSL/TLS). For the example we disable TLS to avoid
                // needing certificates.
                .usePlaintext(true)
                .build();
        blockingStub = GreeterGrpc.newBlockingStub(channel);
        futureStub = GreeterGrpc.newFutureStub(channel);
    }

    public void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }


    public void greetAsyn(String name){
//        logger.info("Will try to greet " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        final ListenableFuture<HelloReply> response = futureStub.sayHello(request);
        response.addListener(new Runnable() {
            public void run() {

                try {

                    logger.info("isDone :" + response.isDone());

                    HelloReply x = response.get();

                    logger.info(x.getMessage());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

            }
        },cachedThreadPool);

    }

    /** Say hello to server. */
    public void greet(String name) {
//        logger.info("Will try to greet " + name + " ...");
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response;
        try {
            response = blockingStub.sayHello(request);
        } catch (StatusRuntimeException e) {
            logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
            return;
        }
        logger.info("Greeting: " + response.getMessage());
    }

    /**
     * Greet server. If provided, the first element of {@code args} is the name to use in the
     * greeting.
     */
    public static void main(String[] args) throws Exception {
        HelloWorldClient client = new HelloWorldClient("localhost", 50051);


        try {
            while (true) {
      /* Access a service running on the local machine on port 50051 */
                String user = "world";
                if (args.length > 0) {
                    user = args[0]; /* Use the arg as the name to greet if provided */
                }
                client.greet(user);
                client.greetAsyn("Shabi");
                Thread.sleep(1000);
            }

        } finally {
            client.shutdown();
        }



    }
}