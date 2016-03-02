package com.wch.learning.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class TCPEchoServer {

    private static final int BUFSIZE = 32;

    @SuppressWarnings("resource")
	public static void main(String[] args) throws IOException {

       int servPort = 8888;
       ServerSocket servSocket = new ServerSocket(servPort);

 
       int recvMsgSize = 0;
       byte [] recvBuf = new byte [ BUFSIZE ];

       while ( true ) {

           Socket clntSocket = servSocket.accept(); // 该方法会阻塞
           SocketAddress clientAddress =
                                clntSocket.getRemoteSocketAddress();
           System. out .println( "Handling client at " + clientAddress);

           InputStream in = clntSocket.getInputStream();
           OutputStream out = clntSocket.getOutputStream();

           while ((recvMsgSize = in.read(recvBuf)) != -1) {
              out.write(recvBuf, 0, recvMsgSize);
           }

           clntSocket.close();
       }
    }
}