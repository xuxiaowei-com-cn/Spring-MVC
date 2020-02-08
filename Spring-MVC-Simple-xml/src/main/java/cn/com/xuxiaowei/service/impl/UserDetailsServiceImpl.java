package cn.com.xuxiaowei.service.impl;

import cn.com.xuxiaowei.entity.User;
import cn.com.xuxiaowei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * 查询登录用户 服务实现层
 *
 * @author xuxiaowei
 * @see Serializable 用于 Session 共享的序列化
 * @since 0.0.1
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, Serializable {

    private static final long serialVersionUID = -8516366996649935635L;

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 权限
        ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        String password;

        // 用户名为空
        if (username == null || "".equals(username)) {

            // 用户名，用于比较
            username = UUID.randomUUID().toString();

            // 密码，用于比较（如果用户未空，返回随机密码（不可预测））
            password = UUID.randomUUID().toString();

        } else {

            User byUsername = userService.getByUsername(username);

            // 未查询到用户
            if (byUsername == null) {

                // 密码，用于比较（如果用户未空，返回随机密码（不可预测））
                password = UUID.randomUUID().toString();

            } else {

                password = byUsername.getPassword();

                // 用户默认权限，正式环境请查询数据库
                authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            }

        }

        return new org.springframework.security.core.userdetails.User(username, password, authorities);
    }

}
