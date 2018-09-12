package com.cycfc.zuul.server.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * 描述：限流拦截器<br/>
 *
 * @author Yanzheng 严正<br/>
 * 时间：2018/8/30 10:23<br/>
 * 版权：Copyright 2018 Cycfc. All rights reserved.
 */
@Component
@Slf4j
public class RateLimitFilter extends ZuulFilter {

    private static final RateLimiter RATE_LIMITER = RateLimiter.create(100);// 塞入100个令牌

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return SERVLET_DETECTION_FILTER_ORDER - 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext requestContext = RequestContext.getCurrentContext();
        // 如果获取不到令牌，进行拦截
        if (!RATE_LIMITER.tryAcquire()) {
            log.info("进入cycfc-zuul-server服务，执行RateLimitFilter，获取不到令牌。");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(HttpStatus.BAD_REQUEST.value());
        }
        log.info("进入cycfc-uul-server服务，执行RateLimitFilter，获取令牌成功！");
        return null;
    }
}
