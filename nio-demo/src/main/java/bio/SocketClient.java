package bio;

import java.io.IOException;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8080);
        socket.getOutputStream().write("hello server".getBytes());
        socket.getOutputStream().flush();
        System.out.println("像服务端发送数据");
        byte[] bytes = new byte[1024];
        socket.getInputStream().read(bytes);
        System.out.println("接收回传数据"+new String(bytes));
        socket.close();
    }

}
