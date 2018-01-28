package com.greywanchuang.rackmonitor.authorization.interceptor;

import com.greywanchuang.rackmonitor.authorization.annotation.Authorization;
import com.greywanchuang.rackmonitor.authorization.annotation.CurrentUser;
import com.greywanchuang.rackmonitor.authorization.manager.TokenManager;
import com.greywanchuang.rackmonitor.authorization.model.TokenModel;
import com.greywanchuang.rackmonitor.entity.User;
import com.greywanchuang.rackmonitor.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，判断此次请求是否有权限
 * @see com.greywanchuang.rackmonitor.authorization.annotation.Authorization
 * @author ScienJus
 * @date 2015/7/30.
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        //是否为登录操作
        if(request.getRequestURI().contains("login"))
        {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        //从header中得到token
        String authorization = request.getHeader(Constants.AUTHORIZATION);
        if("".equals(authorization) || authorization==null)
        {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().print("Bad Request");
            return false;
        }
        //验证token
//        String userId=request.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST).toString();
        User user = manager.getCurrentUser(authorization);
        if (user!=null && manager.checkToken(authorization)) {
            //如果token验证成功，将token对应的用户id存在request中，便于之后注入
            request.setAttribute(Constants.CURRENT_USER_ID, user.getId());
            return true;
        }
        //如果验证token失败，并且方法注明了Authorization，返回401错误
        if (method.getAnnotation(Authorization.class) != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().print("Unauthorization");
            return false;
        }
        return true;
    }
}