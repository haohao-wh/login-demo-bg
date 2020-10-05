package com.config;


import com.model.User;
import com.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassNmae aa
 * @Discription
 * @Author wh
 * @Date 2020/10/5  12:08
 * @Version 1.0
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User userInfo = (User) principals.getPrimaryPrincipal();
        /*
         * for (SysRole role : userInfo.getRoleList()) {
         * authorizationInfo.addRole(role.getRole()); for (SysPermission p :
         * role.getPermissions()) {
         * authorizationInfo.addStringPermission(p.getPermission()); } }
         */
        // 获取用户角色
        Set<String> roleSet = new HashSet<String>();
        roleSet.add("100002");
        info.setRoles(roleSet);

        // 获取用户权限
        Set<String> permissionSet = new HashSet<String>();
        permissionSet.add("权限添加");
        permissionSet.add("权限删除");
        info.setStringPermissions(permissionSet);

        return info;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        User userInfo = userService.findUserByName(username);
        if (userInfo == null) {
            return null;
        }
        String password = new String((char[]) token.getCredentials());
        String pwdMd5 = (new Md5Hash(password, username)).toHex();
        System.out.println("----->>pwdMd5====" + pwdMd5);

        User user = new User();
        user.setName(username);
        user.setPassword(pwdMd5);

        return new SimpleAuthenticationInfo(user, pwdMd5, ByteSource.Util.bytes(username), getName());
    }

}
