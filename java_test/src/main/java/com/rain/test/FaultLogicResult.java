package com.rain.test;

import lombok.Data;

import java.util.List;

@Data
public class FaultLogicResult {
    private static final long serialVersionUID = 1L;

    private List<TedsFaultInfo> faults;

    private String car_code;//车辆号

    private int car_seq; //车辆序号

    private int image_height;  //图片像素高

    private int image_width; //图片像素宽

    private String pass_time; //过车时间

    private String pass_way_code; //部位序号

    private String station_code; //探测站编码

    private String train_set_no; //标准车组号

    private String train_set_way_id;//列车 ID

    private int vehicle_number;//车辆总数

    private String vehicle_type;// 车型

    private String way_channel_id;//部位 ID

    private String receive_image_time;

    private String send_fault_time;

    private String ai_start_time;

    private String ai_end_time;
}
