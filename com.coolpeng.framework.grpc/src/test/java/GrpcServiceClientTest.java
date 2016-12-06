import com.coolpeng.framework.grpc.client.GrpcServiceClient;

/**
 * Created by luanhaipeng on 16/11/1.
 */
public class GrpcServiceClientTest extends GrpcServiceClient{

    public void getddd(){

    }
    public void init(){
        super.init("cprs://127.0.0.1:3534/helloworld");
    }

}
