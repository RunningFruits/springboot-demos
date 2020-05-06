package com.code.demo.qiniulayui.service.impl;

import com.code.demo.qiniulayui.mapper.ImagesMapper;
import com.code.demo.qiniulayui.pojo.Images;
import com.code.demo.qiniulayui.service.ImagesService;
import com.code.demo.qiniulayui.util.DateUtil;
import com.code.demo.qiniulayui.util.IpUtil;
import com.code.demo.qiniulayui.util.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ImagesServiceImpl implements ImagesService {

    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    public void upload(MultipartFile file, HttpServletRequest request) throws IOException {
        String path = ResourceUtils.getURL("classpath:").getPath() + "/static/images";
        String ipAddr = IpUtil.getIpAddr(request);
        int i = file.getOriginalFilename().lastIndexOf(".");
        Long time = System.currentTimeMillis();
        String name = time + file.getOriginalFilename().substring(i);
        File file2 = new File(path);
        if (!file2.exists()) {
            file2.mkdir();
        }
        File tmpFile = new File(path + "/" + name);
        file.transferTo(tmpFile);
        QiniuUtil.upload(tmpFile.getPath(), name);
        Images images = new Images();
        images.setIp(ipAddr);
        images.setName(name);
        images.setSize(tmpFile.length() / 1024);
        images.setTime(time);
        tmpFile.delete();
        imagesMapper.insert(images);
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        Example example = new Example(Images.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("id", ids);
        imagesMapper.deleteByExample(example);
    }

    @Override
    public void query(Model model) {
        List<Images> list = imagesMapper.selectAll();
        for (Images images : list) {
            images.setVTime(DateUtil.format(images.getTime()));
        }
        Collections.sort(list, new Comparator<Images>() {
            @Override
            public int compare(Images o1, Images o2) {
                return (int) (o2.getTime() - o1.getTime());
            }
        });
        model.addAttribute("images", list);
    }
}
