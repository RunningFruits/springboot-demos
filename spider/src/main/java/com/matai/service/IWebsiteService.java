package com.matai.service;

import com.matai.entity.WebSite;
import com.matai.model.website;

import java.util.List;

public interface IWebsiteService {

    int insert(website record);

    List<WebSite> getList();
}
