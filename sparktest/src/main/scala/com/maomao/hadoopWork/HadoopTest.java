package com.maomao.hadoopWork;

import org.apache.hadoop.hdfs.HAUtil;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class HadoopTest {

    public String getActiveNameNode() throws IOException {
        InetSocketAddress active = HAUtil.getAddressOfActive();
        InetAddress address = active.getAddress();
        return "hdfs://"+address.getHostAddress()+":"+active.getPort();
    }

}
