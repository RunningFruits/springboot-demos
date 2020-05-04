package com.matai.service.impl;

import com.matai.dao.IWebsiteDao;
import com.matai.entity.WebSite;
import com.matai.model.website;
import com.matai.service.IWebsiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsiteServiceImpl implements IWebsiteService {

    @Autowired
    private IWebsiteDao websiteDao;

    @Override
    public int insert(website record) {
        return websiteDao.insert(record);
    }

    @Override
    public List<WebSite> getList() {
        return websiteDao.getList();
    }
}
