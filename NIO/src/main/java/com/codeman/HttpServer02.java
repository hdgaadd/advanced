package com.codeman;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hdgaadd
 * Created on 2022/02/18
 * access : http://localhost:8080
 * 压测工具SuperBenchmarker : sb -u http://localhost:8080/ -c 40 -N 30
 */
public class HttpServer02 { // RPS: 1313.7 (requests/second) ，系统每秒能接受1313个请求
    public static void main(String[] args) throws IOException {
        ExecutorService exec = Executors.newFixedThreadPool(40);
        final ServerSocket serverSocket = new ServerSocket(8080);

        while (true) {
            try {
                final Socket socket = serverSocket.accept();
                exec.execute(() -> handle(socket));
            } catch (Exception e) {
                e.printStackTrace(); // 打印异常信息
            }
        }
    }

    private static void handle(Socket socket) {
        try {
            Thread.sleep(20);

            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("HTTP/1.1 200 OK");
            printWriter.println("Content-Type:text/html;charset=utf-8");
            printWriter.println();
            printWriter.write("this is NIO");
            printWriter.close();
            socket.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
