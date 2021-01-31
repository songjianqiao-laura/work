package aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AioDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
        final AsynchronousServerSocketChannel socketChannel = AsynchronousServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(8080));
        System.out.println("当前线程:"+Thread.currentThread().getName());
        socketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
            @Override
            public void completed(final AsynchronousSocketChannel asynchronousSocketChannel, Object attachment) {

                try {
                    socketChannel.accept(attachment, this);
                    System.out.println(asynchronousSocketChannel.getRemoteAddress());
                    System.out.println("当前线程:"+Thread.currentThread().getName());
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    asynchronousSocketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                        @Override
                        public void completed(Integer result, ByteBuffer attachment) {
                            attachment.flip();
                            System.out.println(new String(attachment.array(), 0, result));
                            System.out.println("当前线程:"+Thread.currentThread().getName());
                            asynchronousSocketChannel.write(ByteBuffer.wrap("HelloClient".getBytes()));
                        }

                        @Override
                        public void failed(Throwable exc, ByteBuffer attachment) {

                        }

                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }



            }

            @Override
            public void failed(Throwable exc, Object attachment) {

            }
        });

        Thread.sleep(Integer.MAX_VALUE);
    }

}
