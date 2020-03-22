package com.init.demo.utils;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.Iterator;

@Data
public class PagingResult<T> {

    private long count;
    private int code;
    private Iterator<T> data;

    public PagingResult(Page<T> page) {
        data = page.iterator();
        count = page.getTotalElements();
    }

    public PagingResult(){

    }




}
