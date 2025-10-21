package com.rain.test;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 一个mark result 对应一张局部故障图
 * </p>
 *
 * @author lei chong
 * @since 2024-04-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TedsPartFaultImgInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long markBatchId;
    //0 所有故障图  1 单故障图
    private int markType;
    //0横向 1纵向
    private String direction;
    private long left;
    private long orignLeft;
    private long top;
    private long orignTop;
    private long width;

    private long height;

    private String localFaultImgName;

    private String localFaultImgPath;

//    @JsonProperty(value = "defect_name")
    private String defectName;
    /**
     * 车型
     */
//    @Excel(name = "车型")
    private String vehicleType;
    /**
     *   转向架 0 1
     */
//    @Excel(name = "转向架", readConverterExp = "0=非转向架,1=转向架")
    private Integer bogie;
    //    @Excel(name = "故障编码")
//    @JsonProperty(value = "defect_code")
//    private String defectCode;
//    @JsonProperty(value = "defect_area")
    private String defectArea;
    //    @Excel(name = "置信度")
    private float confidence;
    //    @Excel(name = "图片名称")
    private String imageName;
    //    @Excel(name = "图片路径")
    @JsonProperty(value = "markPath")
    private String mergeImgPath;


    private String imageBatch;

    private int vehicleOrder;

    private String cameraNo;
    /**
     * 故障确认状态
     */
//    @Excel(name = "故障确认状态", readConverterExp = "0=属实,1=误报,2=漏报")
    private Long affirmState;

    private String remark;

    /**
     * 故障等级
     */
//    @Excel(name = "故障等级", readConverterExp = "0=A类,1=B类,2=C类,-1=未知")
    private Long defectLevel;
    //    @Excel(name = "识别时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
//    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    //    @Excel(name = "检查结果", readConverterExp = "0=属实,1=误报,2=漏报")
    private String checkResult;
    /**
     * 判别人
     */
//    @Excel(name = "判别人")
    private String affirmBy;
    /**
     * 判别时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//    @Excel(name = "判别时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date affirmTime;
    /**
     * 故障识别工位
     */
//    @Excel(name = "故障识别工位", readConverterExp = "vehicleBodyBottom=车侧/车底,vehicleBottom=车底,vehicleBody=车侧")
    private String affirmStation;
    /**
     * 创建者
     */
    private String createBy;

    /**
     * 测试名称
     */
    private String testName;

    /**
     * 故障类型
     */
    private Integer faultType;
    /**
     * 图片ID
     */
    private Long imageId;


    @JsonProperty(value = "fault_serial")
    private String faultSerial;
//    故障严重程度等级, 0（严重，需弹框） 1（中等）
//
//            2（轻微），不需要分等级的设置成-1（作业平台
//    前端代码在读到服务端返回的ponderance为-1 时
//    候，通过读取不同路局对应的故障配置文件显示）。
//    默认都为-1。
    private Integer ponderance;
    @JsonProperty(value = "detection_confidence")
    private Float detectionConfidence;
    private Object confidence_list;


    /**
     * 故障来源
     */
    private Integer faultCategory;

    /**
     * 故障删除标记位
     */
    private Integer deleteFlag;
    private Integer testImageId;


}
