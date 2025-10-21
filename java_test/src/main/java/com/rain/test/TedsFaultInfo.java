package com.rain.test;

import lombok.Data;

import java.util.List;

@Data
public class TedsFaultInfo {

    private static final long serialVersionUID = 1L;
    private String path;  //故障图像的存储路径。

    private List<TedsPartFaultImgInfo> fault_info;

    private Integer defect_count;  //该张图片检测到的故障数量。

    private String part;//  工位信息，不区分，默认都是 crh_detect。

    private String vehicle_type;  //车型，若请求没有传入则为空字符串。
}
