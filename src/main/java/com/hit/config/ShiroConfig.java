package com.hit.config;

import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    //ShiroFilterFactoryBean:3
    @Bean
    public  ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        /*
        * anon:无需认证就可以访问
        * authc:必须认证了才能访问
        * user:必须拥有记住我功能才能访问
        * perms：拥有对某个资源的权限才能访问
        * role:拥有某个角色权限才能访问
        * */
        Map<String,String> filterMap=new LinkedHashMap<>();
//        filterMap.put("/index","anon");
        //授权
        //正常情况下，未授权会跳转到未授权页面
//        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/root/**","perms[root]");
//        filterMap.put("/root/dashboard","role[root]");
        //认证
//        filterMap.put("/user/*","authc");
        filterMap.put("/user/**","authc");
        bean.setFilterChainDefinitionMap(filterMap);
        bean.setLoginUrl("/toLogin");
        bean.setUnauthorizedUrl("/noauth");

        return bean;
    }
    //DefaultWebSecurityManager:2
    @Bean(name="securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager=new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return  securityManager;
    }



    //创建realm对象,需要自定义:1
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }

    /**
     * rememberMe管理器
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(SimpleCookie rememberMeCookie) {
        CookieRememberMeManager manager = new CookieRememberMeManager();
        manager.setCipherKey(Base64.decode("Z3VucwAAAAAAAAAAAAAAAA=="));
        manager.setCookie(rememberMeCookie);
        return manager;
    }

    /**
     * 记住密码Cookie
     */
    @Bean
    public SimpleCookie rememberMeCookie() {
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(true);
        simpleCookie.setMaxAge(7 * 24 * 60 * 60);//7天
        return simpleCookie;
    }

}
