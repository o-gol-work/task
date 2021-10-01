package ru.olejkai.task_vsr.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.services.CustomUserDetailsServices;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;


public class JWTAuthenticationFilter extends OncePerRequestFilter {

    public static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    private  JWTTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailsServices customUserDetailsServices;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt =getJWTFromRequest(request);
            if(StringUtils.hasText(jwt)&&jwtTokenProvider.validationToken(jwt)){
                Long userId=jwtTokenProvider.getUserIdFromToken(jwt);
                EmployeeEntity employeeDetail=customUserDetailsServices.loadUserById(userId);

                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(
                        employeeDetail,null, Collections.emptyList()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            LOG.error(e.getMessage() + "error Filter User Authentication class: JWTAuthenticationFilter method: doFilterInternal ");
            filterChain.doFilter(request,response);
        }


    }

    private String getJWTFromRequest(HttpServletRequest request){
        String bearToken=request.getHeader(SecurityConstants.HEADER_STRING);
        if(StringUtils.hasText(bearToken) && bearToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
            return bearToken.split(" ")[1];

        }
        return null;

    }
}
