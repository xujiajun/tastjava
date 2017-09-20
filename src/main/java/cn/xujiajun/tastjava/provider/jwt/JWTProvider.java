package cn.xujiajun.tastjava.provider.jwt;

import cn.xujiajun.tastjava.util.PropsUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class JWTProvider {

    private static String JWTSecret;
    private static String JWTIssuer = "tastjava";

    static {
        Properties conf = PropsUtil.loadProps("jwt.properties");
        JWTSecret = conf.getProperty("jwt.secret");
    }
    public static String createToken(int uid) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWTSecret);
            String token = JWT.create()
                    .withIssuer(JWTIssuer)
                    .withClaim("uid", uid)
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception) {
            throw new RuntimeException("Exception in createToken,msg: " + exception.getMessage() + exception.toString(), exception);
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
            throw new RuntimeException("Exception in createToken,msg: " + exception.getMessage() + exception.toString(), exception);
        }
    }

    public static DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWTSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWTIssuer)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (UnsupportedEncodingException exception) {
            throw new RuntimeException("Exception in verifyToken,msg: " + exception.getMessage() + exception.toString(), exception);
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            throw new RuntimeException("Exception in verifyToken,msg: " + exception.getMessage() + exception.toString(), exception);
        }
    }
}
