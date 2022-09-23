package com.rain.test.tool;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.InetAddress;

/**
 * 采集网络带宽使用率
 *
 * ****************************
 * cat /proc/net/dev
 * Inter-|   Receive                                                |  Transmit
 *  face |bytes    packets errs drop fifo frame compressed multicast|bytes    packets errs drop fifo colls carrier compressed
 *    lo: 341566047 4020765    0    0    0     0          0         0 341566047 4020765    0    0    0     0       0          0
 * enp0s31f6: 7222870592 9003564    0    0    0     0          0    125359 680013336 4352932    0    0    0     0       0          0
 *
 * ****************************
 * ethtool enp0s31f6
 * Settings for enp0s31f6:
 *  Supported ports: [ TP ]
 *  Supported link modes:   10baseT/Half 10baseT/Full
 *                          100baseT/Half 100baseT/Full
 *                          1000baseT/Full
 *  Supported pause frame use: No
 *  Supports auto-negotiation: Yes
 *  Advertised link modes:  10baseT/Half 10baseT/Full
 *                          100baseT/Half 100baseT/Full
 *                          1000baseT/Full
 *  Advertised pause frame use: No
 *  Advertised auto-negotiation: Yes
 *  Speed: 1000Mb/s
 *  Duplex: Full
 *  Port: Twisted Pair
 *  PHYAD: 1
 *  Transceiver: internal
 *  Auto-negotiation: on
 *  MDI-X: on (auto)
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