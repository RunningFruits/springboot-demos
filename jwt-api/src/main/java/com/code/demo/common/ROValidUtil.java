package com.code.demo.common;

import com.code.demo.api.exception.ServiceException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;


public class ROValidUtil {

    public static void valid(BindingResult result) {
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                throw new ServiceException(1, error.getDefaultMessage());
            }
        }
    }
}
