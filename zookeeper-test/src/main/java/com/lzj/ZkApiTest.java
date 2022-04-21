package com.lzj;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.StandardCharsets;
import java.util.List;
public class ZkApiTest {
    public static void main(String[] args) throws Exception{
            //连接zookeeper
            ZooKeeper zooKeeper = new ZooKeeper("152.136.214.66:21810", 2000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    System.out.println("触发了" + event.getType() + "的事件");
                }
            });
//            //创建父节点
//            String path = zooKeeper.create("/appidea", "我是idea创建出来的父节点".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            System.out.println(path);
//            //创建子节点
//            String childrenpath = zooKeeper.create("/appidea/app1", "我是idea创建出来的子节点".getBytes(StandardCharsets.UTF_8), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//            System.out.println(childrenpath);
            //查看父节点数据
            byte[] data = zooKeeper.getData("/appidea", false, null);
            System.out.println(new String(data));
            //查看父节点下的子节点
            List<String> childrens = zooKeeper.getChildren("/appidea", false);
            for (String children : childrens) {
                System.out.println(children);
            }
            //修改节点的值
            Stat stat = zooKeeper.setData("/appidea","appideavalue".getBytes(),-1);
            System.out.println(stat);
            //判断节点是否存在
            Stat exists1 = zooKeeper.exists("/appidea/app1",false);
            System.out.println(exists1);
            Stat exists2 = zooKeeper.exists("/appidea/app2",false);
            System.out.println(exists2);
//            //删除节点
//            zooKeeper.delete("/appidea/app1",-1);
        }

}