package com.rain.test.common;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class IMGUtils {
    /**
     * 将图像转为png文件的字节数据
     *
     * @param image 目标图像
     * @return 返回数据
     */
    public static byte[] getBytes(BufferedImage image) {
        return getBytes(image, "png");
    }


    /**
     * 将图像转换为指定媒体文件类型的字节数据
     *
     * @param image    目标图像
     * @param fileType 文件类型（后缀名）
     * @return 返回数据
     */
    public static byte[] getBytes(BufferedImage image, String fileType) {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, fileType, outStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outStream.toByteArray();
    }

    /**
     * 读取图像，通过文件
     *
     * @param filePath 文件路径
     * @return BufferedImage
     */
    public static BufferedImage getImage(String filePath) {
        try {
            return ImageIO.read(new FileInputStream(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取图像，通过字节数据
     *
     * @param data 字节数据
     * @return BufferedImage
     */
    public static BufferedImage getImage(byte[] data) {
        try {
            return ImageIO.read(new ByteArrayInputStream(data));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 保存图像到指定文件
     *
     * @param image    图像
     * @param filePath 文件路径
     */
    public static void saveImageToFile(BufferedImage image, String filePath) throws IOException {
        FileUtils.saveDataToFile(filePath, getBytes(image));
    }

    /**
     * 纵向拼接图片
     */
    public static BufferedImage verticalJoinTwoImage(BufferedImage image1, BufferedImage image2) {
        if (image1 == null) return image2;
        if (image2 == null) return image1;
        BufferedImage image = new BufferedImage(image1.getWidth(), image1.getHeight() + image2.getHeight(), image1.getType());
        Graphics2D g2d = image.createGraphics();
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.drawImage(image1, 0, 0, image1.getWidth(), image1.getHeight(), null);
        g2d.drawImage(image2, 0, image1.getHeight(), image2.getWidth(), image2.getHeight(), null);
        g2d.dispose();// 释放图形上下文使用的系统资源
        return image;
    }


    /**
     * 剪裁图片
     *
     * @param image  对象
     * @param x      顶点x
     * @param y      顶点y
     * @param width  宽度
     * @param height 高度
     * @return 剪裁后的对象
     */
    public static BufferedImage cutImage(BufferedImage image, int x, int y, int width, int height) {
        if (image == null) return null;
        return image.getSubimage(x, y, width, height);
    }

}
