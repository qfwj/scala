package javatest.filterandinterceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: TODO
 * @author: zhbo
 * @date 2020/5/14 10:47
 */
@Configuration
public class IntercpetorConfig implements WebMvcConfigurer {


    @Autowired
    private ParamValidateInterceptor repeatInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(repeatInterceptor).addPathPatterns("/**");
    }
}

