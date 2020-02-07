package cn.com.xuxiaowei.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 密码编码器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Component
public class PasswordEncoderConfiguration implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return rawPassword.toString();
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {

        String password = String.valueOf(rawPassword);

        return password.equals(encodedPassword);
    }

}
