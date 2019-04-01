package com.xw.config;

import com.xw.entiry.User;
import com.xw.mapper.UserDao;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityRealm extends AuthorizingRealm {

    @Autowired
    private UserDao userDao;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String currentUserName = (String)principalCollection.getPrimaryPrincipal();
        List<String> roles = new ArrayList<String>();  //角色
        List<String> prems = new ArrayList<String>(); //权限
        roles.add("baidu");
        roles.add("google");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        authorizationInfo.addStringPermissions(prems);
        return authorizationInfo;
    }

    /**
     * 认证，验证当前登录的Subject
     * LoginController.login 方法中调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

//        SecurityUtils.getSubject().getSession().getId(),AuthenticationInfo.class
        String userName = (String)authenticationToken.getPrincipal();
        //为了测试通过，这里暂时写死， user： admin password： 123456
        // UserVo user = loginClient.selectUserByName(userName);
        User user = userDao.findByName(userName);
        if(user == null){
            throw new UnknownAccountException();//没找到帐号
        }
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                "admin", //用户名
                user.getPassword(), //密码
                getName()  //realm name
        );
        return authenticationInfo;
    }
}
