package io.grpc.examples.helloworld;

import com.coolpeng.grpc.register.api.GrpcServiceClient;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public class HelloworldClient2 extends GrpcServiceClient{

    public HelloworldClient2() {
        super("helloworld");
    }

    public GreeterGrpc.GreeterBlockingStub getGreeterBlockingStub(){
        return GreeterGrpc.newBlockingStub(super.getManagedChannel());
    }


}
