package com.lee.schoolhelper.config;

import com.lee.schoolhelper.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class WebExceptionAdvice {
    @ExceptionHandler(RuntimeException.class)
    public R HandleRuntimeException(RuntimeException e){
        log.error(e.toString(),e);
        return R.fail("服务器异常");
    }
}
