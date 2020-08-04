package com.hit.config;

import com.hit.pojo.User;
import com.hit.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

//自定义的realm         extends AuthorizingRealm
public class UserRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        System.out.println("执行了=>授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermission("user:add");
        //拿到当前登录的这个对象(需要认证界面传递user)
        Subject subject= SecurityUtils.getSubject();
        User currentUser=(User)subject.getPrincipal();
        info.addStringPermission(currentUser.getPerms());
//        info.addRole(currentUser.getRole());
        return info;

    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        System.out.println("执行了=>认证");

        UsernamePasswordToken userToken=(UsernamePasswordToken) token;
        //连接真实的数据库
        User user=userService.queryUserByName(userToken.getUsername());
        if(user==null){
            return null;//抛出异常UnknownAccountException
        }
        //密码认证,shiro做
        return new SimpleAuthenticationInfo(user,user.getPwd(),"");
    }
}
