package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioNewServer {

    public static void main(String[] args) throws  Exception{
        //创建一个epoll对象，采用linux的epoll_create
        ServerSocketChannel socketChannel = ServerSocketChannel.open();
        socketChannel.bind(new InetSocketAddress(8080));

        socketChannel.configureBlocking(false);
        //注册
        Selector selector = Selector.open();
        //这里还没有真正的注册，只是将所有的SocketChannel放入一个内部的集合中
        SelectionKey register = socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动成功");

        while (true){
            //调用epoll_ctl方法将fd(linux为了高效管理文件所设的索引)和event进行时间绑定。
            //当socket接收到数据，然后通过Linux的中断机制调用回调函数，为epoll的就绪事件列表rdList中添加socket的引用
            //然后epoll_wait方法则是去rdList中拿socket引用，如果有则返回，没有则阻塞
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();
                if (next.isAcceptable()){
                    //如果是连接事件，注册读
                    ServerSocketChannel channel = (ServerSocketChannel) next.channel();
                    SocketChannel accept = channel.accept();
                    accept.configureBlocking(false);
                    accept.register(selector, SelectionKey.OP_READ);
                }else if (next.isReadable()){
                    SocketChannel channel = (SocketChannel) next.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    int read = channel.read(byteBuffer);
                    if (read > 0){
                        System.out.println("客户端读取数据"+new String(byteBuffer.array()));
                    }else if (read == -1){
                        channel.close();
                    }
                }
                iterator.remove();
            }
        }
    }


}
