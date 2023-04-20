package com.rain.test.tool.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;

/**
 * 仅仅测试网络通不通
 *
 */
public class NetUtils {

    private static Logger logger = LoggerFactory.getLogger(NetUtils.class);


    public static boolean sendPingRequest(String ipAddress) throws IOException {
        InetAddress geek = InetAddress.getByName(ipAddress);
        logger.info("Sending Ping Request to " + ipAddress);
        return geek.isReachable(5000);
    }


    public static void main(String[] args) throws Exception {

        sendPingRequest("192.168.99.132");


    }
}