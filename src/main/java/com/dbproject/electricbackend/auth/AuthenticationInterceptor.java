package com.dbproject.electricbackend.auth;

import com.dbproject.electricbackend.exception.CustomException;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Objects;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String token = request.getHeader("Token");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        // check NeedAuth annotation
        if (method.isAnnotationPresent(AuthRequired.class)) {
            AuthRequired auth = method.getAnnotation(AuthRequired.class);
            if (auth.required()) {
                if (Objects.isNull(token)) {
                    throw CustomException.defined(CustomException.Define.NEED_LOGIN_FIRST);
                }
                if (TokenUtil.verifyToken(token)) {
                    return true;
                } else {
                    throw CustomException.defined(CustomException.Define.INVALID_SESSION);
                }
            }
        }
        return true;
    }
}
