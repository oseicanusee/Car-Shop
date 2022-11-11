//package com.carshop.CarShop.configuration;
//
//import com.carshop.CarShop.Service.Impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//
//public class AuthenticationFilter extends OncePerRequestFilter {
//    @Autowired
//    private JwtService jwtService;
//    @Autowired
//    private UserServiceImpl userServiceImpl;
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//            throws ServletException, IOException {
//        String jws = request.getHeader(HttpHeaders.AUTHORIZATION);
//
//        if (jws != null) {
//            String user = jwtService.getAuthUser(request);
//
//            // Authenticate
//            Authentication authentication =
//                    new UsernamePasswordAuthenticationToken(user, null, java.util.Collections.emptyList());
//
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        }
//
//        filterChain.doFilter(request, response);
//    }
//
//}
