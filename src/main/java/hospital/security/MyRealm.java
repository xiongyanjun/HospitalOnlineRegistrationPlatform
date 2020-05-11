package hospital.security;

import hospital.enity.User;
import hospital.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    private LoginService loginService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user =(User) SecurityUtils.getSubject().getSession().getAttribute("User");
        if(user.getRoleId()==1){
            authorizationInfo.addRole("customer");
        }
        else if(user.getRoleId()==2){
            authorizationInfo.addRole("doctor");
        }
        else if(user.getRoleId()==3){
            authorizationInfo.addRole("admin");
        }
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String name = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        List<User> list = loginService.logincheck(name, password);
        if (!list.isEmpty()) {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(usernamePasswordToken.getPrincipal(), usernamePasswordToken.getCredentials(), getName());
            User user = list.get(0);
            Session session = SecurityUtils.getSubject().getSession();
            session.setAttribute("User",user);
            return simpleAuthenticationInfo;
        }
        throw new UnknownAccountException();
    }
}
