/*
 * Copyright 2020 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.com.xuxiaowei.util.rsa;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

/**
 * RSA 测试类
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(value = {"classpath:spring-context.xml"})
public class RsaTests {

    private static final String DATA = "你好，欢迎使用 RSA 加密。";

    @Test
    public void rsa() throws NoSuchAlgorithmException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException,
            InvalidKeySpecException, NoSuchPaddingException, SignatureException {
        Rsa.StringKey stringKey = Rsa.getStringKey();

        String publicKey = stringKey.getPublicKey();
        String privateKey = stringKey.getPrivateKey();

        String publicKeyEncrypt = Rsa.publicKeyEncrypt(publicKey, DATA);
        String privateKeyDecrypt = Rsa.privateKeyDecrypt(privateKey, publicKeyEncrypt);

        String signByte = RsaSignature.sign(privateKey, publicKeyEncrypt);

        boolean publicKeyVerifySign = RsaSignature.verifySign(publicKey, publicKeyEncrypt, signByte);

        log.info("公钥：" + publicKey);
        log.info("私钥：" + privateKey);
        log.info("公钥加密前的数据：" + DATA);
        log.info("私钥解密后的数据：" + privateKeyDecrypt);
        log.info("");
        log.info("私钥签名：\t\t" + signByte);
        log.info("公钥验证签名：\t" + publicKeyVerifySign);
        log.info("");
    }

}
