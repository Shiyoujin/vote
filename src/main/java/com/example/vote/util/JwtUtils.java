package com.example.vote.util;

import com.sun.javafx.scene.traversal.Algorithm;
import com.sun.org.apache.xml.internal.security.algorithms.JCEMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {
    /**
     * 设置过期时间 15分钟
     *
     *  token 私钥
     *
     *  加密解密都需要用到它
     *  uuid，加密方式是HMAC256
     */
     private static final String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e618";
    /**
     * @param u_id
     * @return
     */
    public static String jwtSignIn(String u_id){
        long now  = System.currentTimeMillis();
        long EXPIRE_TIME = now + 15 * 60 * 1000;


        //指定签名的时候使用的签名算法，也就是header那部分，jjwt已经将这部分内容封装好了。
        //SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;  这里下面已经直接使用了

        //创建payload的私有声明（根据特定的业务需要添加，如果要拿这个做验证，一般是需要和jwt的接收方提前沟通好验证方式的）
        Map<String,Object> claims = new HashMap<String,Object>(1000);
        claims.putIfAbsent("u_id",u_id);
        //生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。
        //它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        SecretKey key = generalKey();
        //下面就是在为payload添加各种标准声明和私有声明了
        //这里其实就是new一个JwtBuilder，设置jwt的body
        JwtBuilder builder = Jwts.builder()
                //如果有私有声明，一定要先设置这个自己创建的私有的声明，
                //这个是给builder的claim赋值，一旦写在标准的声明赋值之后，就是覆盖了那些标准的声明的
                .setClaims(claims)
                //设置jti(JWT ID)：是JWT的唯一标识，根据业务需要，
                //这个可以设置为一个不重复的值，主要用来作为一次性token,从而回避重放攻击。
                .setId("002")
                //sub(Subject)：代表这个JWT的主体，即它的所有人，
                //这个是一个json格式的字符串，可以存放什么userid，roldid之类的，作为什么用户的唯一标志
                .setSubject("{\"u_id\":"+ u_id)
                //iat: jwt的签发时间
                .setIssuedAt(new Date(now))
                //设置签名使用的签名算法和签名使用的秘钥
                .signWith(SignatureAlgorithm.HS256,key)
                //设置过期时间，注意 new Date()
                .setExpiration(new Date(EXPIRE_TIME));

        return builder.compact();
    }

    /**
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public Claims parseJWT(String jwt) throws Exception{
        //签名秘钥，和生成的签名的秘钥一模一样
        SecretKey key = generalKey();
        //得到DefaultJwtParser
        Claims claims = Jwts.parser()
                //设置签名的秘钥
                .setSigningKey(key)
                //设置需要解析的jwt
                .parseClaimsJws(jwt).getBody();
        return claims;
    }

    /**
     * 由字符串生成加密key
     * @return
     */
    public static SecretKey generalKey(){
        //本地配置(文件)中加密的密文
        String stringkey = TOKEN_SECRET;
        //本地的密码解码
        byte[] encodedkey = Base64.decodeBase64(stringkey);
        //// 根据给定的字节数组使用AES加密算法构造一个密钥，
        // 使用 encodedKey中的始于且包含 0 到前 leng 个字节这里当然是所有。
        SecretKey key = new SecretKeySpec(encodedkey,0,encodedkey.length,"AES");
        return key;
    }
}
