package com.rain;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConstantValue {
	/**
	 * 头结点55AA
	 */
	public static final String HEAD_NODE= "55aa";
	/**
	 * 协议版本号
	 */
	public static final String VERSION_00= "00";
    public static final String VERSION_01= "01";
    public static final String VERSION_02= "02";
    /**
	 * 保留字段
	 */
	public static final String RETAIN = "000000000000";
	/**
	 * |2B头结点|1B协议版本|1B指令|8BDevCode|4B保留|2B保留|2B数据长度|
	 */
	public static final int BASE_LENGTH = 2+1+1+8+4+2+2;//报文头长度
	/**
	 * 命令标识类型 0x01:标识注册“reg” 0x02:标识数据同步请求“sd” 0x03:标识数据同步完成确认“com” 0x04:标识清空硬盘“dc”
	 * 0x05:标识固件升级请求“fwup” 0x06:标识固件升级完成确认“fwupcom” 0x07:标识心跳“h”
	 */
	public static final String CMD_REG = "01";
	public static final String CMD_SD = "02";
	public static final String CMD_COM = "03";
	public static final String CMD_DC = "04";
	public static final String CMD_FWUP = "05";
	public static final String CMD_FWUPCOM = "06";
	public static final String CMD_HEART = "07";
    public static final String CMD_POWER = "08";
    /**
     * 网关配置参数
     * 1-maxBlockSize 网关上传文件分组最大大小,单位GB
	 * 2-minRouteTime 机车最小交路时间,单位分钟
	 */
	public static final String PARAM_1 = "maxBlockSize";
	public static final String PARAM_2 = "minRouteTime";
	/**
	 * 网关通讯流水号map
	 */
	public static Map<String, String> transMap = new ConcurrentHashMap<String, String>();

	public static Map<String, ChannelHandlerContext> channelMap = new ConcurrentHashMap<>();

	/**
	 * 网关侧块数据丢失，无需再断点续传；
	 */
	public static final String BLOCK_MISSED = "block_missed";
	/**
	 * 网关侧块数据正在传输中
	 */
	public static final String BLOCK_UPLOADING = "uploading";

}
