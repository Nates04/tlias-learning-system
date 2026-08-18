package com.wust.ems;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
class EmsApplicationTests {

    //生成JWT
    //@Test
    public String testJWT(){
        //令牌存入的内容
        Map<String,Object> map=new HashMap<>();
        map.put("id",1);
        map.put("name","tom");

        //密钥长度要大于32
        String jwt=Jwts.builder()//构建令牌
                //签名算法，和密钥
                .signWith(SignatureAlgorithm.HS256,"mySecretKey12345678901234567890")
                .setClaims(map)//自定义内容，载荷
                .setExpiration(new Date(System.currentTimeMillis()+1000*3600))//设置令牌过期时间
                .compact();//生成令牌的字符串
        return jwt;
    }

    //JWT令牌的解析
    // @Test
    public void getJWT(){
        Map<String,Object> map=Jwts.parser()
                .setSigningKey("mySecretKey12345678901234567890")//设置的密钥
                //JWT令牌的字符串
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJuYW1lIjoidG9tIiwiaWQiOjEsImV4cCI6MTc4NzA0NjcyM30.oehKW3mhoD6NYKOnlATlH3iyBS2gkIhJv_kjSSBJBI0")
                .getBody();//读出里面的内容
        map.forEach((String k,Object v)->{
            System.out.println(k+":"+v);
        });
    }

}
