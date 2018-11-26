/*
package com.wl.servicezuul.config;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MyFilter extends ZuulFilter {

    private static Logger log = LoggerFactory.getLogger(MyFilter.class);

    //可以在请求被路由之前调用
    @Override
    public String filterType() {
        return "pre";
    }

    //通过int值来定义过滤器的执行顺序，越小的值越优先处理。
    @Override
    public int filterOrder() {
        return 2;
    }

    //返回一个boolean类型来判断该过滤器是否要执行，所以通过此函数可实现过滤器的开关。true:总是生效，false:不生效
    @Override
    public boolean shouldFilter() {

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("请求url为 ：" + request.getRequestURI());

        // 在请求api-a/hi做token的鉴权
        if ("/api-a/hi".equalsIgnoreCase(request.getRequestURI())) {
            return true;
        } else {
            //todo
            return true;
        }
    }

    */
/**
     * pre 路由之前
     * routing 路由之时
     * post 路由之后
     * error 发送错误调用
     * @return
     * @throws ZuulException
     *//*
//过滤器的具体逻辑根据业务自行编写。
    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        log.info(" method :"+request.getServletPath());

        log.info(String.format("%s >>> %s", request.getMethod(), request.getRequestURL().toString()));
        Object accessToken = request.getParameter("token");
        log.info("token : " + accessToken);
        if (accessToken == null) {
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);

            try {
                ctx.getResponse().getWriter().write("token is empty");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        log.info("ok");
        return null;
    }
}
*/
