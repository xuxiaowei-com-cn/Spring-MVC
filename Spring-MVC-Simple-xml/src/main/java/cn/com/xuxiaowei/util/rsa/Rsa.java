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

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * RSA 非对称加密
 * <p>
 * 需要依赖 Apache Commons Codec
 * <p>
 * Apache Commons Codec 软件包包含各种格式的简单编码器和解码器，如Base64和Hexadecimal。
 * 除了这些广泛使用的编码器和解码器之外，编解码器包还维护一组语音编码实用程序。
 *
 * @author xuxiaowei
 * @see <a href="https://mvnrepository.com/artifact/commons-codec/commons-codec">Apache Commons Codec</a>
 * @since 0.0.1
 */
public class Rsa {

    /**
     * RSA 私钥 名称
     */
    public static String RSA_PRIVATE_KEY = "RSA_PRIVATE_KEY";

    /**
     * RSA 公钥 名称
     */
    public static String RSA_PUBLIC_KEY = "RSA_PUBLIC_KEY";

    /**
     * 加密算法类型
     * <p>
     * 只能是：DiffieHellman、DSA、RSA、EC
     *
     * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator">KeyPairGenerator Algorithms</a>
     */
    static final String ALGORITHM_KEY = "RSA";

    /**
     * 算法长度
     * DiffieHellman（1024）、DSA（1024）、RSA（1024,2048）
     *
     * @see <a href="https://docs.oracle.com/javase/8/docs/api/java/security/KeyPairGenerator.html">Class KeyPairGenerator</a>
     */
    private static final int KEY_SIZE = 1024;

    /**
     * 初始化秘钥对
     *
     * @return 标准密钥对
     * @throws NoSuchAlgorithmException 算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @see <a href="https://docs.oracle.com/javase/8/docs/technotes/guides/security/StandardNames.html#KeyPairGenerator">KeyPairGenerator Algorithms</a>
     */
    public static KeyPair initKeyPair() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_KEY);
        keyPairGenerator.initialize(KEY_SIZE);
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 初始化密钥对
     *
     * @return 字节数组数据类型的密钥对
     * @throws NoSuchAlgorithmException 算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @see #initKeyPair()
     */
    public static ByteKey getByteKey() throws NoSuchAlgorithmException {
        KeyPair keyPair = initKeyPair();
        return new ByteKey(keyPair.getPublic(), keyPair.getPrivate());
    }

    /**
     * 初始化密钥对
     *
     * @return String 类型的密钥对
     * @throws NoSuchAlgorithmException 算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @see #initKeyPair()
     */
    public static StringKey getStringKey() throws NoSuchAlgorithmException {
        KeyPair keyPair = initKeyPair();
        return new StringKey(keyPair.getPublic(), keyPair.getPrivate());
    }

    /**
     * 通过公钥加密数据
     *
     * @param publicKeyByte 公钥，字节数组类型
     * @param dataByte      需要加密的数据，字节数组类型
     * @return 返回公钥加密数据
     * @throws NoSuchAlgorithmException  算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws InvalidKeySpecException   使用字节数据类型的公钥根据指定算法生成公钥时异常
     *                                   可能出现的原因：
     *                                   1、公钥错误
     *                                   2、公钥与算法不匹配
     * @throws NoSuchPaddingException    本例中不出现
     *                                   使用 {@link KeyPairGenerator#getInstance(String, String)}、
     *                                   {@link KeyPairGenerator#getInstance(String, Provider)} 时可能会出现此异常
     * @throws InvalidKeyException       秘钥无效、秘钥与秘钥类型不匹配
     * @throws BadPaddingException       秘钥错误
     * @throws IllegalBlockSizeException 加密模块长度不合法
     *                                   长度限制：需要加密的数组字节数据长度 小于 (数据加密算法模块长度 / 8 - 11)
     * @see #publicKey(byte[], byte[], int)
     * @see #privateKeyDecryptByte(byte[], byte[]) 对应解密时使用
     */
    public static byte[] publicKeyEncryptByte(byte[] publicKeyByte, byte[] dataByte) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        return publicKey(publicKeyByte, dataByte, Cipher.ENCRYPT_MODE);
    }


    /**
     * 通过私钥解密数据
     *
     * @param privateKeyByte 私钥，字节数组类型
     * @param dataByte       需要解密的数据，字节数组类型
     * @return 返回通过私钥解密的数据，字节数组类型
     * @throws NoSuchAlgorithmException  算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws InvalidKeySpecException   使用字节数据类型的公钥根据指定算法生成公钥时异常
     *                                   可能出现的原因：
     *                                   1、公钥错误
     *                                   2、公钥与算法不匹配
     * @throws NoSuchPaddingException    本例中不出现
     *                                   使用 {@link KeyPairGenerator#getInstance(String, String)}、
     *                                   {@link KeyPairGenerator#getInstance(String, Provider)} 时可能会出现此异常
     * @throws InvalidKeyException       秘钥无效、秘钥与秘钥类型不匹配
     * @throws BadPaddingException       秘钥错误
     * @throws IllegalBlockSizeException 加密模块长度不合法
     *                                   长度限制：需要加密的数组字节数据长度 小于 (数据加密算法模块长度 / 8 - 11)
     * @see #privateKey(byte[], byte[], int)
     * @see #publicKeyEncryptByte(byte[], byte[]) 对应加密时使用
     */
    public static byte[] privateKeyDecryptByte(byte[] privateKeyByte, byte[] dataByte) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {
        return privateKey(privateKeyByte, dataByte, Cipher.DECRYPT_MODE);
    }

    /**
     * 通过公钥加密数据
     *
     * @param publicKey 公钥
     * @param data      需要加密的数据
     * @return 返回公钥加密数据
     * @throws NoSuchAlgorithmException  算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws InvalidKeySpecException   使用字节数据类型的公钥根据指定算法生成公钥时异常
     *                                   可能出现的原因：
     *                                   1、公钥错误
     *                                   2、公钥与算法不匹配
     * @throws NoSuchPaddingException    本例中不出现
     *                                   使用 {@link KeyPairGenerator#getInstance(String, String)}、
     *                                   {@link KeyPairGenerator#getInstance(String, Provider)} 时可能会出现此异常
     * @throws InvalidKeyException       秘钥无效、秘钥与秘钥类型不匹配
     * @throws BadPaddingException       秘钥错误
     * @throws IllegalBlockSizeException 加密模块长度不合法
     *                                   长度限制：需要加密的数组字节数据长度 小于 (数据加密算法模块长度 / 8 - 11)
     * @see #privateKey(byte[], byte[], int)
     * @see #privateKeyDecrypt(String, String) 对应解密时使用
     */
    public static String publicKeyEncrypt(String publicKey, String data) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        return Base64.encodeBase64String(publicKeyEncryptByte(Base64.decodeBase64(publicKey), data.getBytes()));
    }

    /**
     * 通过私钥解密数据
     *
     * @param privateKey 私钥
     * @param data       需要解密的数据
     * @return 返回私钥解密数据
     * @throws NoSuchAlgorithmException  算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws InvalidKeySpecException   使用字节数据类型的公钥根据指定算法生成公钥时异常
     *                                   可能出现的原因：
     *                                   1、公钥错误
     *                                   2、公钥与算法不匹配
     * @throws NoSuchPaddingException    本例中不出现
     *                                   使用 {@link KeyPairGenerator#getInstance(String, String)}、
     *                                   {@link KeyPairGenerator#getInstance(String, Provider)} 时可能会出现此异常
     * @throws InvalidKeyException       秘钥无效、秘钥与秘钥类型不匹配
     * @throws BadPaddingException       秘钥错误
     * @throws IllegalBlockSizeException 加密模块长度不合法
     *                                   长度限制：需要加密的数组字节数据长度 小于 (数据加密算法模块长度 / 8 - 11)
     * @see #publicKey(byte[], byte[], int)
     * @see #publicKeyEncrypt(String, String) 对应加密时使用
     */
    public static String privateKeyDecrypt(String privateKey, String data) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {
        return new String(privateKeyDecryptByte(Base64.decodeBase64(privateKey), Base64.decodeBase64(data)));
    }

    /**
     * 通过私钥加密数据
     *
     * @param privateKeyByte 私钥，字节数组类型
     * @param dataByte       需要解密的数据，字节数组类型
     * @return 返回私钥加密数据，字节数组类型
     * @throws NoSuchPaddingException    本例中不出现
     *                                   使用 {@link KeyPairGenerator#getInstance(String, String)}、
     *                                   {@link KeyPairGenerator#getInstance(String, Provider)} 时可能会出现此异常
     * @throws NoSuchAlgorithmException  算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws IllegalBlockSizeException 加密模块长度不合法
     *                                   长度限制：需要加密的数组字节数据长度 小于 (数据加密算法模块长度 / 8 - 11)
     * @throws BadPaddingException       秘钥错误
     * @throws InvalidKeyException       秘钥无效、秘钥与秘钥类型不匹配
     * @throws InvalidKeySpecException   使用字节数据类型的公钥根据指定算法生成公钥时异常
     *                                   可能出现的原因：
     *                                   1、公钥错误
     *                                   2、公钥与算法不匹配
     * @see #privateKey(byte[], byte[], int)
     * @see #publicKeyDecryptByte(byte[], byte[]) 对应解密时使用
     */
    public static byte[] privateKeyEncryptByte(byte[] privateKeyByte, byte[] dataByte) throws NoSuchPaddingException,
            NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
            InvalidKeySpecException {
        return privateKey(privateKeyByte, dataByte, Cipher.ENCRYPT_MODE);
    }

    /**
     * 通过公钥解密数据
     *
     * @param publicKeyByte 公钥，字节数组类型
     * @param dataByte      需要加密的数据，字节数组类型
     * @return 返回公钥解密数据，字节数组类型
     * @throws NoSuchPaddingException    本例中不出现
     *                                   使用 {@link KeyPairGenerator#getInstance(String, String)}、
     *                                   {@link KeyPairGenerator#getInstance(String, Provider)} 时可能会出现此异常
     * @throws NoSuchAlgorithmException  算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws IllegalBlockSizeException 加密模块长度不合法
     *                                   长度限制：需要加密的数组字节数据长度 小于 (数据加密算法模块长度 / 8 - 11)
     * @throws BadPaddingException       秘钥错误
     * @throws InvalidKeyException       秘钥无效、秘钥与秘钥类型不匹配
     * @throws InvalidKeySpecException   使用字节数据类型的公钥根据指定算法生成公钥时异常
     *                                   可能出现的原因：
     *                                   1、公钥错误
     *                                   2、公钥与算法不匹配
     * @see #publicKey(byte[], byte[], int)
     * @see #privateKeyEncryptByte(byte[], byte[]) 对应加密时使用
     */
    public static byte[] publicKeyDecryptByte(byte[] publicKeyByte, byte[] dataByte) throws NoSuchPaddingException,
            NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
            InvalidKeySpecException {
        return publicKey(publicKeyByte, dataByte, Cipher.DECRYPT_MODE);
    }

    /**
     * 通过私钥加密数据
     *
     * @param privateKey 私钥
     * @param data       需要解密的数据
     * @return 私密加密的数据
     * @throws NoSuchPaddingException    本例中不出现
     *                                   使用 {@link KeyPairGenerator#getInstance(String, String)}、
     *                                   {@link KeyPairGenerator#getInstance(String, Provider)} 时可能会出现此异常
     * @throws NoSuchAlgorithmException  算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws IllegalBlockSizeException 加密模块长度不合法
     *                                   长度限制：需要加密的数组字节数据长度 小于 (数据加密算法模块长度 / 8 - 11)
     * @throws BadPaddingException       秘钥错误
     * @throws InvalidKeyException       秘钥无效、秘钥与秘钥类型不匹配
     * @throws InvalidKeySpecException   使用字节数据类型的公钥根据指定算法生成公钥时异常
     *                                   可能出现的原因：
     *                                   1、公钥错误
     *                                   2、公钥与算法不匹配
     * @see #privateKey(byte[], byte[], int)
     * @see #publicKeyDecrypt(String, String) 对应解密时使用
     */
    public static String privateKeyEncrypt(String privateKey, String data) throws NoSuchPaddingException,
            NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
            InvalidKeySpecException {
        return Base64.encodeBase64String(privateKey(Base64.decodeBase64(privateKey), data.getBytes(), Cipher.ENCRYPT_MODE));
    }

    /**
     * 通过公钥解密数据
     *
     * @param publicKey 公钥，字节数组类型
     * @param data      需要加密的数据，字节数组类型
     * @return 返回公钥解密数据，字节数组类型
     * @throws NoSuchPaddingException    本例中不出现
     *                                   使用 {@link KeyPairGenerator#getInstance(String, String)}、
     *                                   {@link KeyPairGenerator#getInstance(String, Provider)} 时可能会出现此异常
     * @throws NoSuchAlgorithmException  算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws IllegalBlockSizeException 加密模块长度不合法
     *                                   长度限制：需要加密的数组字节数据长度 小于 (数据加密算法模块长度 / 8 - 11)
     * @throws BadPaddingException       秘钥错误
     * @throws InvalidKeyException       秘钥无效、秘钥与秘钥类型不匹配
     * @throws InvalidKeySpecException   使用字节数据类型的公钥根据指定算法生成公钥时异常
     *                                   可能出现的原因：
     *                                   1、公钥错误
     *                                   2、公钥与算法不匹配
     * @see #publicKey(byte[], byte[], int)
     * @see #privateKeyEncrypt(String, String) 对应加密时使用
     */
    public static String publicKeyDecrypt(String publicKey, String data) throws NoSuchPaddingException,
            NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
            InvalidKeySpecException {
        return new String(publicKey(Base64.decodeBase64(publicKey), Base64.decodeBase64(data), Cipher.DECRYPT_MODE));
    }


    /**
     * 通过公钥加密、解密数据
     *
     * @param publicKeyByte    公钥，字节数组类型
     * @param dataByte         需要加密的数据，字节数组类型
     * @param encryptOrDecrypt 加密还是解密
     *                         加密：{@link Cipher#ENCRYPT_MODE}
     *                         解密：{@link Cipher#DECRYPT_MODE}
     * @return 返回通过公钥加密的数据
     * @throws NoSuchAlgorithmException  算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws InvalidKeySpecException   使用字节数据类型的公钥根据指定算法生成公钥时异常
     *                                   可能出现的原因：
     *                                   1、公钥错误
     *                                   2、公钥与算法不匹配
     * @throws NoSuchPaddingException    本例中不出现
     *                                   使用 {@link KeyPairGenerator#getInstance(String, String)}、
     *                                   {@link KeyPairGenerator#getInstance(String, Provider)} 时可能会出现此异常
     * @throws InvalidKeyException       秘钥无效、秘钥与秘钥类型不匹配
     * @throws BadPaddingException       秘钥错误
     *                                   可能出现的错误：秘钥与秘钥类型不匹配
     * @throws IllegalBlockSizeException 加密模块长度不合法
     *                                   长度限制：需要加密的数组字节数据长度 小于 (数据加密算法模块长度 / 8 - 11)
     * @see <a href="https://docs.oracle.com/javase/8/docs//technotes/guides/security/StandardNames.html#KeyFactory">KeyFactory Algorithms</a>
     */
    private static byte[] publicKey(byte[] publicKeyByte, byte[] dataByte, int encryptOrDecrypt) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {

        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(publicKeyByte);

        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_KEY);

        Key publicKey = keyFactory.generatePublic(x509KeySpec);
        String algorithmPublicKey = keyFactory.getAlgorithm();

        Cipher cipherPublicKey = Cipher.getInstance(algorithmPublicKey);
        cipherPublicKey.init(encryptOrDecrypt, publicKey);

        return cipherPublicKey.doFinal(dataByte);
    }

    /**
     * 通过私钥加密、解密数据
     *
     * @param privateKeyByte   私钥，字节数组类型
     * @param dataByte         需要解密的数据，字节数组类型
     * @param encryptOrDecrypt 加密还是解密
     *                         加密：{@link Cipher#ENCRYPT_MODE}
     *                         解密：{@link Cipher#DECRYPT_MODE}
     * @return 返回通过私钥解密的数据，字节数组类型
     * @throws NoSuchAlgorithmException  算法类型异常，只能是：DiffieHellman、DSA、RSA、EC
     * @throws InvalidKeySpecException   使用字节数据类型的私钥根据指定算法生成私钥时异常
     *                                   可能出现的原因：
     *                                   1、私钥错误
     *                                   2、私钥与算法不匹配
     * @throws NoSuchPaddingException    本例中不出现
     *                                   使用 {@link KeyPairGenerator#getInstance(String, String)}、
     *                                   {@link KeyPairGenerator#getInstance(String, Provider)} 时可能会出现此异常
     * @throws InvalidKeyException       秘钥无效、秘钥与秘钥类型不匹配
     * @throws BadPaddingException       秘钥错误
     *                                   可能出现的错误：秘钥与秘钥类型不匹配
     * @throws IllegalBlockSizeException 解密模块长度不合法
     *                                   长度限制：需要解密的数组字节数据长度 小于 (数据加密算法模块长度 / 8 - 11)
     * @see <a href="https://docs.oracle.com/javase/8/docs//technotes/guides/security/StandardNames.html#KeyFactory">KeyFactory Algorithms</a>
     */
    private static byte[] privateKey(byte[] privateKeyByte, byte[] dataByte, int encryptOrDecrypt) throws NoSuchAlgorithmException,
            InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, BadPaddingException,
            IllegalBlockSizeException {

        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM_KEY);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(privateKeyByte);

        Key privateKey = keyFactory.generatePrivate(pkcs8KeySpec);
        String algorithmPrivateKey = keyFactory.getAlgorithm();

        Cipher cipherPrivateKey = Cipher.getInstance(algorithmPrivateKey);
        cipherPrivateKey.init(encryptOrDecrypt, privateKey);

        return cipherPrivateKey.doFinal(dataByte);
    }

    /**
     * 字节数据类型的密钥对
     */
    public static class ByteKey {

        /**
         * 公钥
         */
        private final byte[] publicKey;

        /**
         * 私钥
         */
        private final byte[] privateKey;

        /**
         * 构造器
         *
         * @param publicKey  公钥
         * @param privateKey 私钥
         */
        private ByteKey(PublicKey publicKey, PrivateKey privateKey) {
            this.publicKey = publicKey.getEncoded();
            this.privateKey = privateKey.getEncoded();
        }

        /**
         * 获取公钥
         *
         * @return 返回公钥
         */
        public byte[] getPublicKey() {
            return publicKey;
        }

        /**
         * 获取私钥，byte[]
         *
         * @return 返回私钥，byte[]
         */
        public byte[] getPrivateKey() {
            return privateKey;
        }

    }

    /**
     * String数据类型的密钥对
     */
    public static class StringKey {

        /**
         * 公钥
         */
        private final String publicKey;

        /**
         * 私钥
         */
        private final String privateKey;

        /**
         * 构造器
         *
         * @param publicKey  公钥
         * @param privateKey 私钥
         */
        private StringKey(PublicKey publicKey, PrivateKey privateKey) {
            this.publicKey = Base64.encodeBase64String(publicKey.getEncoded());
            this.privateKey = Base64.encodeBase64String(privateKey.getEncoded());
        }

        /**
         * 获取公钥
         *
         * @return 返回公钥，String
         */
        public String getPublicKey() {
            return publicKey;
        }

        /**
         * 获取私钥
         *
         * @return 返回私钥，String
         */
        public String getPrivateKey() {
            return privateKey;
        }

    }

}

