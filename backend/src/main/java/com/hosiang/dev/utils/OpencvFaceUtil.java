package com.hosiang.dev.utils;


import org.apache.commons.io.IOUtils;
import org.opencv.core.*;
import org.opencv.highgui.HighGui;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;


import java.io.File;

public class OpencvFaceUtil {


    // 初始化人脸探测器
    static CascadeClassifier faceDetector;

    static final File xmlFile = new File("src/main/resources/opencv/haarcascade_frontalface_alt.xml");
    private static final String xmlFilePath = xmlFile.getAbsolutePath();

    static final File dllFile = new File("src/main/resources/opencv/dll/opencv_java411.dll");

    static {
        System.load(dllFile.getAbsolutePath());//绝对路径
        faceDetector = new CascadeClassifier(xmlFilePath);
    }

    //判断图片中是否是一张人脸
    public static boolean isAPersonFace(String imgPath) {
        // 2 读取测试图片
        Mat image = Imgcodecs.imread(imgPath);
        if (image.empty()) {
            return false;
        }
        // 3 特征匹配
        MatOfRect face = new MatOfRect();
        faceDetector.detectMultiScale(image, face);
        // 4 匹配 Rect 矩阵 数组
        Rect[] rects = face.toArray();
        if (rects.length == 1) {
            return true;
        } else {
            return false;
        }
    }

}
