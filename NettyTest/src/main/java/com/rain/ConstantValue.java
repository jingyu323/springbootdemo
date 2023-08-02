package com.rain;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConstantValue {

	public static final String HEAD_NODE= "55aa";
	/**
	 * 协议版本号
	 */

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



}
