package com.example.fastdfs.controller;


import com.example.fastdfs.utils.fastdfs.FastDFSClient;
import com.example.fastdfs.utils.fastdfs.FastDFSFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;

@Controller
@RequestMapping("fdfs")
public class FastDFSController {
    private static Logger logger = LoggerFactory.getLogger(FastDFSController.class);

    @GetMapping("")
    public String index() {
        return "fdfs/upload";
    }

    @PostMapping("/upload") //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "请选择一个文件上传！");
            return "redirect:fdfs/uploadStatus";
        }
        try {
            // Get the file and save it somewhere
            String path = saveFile(file);
            redirectAttributes.addFlashAttribute("message", "成功上传： '" + file.getOriginalFilename() + "'");
            redirectAttributes.addFlashAttribute("path", "文件路径 url '" + path + "'");
        } catch (Exception e) {
            logger.error("上传文件失败！", e);
        }
        return "redirect:fdfs/uploadStatus";
    }

    @GetMapping("/uploadStatus")
    public String uploadStatus() {
        return "fdfs/uploadStatus";
    }

    /**
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        if (inputStream != null) {
            int len1 = inputStream.available();
            file_buff = new byte[len1];
            inputStream.read(file_buff);
        }
        inputStream.close();
        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //上传到 fastdfs
        } catch (Exception e) {
            logger.error("上传文件异常!", e);
        }
        if (fileAbsolutePath == null) {
            logger.error("上传文件失败,请重试!");
        }
        String path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + "/" + fileAbsolutePath[1];
        return path;
    }


}
