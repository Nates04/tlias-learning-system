package com.wust.ems.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Jwt {

    //密钥
    private static String signKey="mySecretKey12345678901234567890";
    //JWT令牌的有效时间
    private static Long expire=3600000L;

    //获得JWT令牌
    public static String getJWT(Map<String,Object> map){
        //密钥长度要大于32
        String jwt= Jwts.builder()//构建令牌
                //签名算法，和密钥
                .signWith(SignatureAlgorithm.HS256,signKey)
                .setClaims(map)//自定义内容，载荷
                .setExpiration(new Date(System.currentTimeMillis()+expire))//设置令牌过期时间
                .compact();//生成令牌的字符串
        return jwt;
    }

    //解析JWT令牌
    public static Claims parseJWT(String jwt){
        Claims claims=Jwts.parser()
                .setSigningKey(signKey)//设置的密钥
                //JWT令牌的字符串
                .parseClaimsJws(jwt)
                .getBody();//读出里面的内容
        return claims;
    }
}
