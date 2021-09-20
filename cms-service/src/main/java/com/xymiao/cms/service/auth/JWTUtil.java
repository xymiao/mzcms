package com.xymiao.cms.service.auth;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class JWTUtil {
    private final static Logger log = LoggerFactory.getLogger(JWTUtil.class);
    // 携带token的请求头名字
    public final static String TOKEN_HEADER = "Authorization";
    //token的前缀
    public final static String TOKEN_PREFIX = "Bearer ";
    // 默认密钥
    public final static String DEFAULT_SECRET = "mySecret";
    // 用户身份
    private final static String ROLES_CLAIM = "roles";
    // token有效期,单位分钟；
    private final static long EXPIRE_TIME = 5 * 60 * 1000;
    // 设置Remember-me功能后的token有效期
    private final static long EXPIRE_TIME_REMEMBER = 7 * 24 * 60 * 60 * 1000;

    // 创建token
    public static String createToken(String username, List role, String secret, boolean rememberMe) {

        Date expireDate = rememberMe ? new Date(System.currentTimeMillis() + EXPIRE_TIME_REMEMBER) : new Date(System.currentTimeMillis() + EXPIRE_TIME);
        try {
            // 创建签名的算法实例
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT.create()
                    .withExpiresAt(expireDate)
                    .withClaim("username", username)
                    .withClaim(ROLES_CLAIM, role)
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException jwtCreationException) {
            log.warn("Token create failed");
            return null;
        }
    }

    // 验证token
    public static boolean verifyToken(String token, String secret) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            // 构建JWT验证器，token合法同时pyload必须含有私有字段username且值一致
            // token过期也会验证失败
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            // 验证token
            DecodedJWT decodedJWT = verifier.verify(token);
            return true;
        } catch (JWTVerificationException jwtVerificationException) {
            log.warn("token验证失败");
            return false;
        }

    }

    // 获取username
    public static String getUsername(String token) {
        try {
            // 因此获取载荷信息不需要密钥
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException jwtDecodeException) {
            log.warn("提取用户姓名时，token解码失败");
            return null;
        }
    }

    public static List<String> getRole(String token) {
        try {
            // 因此获取载荷信息不需要密钥
            DecodedJWT jwt = JWT.decode(token);
            // asList方法需要指定容器元素的类型
            return jwt.getClaim(ROLES_CLAIM).asList(String.class);
        } catch (JWTDecodeException jwtDecodeException) {
            log.warn("提取身份时，token解码失败");
            return null;
        }
    }
}