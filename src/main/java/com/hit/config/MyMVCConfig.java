package com.hit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import java.util.Locale;
//如果想DIY一些定制化的功能，只要写这个组件，然后将它交给springboot
//扩展 springMVC  dispatchservlet
@Configuration
public class MyMVCConfig implements WebMvcConfigurer {
    //
    //视图跳转
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
        registry.addViewController("/login.html").setViewName("login");
        registry.addViewController("/main.html").setViewName("dashboard");
    }
    //使自定义的国际化组件生效
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }
//    //添加拦截器
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor())
//                .addPathPatterns("/**").excludePathPatterns("/index.html","/","/user/login","/css/*","/js/**","/img/**");
//    }


    //    //public interface ViewResolver 视图解析器（凡是实现了这个接口）
//    @Bean
//    public ViewResolver myViewResolver(){
//        return  new MyViewResolver;
//    }
//
//    public  static  class  MyViewResolver implements ViewResolver{
//    //自定义了一个自己的视图解析器
//        @Override
//        public View resolveViewName(String s, Locale locale) throws Exception {
//            return null;
//        }
//    }
}
