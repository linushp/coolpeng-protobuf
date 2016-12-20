//
//package io.grpc.examples.helloworld;
//
//import com.coolpeng.grpc.register.api.GrpcServiceServer;
//import io.grpc.stub.StreamObserver;
//
//import java.io.IOException;
//import java.util.logging.Logger;
//
///**
// * Server that manages startup/shutdown of a {@code Greeter} server.
// */
//public class HelloWorldServer2 {
//    private static final Logger logger = Logger.getLogger(HelloWorldServer2.class.getName());
//
//    /**
//     * Main launches the server from the command line.
//     */
//    public static void main(String[] args) throws IOException, InterruptedException {
//        GrpcServiceServer server = new GrpcServiceServer(10221,"helloworld");
//        server.addService(new GreeterImpl());
//        server.start();
//        server.blockUntilShutdown();
//    }
//
//    private static class GreeterImpl extends GreeterGrpc.GreeterImplBase {
//
//        @Override
//        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
//            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
//            responseObserver.onNext(reply);
//            responseObserver.onCompleted();
//            System.out.println(req.getName());
//        }
//
//    }
//}
