package com.javacore;

import java.net.InetAddress;

/**
 * Created by Administrator on 17/7/23.
 */
public class InetAddressTest {
    public static void main(String[] args)throws Exception {
        args=new String[]{"www.baidu.com"};
        if (args.length>0){
            String host = args[0];
            InetAddress[] addr = InetAddress.getAllByName(host);
            for (InetAddress address : addr) {
                System.out.println( address);
            }
        }else{
            InetAddress local = InetAddress.getLocalHost();
            System.out.println(local);
        }
    }
}
