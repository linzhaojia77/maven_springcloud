package com.lzj;

/**
 * @Description: TODO
 * @author: lzj
 * @date: 2021年05月07日 15:04
 */


import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NioServer {

    private static class TaskHandler implements Runnable {
        private SocketChannel clientChannel;
        public TaskHandler(SocketChannel clientChannel) {
            this.clientChannel = clientChannel;
        }


        @Override
        public void run() {
            ByteBuffer buf = ByteBuffer.allocate(500);
            try {
                boolean flag = true;
                while (flag) {
                    buf.clear();
                    int read = clientChannel.read(buf);
                    String readMessage = new String(buf.array(), 0, read+1);
                    String writeMessage = "[Echo] " + readMessage + "\n";
                    if (read < 15) {
                        flag = false;
                        System.out.println("通道关闭");
                    }
                    // 写返回数据
                    buf.clear();
                    buf.put(writeMessage.getBytes());
                    buf.flip(); // 重置缓冲区让其输出
                    clientChannel.write(buf);

                }
                clientChannel.close();

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newFixedThreadPool(10);

        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocketChannel serverSocketChannel1 = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel1.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8087));
        serverSocketChannel1.bind(new InetSocketAddress(8086));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        serverSocketChannel1.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务启动在-" + 8087+"和"+8086);

        while (selector.select() > 0) {
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = keys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if (key.isAcceptable()) {
                    SocketChannel socketChannel1 = serverSocketChannel1.accept();
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    if ( socketChannel1 !=null) {
                        // 提交一个任务去处理
                        es.submit(new TaskHandler(socketChannel1));
                    }
                    if ( socketChannel1 !=null) {
                        es.submit(new TaskHandler(socketChannel));
                    }
                }
            }
        }

    }
}
