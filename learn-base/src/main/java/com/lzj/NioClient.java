package com.lzj;

/**
 * @Description: TODO
 * @author: lzj
 * @date: 2021年05月07日 15:06
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NioClient {
    public static void main(String[] args) throws Exception {
        MyThread t1 = new MyThread(8086);
        MyThread t2 = new MyThread(8087);
        new Thread(t1,"线程1").start();
        new Thread(t2,"线程2").start();
    }
      static class MyThread implements Runnable {
        private int i = 0;
        private int port ;
          public MyThread(int i) {
              this.port=i;
          }

          @Override
        public void run() {
            try {
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.connect(new

                        InetSocketAddress("127.0.0.1", port));

                ByteBuffer buf = ByteBuffer.allocate(50);

                boolean flag = true;

                while (flag) {
                    buf.clear();
                    String input = "Input something:";
//            if (input.equalsIgnoreCase("bye")) {
//                flag = false;
//            }
                    if (i < 10) {
                        i++;
                    } else {
                        flag = false;
                    }
                    input += "\n";
                    buf.put(input.getBytes());
                    buf.flip();
                    socketChannel.write(buf);
                    System.out.println("第" + i + "次传输数据,"+Thread.currentThread().getName()+"使用端口号:"+port);
                    buf.clear();
                    int read = socketChannel.read(buf);
                    String readMessage = new String(buf.array(), 0, read);
                    System.out.println("我下面有回复信息了吗"+Thread.currentThread().getName()+"使用端口号:"+port);
                    System.out.println("Read resp - " + readMessage);
                }
                socketChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

