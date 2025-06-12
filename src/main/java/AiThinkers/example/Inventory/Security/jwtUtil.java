package AiThinkers.example.Inventory.Security;


import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class jwtUtil {
    private final String jwtSecret = "iamusingthisanmysuperSecretKeyheretooverthesecretkeyKeySizementionedintheconsolewhereitshouldbelongerandmorelongerhere"; // Use env variable in prod!
    private final long jwtExpirationMs = 2 * 60 * 60 * 1000; //  2-Hours


    //Here We Are Generating the Tokens Which use to be stored in cache
    //so that the user can log in without any login credentials Until 24hrs

    //later user need to login again with credentials so another token will be
    //generated again the process repeats by generating the token

    //here the subject was the Userdetails it verify the token and token contain userEmail/phoneno
    public String generateToken(String Userdetails) {
        return Jwts.builder()
                .setSubject(Userdetails)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    //claims here are claiming the user details that true to login by token

    //it has Header - algorithm and type-"jwt" and
    // Payload-which contain issue date and expiration time and name
    //Signature - by this we need to verify
    //their will an encoded version.

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

