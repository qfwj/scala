package javatest.filterandinterceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/14 10:33
 */
@Component
public class ParamValidateInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        Map mm = request.getParameterMap();
        String ddd = request.getParameter("appSecret");
        RequestWrapper requestWrapper = new RequestWrapper(request);
        String body = requestWrapper.getBody();
        System.out.println("拦截器获取到body内容:"+body);


        return true;
    }
}


