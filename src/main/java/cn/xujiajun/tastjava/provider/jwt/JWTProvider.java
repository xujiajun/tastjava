package cn.xujiajun.tastjava.provider.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.pmw.tinylog.Logger;

import java.io.UnsupportedEncodingException;

public class JWTProvider {

    private static String JWTSecret = "tastjavasecret";
    private static String JWTIssuer = "tastjava";

    public static String createToken(int uid) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWTSecret);
            String token = JWT.create()
                    .withIssuer(JWTIssuer)
                    .withClaim("uid", uid)
                    .sign(algorithm);
            return token;
        } catch (UnsupportedEncodingException exception) {
            //UTF-8 encoding not supported
        } catch (JWTCreationException exception) {
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return null;

    }

    public static DecodedJWT verifyToken(String token) {
//        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXUyJ9.eyJpc3MiOiJhdXRoMCJ9.AbIJTDMFc7yUa5MhvcP03nJPyCPzZtQcGEp-zWfOkEE";
        try {
            Algorithm algorithm = Algorithm.HMAC256(JWTSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(JWTIssuer)
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);
            return jwt;
        } catch (UnsupportedEncodingException exception) {
            Logger.info(exception);
            //UTF-8 encoding not supported
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            Logger.info(exception);
        }

        return null;
    }
}
