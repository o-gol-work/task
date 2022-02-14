package ru.olejkai.task_vsr.security;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.olejkai.task_vsr.entity.EmployeeEntity;
import ru.olejkai.task_vsr.payload.request.SignupRequest;
import ru.olejkai.task_vsr.services.authServices.CustomUserDetailsServices;
import ru.olejkai.task_vsr.util.CachedBodyHttpServletRequest;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collections;
import java.util.Scanner;


public class JWTAuthenticationFilter extends OncePerRequestFilter {

    public static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    private  JWTTokenProvider jwtTokenProvider;


    @Autowired
    private CustomUserDetailsServices customUserDetailsServices;




   @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOG.info("Filter begin");
       CachedBodyHttpServletRequest cachedBodyHttpServletRequest = new CachedBodyHttpServletRequest(request);
        try {


            String body=reader(cachedBodyHttpServletRequest);
            LOG.info(body);
            ObjectMapper objectMapper=new ObjectMapper();
            SignupRequest signupRequest=null;
            try {
                signupRequest = objectMapper.readValue(cachedBodyHttpServletRequest.getReader(), SignupRequest.class);
            }catch (Exception e){

            }
            String jwt =getJWTFromRequest(request);




            if(jwt==null && signupRequest!=null)
                throw new NullPointerException();
            LOG.info(jwt);
            if(StringUtils.hasText(jwt)&&jwtTokenProvider.validationToken(jwt)){
                Long userId=jwtTokenProvider.getUserIdFromToken(jwt);
                LOG.info(userId.toString());
                EmployeeEntity employeeDetail=customUserDetailsServices.loadUserById(userId);

                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(
                        employeeDetail,null, employeeDetail.getAuthorities()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                LOG.info("Filter end");

            }
        } catch (Exception e ) {
            LOG.error( e.getMessage());
            LOG.error(e.getMessage() + " error Filter User Authentication class: JWTAuthenticationFilter method: doFilterInternal ");
            LOG.info(convertObjectToJson(e));
        }


       filterChain.doFilter(
               cachedBodyHttpServletRequest
               ,response);

   }


   private String reader(HttpServletRequest request) throws IOException {
       StringBuilder sb = new StringBuilder();

       BufferedReader reader = request.getReader();
       try {
           String line;
           while ((line = reader.readLine()) != null) {
               sb.append(line).append('\n');
           }
       } finally {
           reader.close();
       }
       String str=sb.toString();
       LOG.info(str);
       return str;
   }








     public String convertObjectToJson(Object object) throws JsonProcessingException {
         if (object == null) {
             return null;
         }
         ObjectMapper mapper = new ObjectMapper();
         return mapper.writeValueAsString(object);
     }


    private String getJWTFromRequest(HttpServletRequest request){
        String bearToken=request.getHeader(SecurityConstants.HEADER_STRING);
        LOG.info(bearToken);
        if(StringUtils.hasText(bearToken) && bearToken.startsWith(SecurityConstants.TOKEN_PREFIX)){
            return bearToken.split(" ")[1];

        }
        return null;

    }
}


