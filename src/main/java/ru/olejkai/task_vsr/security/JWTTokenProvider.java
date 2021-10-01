package ru.olejkai.task_vsr.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import ru.olejkai.task_vsr.entity.EmployeeEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTTokenProvider {
    public static final Logger LOG = LoggerFactory.getLogger(JWTTokenProvider.class);

    public String generateToken(Authentication authentication){
        EmployeeEntity employee=(EmployeeEntity)authentication.getPrincipal();
        Date now=new Date(System.currentTimeMillis());
        Date expiryDat = new Date (now.getTime() + SecurityConstants.EXPIRATION_TIME);

        String employeeId = Long.toString(employee.getId());

        Map<String , Object> claimsMap= new HashMap<>();

        claimsMap.put("id",employeeId);
        claimsMap.put("username",Integer.toString(employee.getTabelNumber()));
        claimsMap.put("name",employee.getName());
        claimsMap.put("surname",employee.getSurname());

        return Jwts.builder()
                .setSubject(employeeId)
                .addClaims(claimsMap)
                .setIssuedAt(now)
                .setExpiration(expiryDat)
                .signWith(SignatureAlgorithm.ES512, SecurityConstants.SECRET)
                .compact();



    }

    public boolean validationToken (String token){
        try {
            Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException |
                MalformedJwtException|
                ExpiredJwtException |
                UnsupportedJwtException |
                IllegalArgumentException e
                    ){
            LOG.error(e.getMessage());
            return false;
        }
    }

    public Long getUserIdFromToken(String token){
        Claims claims=Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getId());
    }
}
