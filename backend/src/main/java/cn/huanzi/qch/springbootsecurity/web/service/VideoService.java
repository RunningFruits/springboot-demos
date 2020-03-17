package cn.huanzi.qch.springbootsecurity.web.service;

import cn.huanzi.qch.springbootsecurity.web.pojo.Video;

import java.util.List;

public interface VideoService {

    public Video getVideoById(Integer id);

    boolean addVideo(Video video);

    public List<Video> getVideoList();

    public boolean deleteVideoById(Integer id);

}

