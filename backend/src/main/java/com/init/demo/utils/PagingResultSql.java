package com.init.demo.utils;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Iterator;
import java.util.List;

@Data
public class PagingResultSql<T> {

    private long count;
    private int code;
    private List<T> data;

    public PagingResultSql(List<T> data, long count) {
        this.data = data;
        this.count = count;
    }

    public PagingResultSql() {

    }


}
