package com.home.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.home.entity.AppUser;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
public class JWTService {
    @Value("${jwt.algorithm.key}")
    private String algorithmKey;

    @Value("${jwt.issuer}")
    private String issuer;
    @Value("${jwt.expiryDuration}")
    private int expiryTime;

    private Algorithm algorithm;
//    private final static String USER_NAME = "name";
    @PostConstruct
    public  void postConstruct(){
        algorithm = Algorithm.HMAC256(algorithmKey);
//         System.out.println(issuer);
    }
    public String generateToken(AppUser appUser){
        return JWT.create().withClaim("name",appUser.getName())
                .withExpiresAt(new Date(System.currentTimeMillis()+expiryTime))
                .withIssuer(issuer)
                .sign(algorithm);
    }
    public String getUserName(String token){
        DecodedJWT decodedJwt = JWT.require(algorithm).withIssuer(issuer).build().verify(token);
        return decodedJwt.getClaim("name").asString();
    }
}










