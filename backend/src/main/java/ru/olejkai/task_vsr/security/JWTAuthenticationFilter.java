package ru.olejkai.task_vsr.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.olejkai.task_vsr.services.authServices.CustomUserDetailsServices;

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
        LOG.info("Filter begin");
        try {
            String jwt =getJWTFromRequest(request);
            if(jwt==null)
                throw new NullPointerException();
            LOG.info(jwt);
            if(StringUtils.hasText(jwt)&&jwtTokenProvider.validationToken(jwt)){
                Long userId=jwtTokenProvider.getUserIdFromToken(jwt);
                LOG.info(userId.toString());
                EmployeeEntity employeeDetail=customUserDetailsServices.loadUserById(userId);

                UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(
                        employeeDetail,null, Collections.emptyList()
                );
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e ) {
            LOG.error( e.getMessage());
            LOG.error(e.getMessage() + " error Filter User Authentication class: JWTAuthenticationFilter method: doFilterInternal ");
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.getWriter().write(convertObjectToJson(e));
        }
       LOG.info("Filter end");
            filterChain.doFilter(request,response);


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


/*public class JWTAuthenticationFilter extends OncePerRequestFilter {
    public static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    private JWTTokenProvider jwtTokenProvider;
    @Autowired
    private CustomUserDetailsServices customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = getJWTFromRequest(httpServletRequest);
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validationToken(jwt)) {
                Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
                EmployeeEntity userDetails = customUserDetailsService.loadUserById(userId);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, Collections.emptyList()
                );

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));;
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            LOG.error("Could not set user authentication");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearToken = request.getHeader(SecurityConstants.HEADER_STRING);
        if (StringUtils.hasText(bearToken) && bearToken.startsWith(SecurityConstants.TOKEN_PREFIX)) {
            return bearToken.split(" ")[1];
        }
        return null;
    }


}*/
