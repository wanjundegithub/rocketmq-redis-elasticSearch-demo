package com.company.api.gateway.service;

import com.company.api.gateway.util.JwtUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
@Slf4j
public class AccessFilter extends ZuulFilter {
    /**
     * 过滤器类型选择：
     * pre 为路由前
     * route 为路由过程中
     * post 为路由过程后
     * error 为出现错误的时候
     * 同时也支持static ，返回静态的响应，详情见StaticResponseFilter的实现
     * 以上类型在会创建或添加或运行在FilterProcessor.runFilters(type)
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    /**
     * 用来过滤器排序执行的
     * @return
     */
    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     是否通过这个过滤器，默认为true，改成false则不启用
     * @return
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest httpServletRequest = requestContext.getRequest();
        String token = httpServletRequest.getHeader("token");
        if (StringUtils.isEmpty(token)){
            log.info("token 不合法"+ token);
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            return null;
        }
        if(!JwtUtil.validateToken(token)){
            log.info("token 不合法"+ token);
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            return null;
        }
        return null;
    }
}
