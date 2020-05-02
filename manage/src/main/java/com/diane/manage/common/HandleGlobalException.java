package com.diane.manage.common;

import com.diane.common.http.HttpResult;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class HandleGlobalException {

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public HttpResult globalExceptionHandle(Exception e){


        System.out.println(e.getMessage());
       return HttpResult.error("服务器异常");



    }
}
