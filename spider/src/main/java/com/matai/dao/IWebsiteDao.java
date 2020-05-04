package com.matai.dao;

import com.matai.entity.WebSite;
import com.matai.model.website;

import java.util.List;

public interface IWebsiteDao {
    int deleteByPrimaryKey(Integer id);

    int insert(website record);

    int insertSelective(website record);

    website selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(website record);

    int updateByPrimaryKey(website record);

    List<WebSite> getList();
}
