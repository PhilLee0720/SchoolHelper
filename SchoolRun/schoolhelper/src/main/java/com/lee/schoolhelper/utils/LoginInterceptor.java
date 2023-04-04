package com.lee.schoolhelper.utils;

import cn.hutool.core.bean.BeanUtil;
import com.lee.schoolhelper.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        log.info("session is {}",session);
        Object userInfo = session.getAttribute("userInfo");
        log.info("user info  is {}",userInfo);
        if(userInfo == null){
            response.setStatus(401);
            return false;
        }
        UserDTO userDTO = BeanUtil.toBean(userInfo, UserDTO.class);
        log.info("user dto is {}",userDTO);
        UserHolder.set(userDTO);
        return  true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserHolder.remove();
    }
}
