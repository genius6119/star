package com.yc.zuul.filter;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.yc.zuul.common.ResponseData;
import com.yc.zuul.common.cache.RedisCacheUtil;
import com.yc.zuul.constant.Constant;
import com.yc.zuul.dao.UserMapper;
import com.yc.zuul.feign.UserService;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: springcloud
 * @description: 请求过滤器
 * @author: Zwx
 * @create: 2019-06-26 12:10
 **/
@Slf4j
public class TokenFilter extends ZuulFilter {

    @Autowired
    UserService userService;

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisCacheUtil redisCacheUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        /**
         * 校验规则
         */
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        HttpServletResponse response = context.getResponse();

        /**
         * 是否是无需登录的接口
         */
        String uri = request.getRequestURI();
        if(isUnloginUnLoginInterface(uri)){
            return null;
        }

        /**
         * token是否有效
         */
        String token = request.getHeader("token");
        String mobile = "";
        if(StringUtils.isNotEmpty(token)){
            mobile= getMobile(token);
        }
        if(token == null || StringUtils.isEmpty(mobile)){
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            try {
                returnErrMessage(response, "请重新登陆~");
            } catch (IOException e) {
                log.error(e.getMessage());
            }
            return null;
        }

        /**
         * 把解密出的手机号添加到转发的路径中
         */
        request.getParameterMap();
        Map<String, List<String>> requestQueryParams = context.getRequestQueryParams();
        if (requestQueryParams==null) {
            requestQueryParams=new HashMap<>();
        }
        ArrayList<String> paramsList = new ArrayList<>();
        paramsList.add(mobile);
        requestQueryParams.put("mobile", paramsList);
        context.setRequestQueryParams(requestQueryParams);

        return null;
    }



    /**
     * 判断token是否有效
     */
    private String getMobile(String token) {
        String mobile = redisCacheUtil.getCacheValue(token);
        if(StringUtils.isNotEmpty(mobile)){
            if(userMapper.selectUserByMobile(mobile) == null){
                return null;
            }else {
                return mobile;
            }
        }
        return null;
    }

    /**
     * 不用登陆也可以访问的URI
     */
    boolean isUnloginUnLoginInterface(String url){
        if(url.equals(Constant.UnLoginInterface.sendVerifyCode) ||
        url.equals(Constant.UnLoginInterface.loginByVerifyCode)){
            return true;
        }
        return false;
    }

    private void returnErrMessage(HttpServletResponse response, String message) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = response.getWriter();

        out.append(JSON.toJSONString(ResponseData.LoginExpired(message)));
    }

}
