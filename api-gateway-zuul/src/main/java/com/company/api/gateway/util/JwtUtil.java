package com.company.api.gateway.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.company.api.gateway.model.UserInfo;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtUtil {
    //token到期时间60s
    private static final Long EXPIRE_TIME= 5*60*1000L;

    private static final String SECRET_KEY = "SECRET_KEY";

    /**
     * 生成token
     * @param userInfo
     * @return
     */
    public static String generateToken(UserInfo userInfo){
        String token = null ;
        try{
            Date expire_date = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            token= JWT.create()
                    .withIssuer("auth")
                    .withClaim("userName", userInfo.getUserName())
                    .withClaim("password", userInfo.getPassword())
                    .withExpiresAt(expire_date)
                    .sign(Algorithm.HMAC256(SECRET_KEY));
        }catch (Exception e){
            log.error(e.getMessage());
            token = null;
        }
        return token;
    }

    /**
     * 验证token
     * @param token
     * @return
     */
    public static Boolean validateToken(String token){
        boolean flag=true;
        try {
            //创建token验证器
            JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(SECRET_KEY)).withIssuer("auth").build();
            DecodedJWT decodedJWT=jwtVerifier.verify(token);
        }catch (Exception e){
            log.error(e.getMessage());
            flag=false;
        }
        return flag;
    }
}
