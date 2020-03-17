package cn.huanzi.qch.springbootsecurity.web.service.impl;


import cn.huanzi.qch.springbootsecurity.web.pojo.Video;
import cn.huanzi.qch.springbootsecurity.web.repository.VideoDao;
import cn.huanzi.qch.springbootsecurity.web.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao videoDao;

    @Override
    public Video getVideoById(Integer id) {
        return videoDao.getVideoById(id);
    }

    @Override
    public boolean addVideo(Video video) {
        Video save = videoDao.save(video);
        if (null == save) {
            return true;
        }
        return false;
    }

    @Override
    public List<Video> getVideoList() {
        return videoDao.getVideoList();
    }

    @Override
    public boolean deleteVideoById(Integer id) {
        videoDao.deleteById((long) id);
        return true;
    }

}

