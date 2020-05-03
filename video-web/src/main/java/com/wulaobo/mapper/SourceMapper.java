package com.wulaobo.mapper;


import com.wulaobo.bean.Source;

import java.util.List;

public interface SourceMapper {

    boolean addSource(Source source);

    List<Source> getSourceList();

    boolean deleteSourceById(Integer id);

    Source getSourceById(Integer id);
}
