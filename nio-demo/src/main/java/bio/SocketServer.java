package bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws IOException {
        final ServerSocket serverSocket = new ServerSocket(8080);
        //接受服务端数据
        while(true){
            final Socket accept = serverSocket.accept();

            System.out.println("有客户端连接了");
            new Thread(new Runnable() {
                public void run() {
                    try {
                        handel(accept);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }


    public static void handel(Socket socket) throws IOException {
        byte[] bytes = new byte[1024];

        int read = socket.getInputStream().read(bytes);

        if (read != -1){
            System.out.println("接收客户端数据"+new String(bytes, 0, read));
        }

        socket.getOutputStream().write("HelloClient".getBytes());
        socket.getOutputStream().flush();
    }


}
