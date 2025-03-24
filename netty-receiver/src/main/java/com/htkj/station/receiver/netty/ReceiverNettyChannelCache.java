package com.htkj.station.receiver.netty;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReceiverNettyChannelCache {
    public static volatile Map<String, ReceiverNettyChannel> channelMapCache = new ConcurrentHashMap<>();

    public static void add(String code, ReceiverNettyChannel channel) {
        channelMapCache.put(code, channel);
    }

    public static ReceiverNettyChannel get(String code) {
        return channelMapCache.get(code);
    }

    public static void remove(String code) {
        channelMapCache.remove(code);
    }

    public static void save(String code, ReceiverNettyChannel channel) {
        if (channelMapCache.get(code) == null)
            add(code, channel);
    }
}
