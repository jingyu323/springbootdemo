package com.rain.test;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.*;

public class ImageProcessor {
    private static final Logger logger = LoggerFactory.getLogger(ImageProcessor.class);

    public BufferedImage read(String fileName, String imgRootPath) {
        File file = new File(fileName);
        if (!file.exists()) {
            logger.warn("not found: " + fileName);
            if (!imgRootPath.isEmpty())
                file = new File(imgRootPath + "noimage.jpeg");
        }
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(file);
        } catch (IOException e) {
            throw new RuntimeException("read error: " + fileName, e);
        }
        return bi;
    }

    public BufferedImage read(InputStream is) {
        if (is == null)
            return null;
        BufferedImage bi = null;
        try {
            bi = ImageIO.read(is);
        } catch (IOException e) {
            throw new RuntimeException("read error: ", e);
        }
        return bi;
    }

    public void write(BufferedImage image, String fileName) {
        if (image == null) {
            logger.warn("no image data: " + fileName);
            return;
        }
        String extName = getExtName(fileName);
        File file = new File(fileName);
        try {
            ImageIO.write(image, extName, file);
        } catch (IOException e) {
            throw new RuntimeException("write error: " + fileName, e);
        }
    }

    public void write(byte[] imageData, String fileName) {
        BufferedImage image = getBufferedImage(imageData);
        write(image, fileName);
    }

    public byte[] getBytes(BufferedImage image) {
        if (image == null)
            return null;
        byte[] data = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", baos);
            baos.flush();
            data = baos.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException("convert error", e);
        } finally {
            try {
                baos.close();
            } catch (Exception exception) {}
        }
        return data;
    }

    public BufferedImage getBufferedImage(byte[] imageData) {
        if (imageData == null)
            return null;
        BufferedImage image = null;
        InputStream in = new ByteArrayInputStream(imageData);
        try {
            image = ImageIO.read(in);
        } catch (IOException e) {
            throw new RuntimeException("convert error", e);
        } finally {
            try {
                in.close();
            } catch (Exception exception) {}
        }
        return image;
    }

    public BufferedImage clip(BufferedImage image, int x, int y, int width, int height) {
        return clip(image, x, y, width, height, false);
    }

    public BufferedImage clip(BufferedImage image, int x, int y, int width, int height, boolean flipY) {
        if (image == null)
            return null;
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        if (x < 0)
            x = 0;
        if (y < 0)
            y = 0;
        if (x + width > imageWidth)
            width = imageWidth - x;
        if (y + height > imageHeight)
            height = imageHeight - y;
        if (x == 0 && y == 0 && width == imageWidth && height == imageHeight)
            return image;
        if (flipY == true) {
            int endY = imageHeight - y;
            int startY = imageHeight - y + height;
            y = startY;
            height = endY - startY;
        }
        BufferedImage dest = image.getSubimage(x, y, width, height);
        return dest;
    }

    public static BufferedImage scale(BufferedImage image, double destWidth, double destHeight) {
        return scale(image, destWidth, destHeight, false);
    }

    public static BufferedImage drawRect(BufferedImage image, int x, int y, int width, int height) {
        BufferedImage destImage = new BufferedImage(image.getWidth(), image.getHeight(), 1);
        Graphics g = destImage.getGraphics();
        g.drawImage(image, 0, 0, null);
        Graphics g1 = destImage.getGraphics();
        g1.setColor(Color.red);
        Graphics2D g2 = (Graphics2D)g1;
        g2.setStroke(new BasicStroke(10.0F));
        g1.drawRect(x, y, width, height);
        return image;
    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight) throws IOException {
        Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight, 16);
        BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight, 1);
        outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
        return outputImage;
    }

    public static BufferedImage scale(BufferedImage image, double destWidth, double destHeight, boolean force) {
        if (image == null)
            return null;
        int width = image.getWidth();
        int height = image.getHeight();
        if (destWidth >= width && destHeight >= height && !force)
            return image;
        if (width == (int)destWidth && height == (int)destHeight && !force)
            return image;
        double sx = destWidth / width;
        double sy = destHeight / height;
        if (sx <= 0.0D && !force)
            sx = 1.0D;
        if (sy <= 0.0D && !force)
            sy = 1.0D;
        AffineTransform transform = AffineTransform.getScaleInstance(sx, sy);
        AffineTransformOp op = new AffineTransformOp(transform, 1);
        BufferedImage bi2 = null;
        try {
            bi2 = new BufferedImage((int)destWidth, (int)destHeight, 1);
            return op.filter(image, bi2);
        } catch (Exception e) {
            logger.info("图片缩放失败，错误消息是："+ e.getMessage());
                    bi2 = new BufferedImage((int)destWidth, (int)destHeight, 10);
            return op.filter(image, bi2);
        }
    }

    public BufferedImage flip(BufferedImage image, boolean isHorz, boolean isVert) {
        AffineTransform tx;
        if (image == null)
            return null;
        if (!isHorz && !isVert)
            return image;
        if (!isHorz && isVert == true) {
            tx = AffineTransform.getScaleInstance(1.0D, -1.0D);
            tx.translate(0.0D, -image.getHeight());
        } else if (isHorz == true && !isVert) {
            tx = AffineTransform.getScaleInstance(-1.0D, 1.0D);
            tx.translate(-image.getWidth(), 0.0D);
        } else {
            tx = AffineTransform.getScaleInstance(-1.0D, -1.0D);
            tx.translate(-image.getWidth(), -image.getHeight());
        }
        AffineTransformOp op = new AffineTransformOp(tx, 1);
        return op.filter(image, (BufferedImage)null);
    }

    public static BufferedImage rotate(BufferedImage image, int angle) {
        if (image == null)
            return null;
        if (angle % 360 == 0)
            return image;
        int width = image.getWidth();
        int height = image.getHeight();
        AffineTransform transform = new AffineTransform();
        if (angle == 180 || angle == -180) {
            transform.translate(width, height);
        } else if (angle == 270 || angle == -90) {
            angle = 90;
            transform.translate(height, 0.0D);
        } else if (angle == 90 || angle == -270) {
            angle = 270;
            transform.translate(0.0D, width);
        } else {
            throw new IllegalArgumentException("only 0/90/180/270supported: " + angle);
        }
        transform.rotate(Math.toRadians(angle));
        AffineTransformOp op = new AffineTransformOp(transform, 1);
        return op.filter(image, (BufferedImage)null);
    }

    private String getExtName(String fileName) {
        String extName = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extName;
    }

    public void convertFormat(String srcFile, String destFile) {
        File f = new File(srcFile);
        try {
            BufferedImage src = ImageIO.read(f);
            String extName = getExtName(destFile);
            ImageIO.write(src, extName, new File(destFile));
        } catch (Exception e) {
            throw new RuntimeException("convert error: " + srcFile, e);
        }
    }

    public byte[] readByteArray(String filename) {
        File f = new File(filename);
        if (!f.exists())
            return null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());
        BufferedInputStream in = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            in = new BufferedInputStream(fs);
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size)))
                bos.write(buffer, 0, len);
            return bos.toByteArray();
        } catch (IOException e) {
            logger.error("读文件失败："+ e.getMessage());
            throw new RuntimeException(e);
        } finally {
            try {
                if (fs != null)
                    fs.close();
                if (in != null)
                    in.close();
                bos.close();
            } catch (IOException iOException) {}
        }
    }
}