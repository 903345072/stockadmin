package com.beta.web.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.configurers.AbstractConfigAttributeRequestMatcherRegistry;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.AntPathMatcher;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 是否登陆验证方法
 * @author 程就人生
 * @date 2019年5月26日
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {
    private RequestMatcher requestMatcher;
    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.requestMatcher = AnyRequestMatcher.INSTANCE;
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return requestMatcher.matches(request);
    }
    /**
     * 對請求進行過濾
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {

        try {

            //请求体的头中是否包含Authorization
            if(request.getRequestURI().startsWith("/stockServer")){ //不处理websocket请求
                chain.doFilter(request, response);
                return;
            }
            String header = request.getHeader("Authorization");
            //Authorization中是否包含Bearer，有一个不包含时直接返回
            if (header == null || !header.startsWith("Bearer ")) {
                chain.doFilter(request, response);
                responseJson(response);
                return;
            }
            //获取权限失败，会抛出异常\
            UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
            //获取后，将Authentication写入SecurityContextHolder中供后序使用
            SecurityContextHolder.getContext().setAuthentication(authentication);
            chain.doFilter(request, response);
        }catch (Exception e) {
            responseJson(response);
            e.printStackTrace();
        }
    }

    /**
     * 未登錄時的提示
     * @param response
     */
    private void responseJson(HttpServletResponse response){
        try {
            //未登錄時，使用json進行提示
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            PrintWriter out = response.getWriter();
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("code", HttpServletResponse.SC_FORBIDDEN);
            map.put("message","请登录啊！");
            out.write(new ObjectMapper().writeValueAsString(map));
            out.flush();
            out.close();
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (token != null) {
            //通过token解析出用户信息

            Claims userinfo =  Jwts.parser().setSigningKey("MyJwtSecret")
                    .parseClaimsJws(token.replace("Bearer ", "")).getBody();
            ArrayList<HashMap> authorities = (ArrayList) ((HashMap) userinfo.get("userinfo")).get("authorities");
            String username = (String)((HashMap) userinfo.get("userinfo")).get("username");
            ArrayList<GrantedAuthority> authorities_ = new ArrayList<>();
            for (HashMap v: authorities) {
                authorities_.add(new SimpleGrantedAuthority((String) v.get("authority")));
            }
            if (username != null) {
                return new UsernamePasswordAuthenticationToken(userinfo.get("userinfo"), null, authorities_);
            }
            return null;
        }
        return null;
    }

}
