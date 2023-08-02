package com.rain;


public class DeviceProtocol {
	private String headNode = ConstantValue.HEAD_NODE;// 头结点

    public void setVersion(String version) {
        this.version = version;
    }

    private String version;// 协议版本号

	private String cmd;// 命令标识



	private String retain = ConstantValue.RETAIN;// 保留字段

	private int dataLength;// 长度

	private String msgData;

	public String getHeadNode() {
		return headNode;
	}

	public String getVersion() {
		return version;
	}

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	@Override
	public String toString() {
		return "DeviceProtocol [headNode=" + headNode + ", version=" + version + ", cmd=" + cmd
				+ ", retain=" + retain + ", dataLength=" + dataLength + ", msgData=" + msgData + "]";
	}





	public String getRetain() {
		return retain;
	}

	public void setRetain(String retain){
	    this.retain = retain;
    }


	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public String getMsgData() {
		return msgData;
	}

	public void setMsgData(String msgData) {
		this.msgData = msgData;
	}

	public byte[] toBytes() {
		StringBuilder str = new StringBuilder();
		str.append(headNode);
		str.append(version);
		str.append(cmd);
		str.append(retain);
		str.append(String.format("%04x", msgData.getBytes().length));
		str.append(CodeUtils.str2HexStr(msgData));
		return CodeUtils.hexTobytes(str.toString());
	}
}
