package cn.huanzi.qch.springbootsecurity.web.controller;


import cn.huanzi.qch.springbootsecurity.util.FastDFSClient;
import cn.huanzi.qch.springbootsecurity.web.pojo.Video;
import cn.huanzi.qch.springbootsecurity.web.service.VideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Random;


@Controller
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @PostMapping(value = "/videoUpload")
    public String videoUpload(String title, MultipartFile file, HttpServletRequest request) throws IOException {
        Video video = new Video();

        String str = FastDFSClient.uploadFile(file);
        String filepath = FastDFSClient.getResAccessUrl(str);

        String videoName = file.getOriginalFilename();  //获取上传后的文件名
        video.setPath(filepath);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        video.setUploadTime(timestamp);
        video.setTitle(title);
        video.setSize(this.getSize(file.getSize()));
        video.setType(this.getFileExt(videoName));
        boolean result = videoService.addVideo(video);
        if (result) {
            return "videoPlay/uploadSuccess";
        }

        return "videoPlay/failed";
    }

    //管理员
    @GetMapping(value = "/getVideoList")
    public String getVideoList(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> videoList = videoService.getVideoList();

        PageInfo pageInfo = new PageInfo(videoList);
        model.addAttribute("pageInfo", pageInfo);
        return "videoPlay/admin/videoList";
    }

    //普通用户
    @GetMapping(value = "/getVideoListByUser")
    public String getVideoListByUser(@RequestParam(value = "pageNum", defaultValue = "1") Integer num, ModelMap model) {
        PageHelper.startPage(num, 5);
        PageHelper.orderBy("uploadTime desc");
        List<Video> videoList = videoService.getVideoList();

        PageInfo pageInfo = new PageInfo(videoList);
        model.addAttribute("pageInfo", pageInfo);
        return "videoPlay/frontPage/videoList";
    }


    //管理员点击播放按钮，开始播放视频
    @GetMapping(value = "/videoPlayByIdAndAdmin")
    public String videoPlayByIdAndAdmin(Integer id, ModelMap model) {
        Video video = videoService.getVideoById(id);
        model.addAttribute("title", video.getTitle());
        model.addAttribute("path", video.getPath());
        return "videoPlay/videoPlay";
    }

    @GetMapping(value = "/deleteVideoById")
    public String deleteVideoById(Integer id, ModelMap model) {
        Video video = videoService.getVideoById(id);
        if (video != null) {
            try {
                boolean result = FastDFSClient.deleteFile(video.getPath());
                if (result) {
                    videoService.deleteVideoById(id);
                    return "forward:/video/getVideoList";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "videoPlay/failed";
        }
        return "videoPlay/failed";
    }

    @GetMapping(value = "/downloadVideoById")
    public String downloadVideoById(Integer id, ModelMap model) throws IOException {

        Video video = videoService.getVideoById(id);
        File file = new File("D:\\down\\admin\\" + video.getTitle() + ".mp4");
        boolean result = FastDFSClient.downloadFile(video.getPath(), file);
        if (result) {
            System.out.println("下载文件：" + file.getName() + " 成功");
            model.addAttribute("msg", "下载成功");
        } else {
            System.out.println("下载失败！");
            model.addAttribute("msg", "下载失败");
        }
        return "forward:/video/getVideoListByUser";
    }


    /**
     * 获取文件扩展名
     *
     * @return string
     */
    private String getFileExt(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 依据原始文件名生成新文件名
     *
     * @return
     */
    private String getName(String fileName) {
        Random random = new Random();
        return "" + random.nextInt(10000) + System.currentTimeMillis();
    }

    /**
     * 文件大小，返回kb.mb
     *
     * @return
     */
    private String getSize(long fileLength) {
        String size = "";
        DecimalFormat df = new DecimalFormat("#.00");
        if (fileLength < 1024) {
            size = df.format((double) fileLength) + "BT";
        } else if (fileLength < 1048576) {
            size = df.format((double) fileLength / 1024) + "KB";
        } else if (fileLength < 1073741824) {
            size = df.format((double) fileLength / (1024 * 1024)) + "MB";
        } else {
            size = df.format((double) fileLength / (1024 * 1024 * 1024)) + "GB";
        }
        return size;
    }
}

