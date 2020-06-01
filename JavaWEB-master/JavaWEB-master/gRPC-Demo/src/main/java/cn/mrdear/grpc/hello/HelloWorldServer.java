package cn.mrdear.grpc.hello;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.examples.helloworld.GreeterGrpc;
import io.grpc.examples.helloworld.HelloReply;
import io.grpc.examples.helloworld.HelloRequest;
import io.grpc.stub.StreamObserver;

/**
 * @author Niu Li
 * @date 2017/1/28
 */
public class HelloWorldServer {

    private int port = 50051;
    private Server server;

    /**
     * 启动服务
     */
    private void start() throws IOException {

        System.out.println("service start...");
        server = ServerBuilder.forPort(port)
                              .addService(new GreeterImpl())
                              .build()
                              .start();
      System.out.println("service started");

        //程序正常退出,系统调用 System.exit方法或虚拟机被关闭时执行该调用
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            HelloWorldServer.this.stop();
            System.err.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    // block 一直到退出程序
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }


    public static void main(String[] args) throws IOException, InterruptedException {
        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutdown();
    }


    // 实现 定义一个实现服务接口的类
    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
            //获取参数
            System.out.println("收到的信息:"+req.getName());

            //这里可以放置具体业务处理代码 start

            //这里可以放置具体业务处理代码 end

            //构造返回
            HelloReply reply = HelloReply.newBuilder().setMessage(("Hello: " + req.getName())).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }
}
