package com.lzj;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description: TODO
 * @author: lzj
 * @date: 2021年05月07日 14:15
 */
public class NIODemo extends Thread {
    public void run() {
        try (Selector selector = Selector.open(); ServerSocketChannel serverSocket = ServerSocketChannel.open();) {// 创建Selector和Channel
            serverSocket.bind(new InetSocketAddress(InetAddress.getLocalHost(), 8888));
            serverSocket.configureBlocking(false);
            // 注册到Selector，并说明关注点
            serverSocket.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                selector.select();// 阻塞等待就绪的Channel，这是关键点之一
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> iter = selectedKeys.iterator();
                while (iter.hasNext()) {
                    SelectionKey key = iter.next();
                    // 生产系统中一般会额外进行就绪状态检查
                    sayHelloWorld((ServerSocketChannel) key.channel());
                    iter.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sayHelloWorld(ServerSocketChannel server) throws IOException {
        try (SocketChannel client = server.accept();) {
            ByteBuffer readBuffer = ByteBuffer.allocate(32);
            client.read(readBuffer);
            System.out.println("Server received : " + new String(readBuffer.array()));
            ByteBuffer writeBuffer = ByteBuffer.allocate(128);
            writeBuffer.put("hello xiaoming".getBytes());
            writeBuffer.flip();
            client.write(writeBuffer);
            //client.write(Charset.defaultCharset().encode("Hello world!"));
        }
    }

    public static void main(String[] args) throws IOException {
        NIODemo server = new NIODemo();
        server.start();

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(), 8888));

            ByteBuffer writeBuffer = ByteBuffer.allocate(32);
            ByteBuffer readBuffer = ByteBuffer.allocate(32);

            writeBuffer.put("hello".getBytes());
            writeBuffer.flip();
            while (true) {
                writeBuffer.rewind();
                socketChannel.write(writeBuffer);
//                readBuffer.clear();
                socketChannel.read(readBuffer);
                System.out.println("Client received : " + new String(readBuffer.array()));
            }
        } catch (IOException e) {
        }

    }

    /**
     * @return
     */
    private int getPort() {
        return 8888;
    }
}