package com.wl.servicezuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.wl.constant.RedisConstant;
import com.wl.util.CookieUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * 后台登录的权限管理
 */
@Component
public class AdminZuulFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(AdminZuulFilter.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info("当前请求url为：" + request.getRequestURI());
        //当访问--url，就开启
        if (request.getRequestURI().contains("/qa/")) {
            return true;
        }
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        Cookie cookie = CookieUtil.get(request,"openid");
        if (cookie == null || StringUtils.isEmpty(cookie.getValue())
                || StringUtils.isEmpty(
                redisTemplate.opsForValue().get(String.format(RedisConstant.OPENID_TEMPLATE, cookie.getValue())))) {
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(911);
        }
        return null;
    }
}
