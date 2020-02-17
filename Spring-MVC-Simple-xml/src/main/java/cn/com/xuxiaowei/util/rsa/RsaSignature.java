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

import org.apache.commons.codec.binary.Base64;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA 非对称加密签名
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
public class RsaSignature {

    /**
     * 数字签名算法类型
     *
     * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#Signature">Signature Algorithms</a>
     */
    private static final String ALGORITHM_SIGNATURE = "MD5withRSA";

    /**
     * 使用私钥进行签名
     *
     * @param privateKeyByte 私钥
     * @param data           需要签名的加密数据
     * @return 返回私钥签名
     * @throws NoSuchAlgorithmException 算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws InvalidKeySpecException  使用字节数据类型的私钥根据指定算法生成私钥时异常
     *                                  可能出现的原因：
     *                                  1、私钥错误
     *                                  2、私钥与算法不匹配
     * @throws InvalidKeyException      秘钥无效、秘钥与秘钥类型不匹配
     * @throws SignatureException       签名异常
     * @see #signByte(byte[], byte[])
     * @see #verifySign(String, String, String) 对应验证签名时使用
     */
    public static String sign(String privateKeyByte, String data) throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, SignatureException {
        return Base64.encodeBase64String(signByte(Base64.decodeBase64(privateKeyByte), Base64.decodeBase64(data)));
    }

    /**
     * 使用公钥进行签名
     *
     * @param publicKey 公钥
     * @param data      需要验证签名的加密数据
     * @param signByte  签名，字节数组类型
     * @return 返回验证签名结果
     * @throws NoSuchAlgorithmException 算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws InvalidKeySpecException  使用字节数据类型的私钥根据指定算法生成私钥时异常
     *                                  可能出现的原因：
     *                                  1、私钥错误
     *                                  2、私钥与算法不匹配
     * @throws InvalidKeyException      秘钥无效、秘钥与秘钥类型不匹配
     * @throws SignatureException       签名异常
     * @see #verifySignByte(byte[], byte[], byte[])
     * @see #sign(String, String) 对应签名时使用
     */
    public static boolean verifySign(String publicKey, String data, String signByte)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        return verifySignByte(Base64.decodeBase64(publicKey), Base64.decodeBase64(data), Base64.decodeBase64(signByte));
    }

    /**
     * 使用私钥进行签名
     *
     * @param privateKeyByte 私钥，字节数组类型
     * @param data           需要签名的加密数据，字节数组类型
     * @return 返回私钥签名，字节数组类型
     * @throws NoSuchAlgorithmException 算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws InvalidKeySpecException  使用字节数据类型的私钥根据指定算法生成私钥时异常
     *                                  可能出现的原因：
     *                                  1、私钥错误
     *                                  2、私钥与算法不匹配
     * @throws InvalidKeyException      秘钥无效、秘钥与秘钥类型不匹配
     * @throws SignatureException       签名异常
     * @see #verifySignByte(byte[], byte[], byte[]) 对应验证签名时使用
     */
    public static byte[] signByte(byte[] privateKeyByte, byte[] data) throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, SignatureException {

        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyByte);

        KeyFactory keyFactory = KeyFactory.getInstance(Rsa.ALGORITHM_KEY);

        Signature signature = Signature.getInstance(ALGORITHM_SIGNATURE);

        PrivateKey priKey = keyFactory.generatePrivate(pkcs8KeySpec);
        signature.initSign(priKey);
        signature.update(data);

        return signature.sign();
    }

    /**
     * 使用公钥进行签名
     *
     * @param publicKeyByte 公钥，字节数组类型
     * @param data          需要验证签名的加密数据，字节数组类型
     * @param signByte      签名，字节数组类型
     * @return 返回验证签名结果
     * @throws NoSuchAlgorithmException 算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws InvalidKeySpecException  使用字节数据类型的公钥根据指定算法生成公钥时异常
     *                                  可能出现的原因：
     *                                  1、公钥错误
     *                                  2、公钥与算法不匹配
     * @throws InvalidKeyException      秘钥无效、秘钥与秘钥类型不匹配
     * @throws SignatureException       签名异常
     * @see #signByte(byte[], byte[]) 对应签名时使用
     */
    public static boolean verifySignByte(byte[] publicKeyByte, byte[] data, byte[] signByte)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyByte);

        KeyFactory keyFactory = KeyFactory.getInstance(Rsa.ALGORITHM_KEY);

        Signature signature = Signature.getInstance(ALGORITHM_SIGNATURE);

        PublicKey pubKey = keyFactory.generatePublic(keySpec);
        signature.initVerify(pubKey);
        signature.update(data);

        return signature.verify(signByte);
    }

}
