package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NioOldServer {

    static List<SocketChannel> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));
        serverSocketChannel.configureBlocking(false);
        while(true){
            SocketChannel accept = serverSocketChannel.accept();
            //将serverSocket放入list中
            if (accept != null){
                list.add(accept);
            }
            Iterator<SocketChannel> iterator = list.iterator();
            while(iterator.hasNext()){
                SocketChannel next = iterator.next();
                next.configureBlocking(false);
                ByteBuffer byteBuffer = ByteBuffer.allocate(126);
                int read = next.read(byteBuffer);
                if (read > 0){
                    System.out.println("接收到数据"+new String(byteBuffer.array()));
                }else if(read == -1){
                    iterator.remove();
                }
            }
        }
    }

}
