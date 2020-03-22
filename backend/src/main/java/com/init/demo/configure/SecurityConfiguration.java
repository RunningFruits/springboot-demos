package com.init.demo.configure;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.init.demo.entity.system.SystemUser;
import com.init.demo.pojo.system.SecurityUser;
import com.init.demo.repository.SystemUserRepository;
import com.init.demo.service.system.SystemLogService;
import com.init.demo.utils.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SystemLogService systemLogService;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //并根据传入的AuthenticationManagerBuilder中的userDetailsService方法来接收我们自定义的认证方法。
        //且该方法必须要实现UserDetailsService这个接口。
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
        auth.eraseCredentials(false);

//        auth.userDetailsService(userDetailsService()).passwordEncoder(new PasswordEncoder() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence charSequence, String s) {
//                return s.equals(charSequence.toString());
//            }
//        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin() //  定义当需要用户登录时候，转到的登录页面。
//                .loginPage("/login") // 设置登录页面
//                //.loginProcessingUrl("/login") // 自定义的登录接口
//                .and().authorizeRequests() // 定义哪些URL需要被保护、哪些不需要被保护
//                .antMatchers("/login.html").permitAll() // 设置所有人都可以访问登录页面
//                .anyRequest() // 任何请求,登录后可以访问
//                .authenticated().and().csrf().disable(); // 关闭csrf防护

//        http.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                //指定登录页的路径
//                .loginPage("/login.html")
//                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
//                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
//                .permitAll();

//        http.authorizeRequests().antMatchers("/login").permitAll().anyRequest().authenticated().
//                and().formLogin().loginPage("/login").loginProcessingUrl("/login/userLogin").permitAll();
//        http
//                //http.authorizeRequests()方法有多个子节点，每个macher按照他们的声明顺序执行
//                .authorizeRequests()
//
//                //我们指定任何用户都可以访问多个URL的模式。
//                //任何用户都可以访问以"/resources/","/signup", 或者 "/about"开头的URL。
//                .antMatchers("/resources/**", "/signup", "/about").permitAll()
//
//                //以 "/admin/" 开头的URL只能让拥有 "ROLE_ADMIN"角色的用户访问。
//                //请注意我们使用 hasRole 方法，没有使用 "ROLE_" 前缀。
//                .antMatchers("/admin/**").hasRole("ADMIN")
//
//                //任何以"/db/" 开头的URL需要同时具有 "ROLE_ADMIN" 和 "ROLE_DBA"权限的用户才可以访问。
//                //和上面一样我们的 hasRole 方法也没有使用 "ROLE_" 前缀。
//                .antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
//
//                //任何以"/db/" 开头的URL只需要拥有 "ROLE_ADMIN" 和 "ROLE_DBA"其中一个权限的用户才可以访问。
//                //和上面一样我们的 hasRole 方法也没有使用 "ROLE_" 前缀。
//                .antMatchers("/db/**").hasAnyRole("ADMIN", "DBA")
//
//                //尚未匹配的任何URL都要求用户进行身份验证
//                .anyRequest().authenticated()
//                .and()
//                // ...
//                .formLogin();

        http.authorizeRequests() // 如果有允许匿名的url，填在下面 //                .antMatchers().permitAll()
                .anyRequest().authenticated().and() // 设置登陆页
                .formLogin().loginPage("/loginPage")
                .loginProcessingUrl("/login")//发送Ajax请求的路径
                .usernameParameter("username")//请求验证参数
                .passwordParameter("password")//请求验证参数
                // 设置登陆成功页
                .defaultSuccessUrl("/index")
                .permitAll().successHandler(loginSuccessHandler()).failureHandler(loginFailureHandler()) //.failureUrl("/error") // 自定义登陆用户名和密码参数，默认为username和password //
                //  .usernameParameter("username") //
                //  .passwordParameter("password")
                .and().logout().permitAll().invalidateHttpSession(true).
                deleteCookies("JSESSIONID").logoutSuccessHandler(logoutSuccessHandler()).
                and().sessionManagement().maximumSessions(10).expiredUrl("/loginPage");
        http.csrf().disable();// 关闭CSRF跨域
        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**", "/js/**", "/lib/**", "/iconfont/**", "/images/**");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() { //密码加密
        return new BCryptPasswordEncoder(4);
    }

    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() { //登出处理
        return new LogoutSuccessHandler() {
            @Override
            public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                SecurityUser user = null;
                try {
                    user = (SecurityUser) authentication.getPrincipal();
                    log.info("USER : " + user.getUsername() + " LOGOUT SUCCESS !  ");
                    systemLogService.saveLog("登出成功", user.getUsername());
                } catch (Exception e) {
                    log.info("LOGOUT EXCEPTION , e : " + e.getMessage());
                    if (user != null)
                        systemLogService.saveLog("登出失败", user.getUsername());
                    else
                        systemLogService.saveLog("登出失败", user.getUsername());
                }
                httpServletResponse.sendRedirect("/loginPage");
            }
        };
    }

    @Bean
    public AuthenticationFailureHandler loginFailureHandler() { //登入失败处理
        return new SimpleUrlAuthenticationFailureHandler() {
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                ResponseResult jsonData = null;
                if (exception.getMessage().equals("用户不存在")) {
                    jsonData = new ResponseResult("用户不存在", null, 402);
                }
                if (exception.getMessage().equals("Bad credentials")) {
                    jsonData = new ResponseResult("用户名或密码错误", null, 403);
                }
                if (!StringUtils.isEmpty(jsonData)) {
                    String json = objectMapper.writeValueAsString(jsonData);//包装成Json 发送的前台
                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(json);
                    out.flush();
                    out.close();
                }
            }
        };
    }

    @Bean
    public SavedRequestAwareAuthenticationSuccessHandler loginSuccessHandler() { //登入处理
        return new SavedRequestAwareAuthenticationSuccessHandler() {
            @Override
            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                SecurityUser userDetails = (SecurityUser) authentication.getPrincipal();
                logger.info("USER : " + userDetails.getUsername() + " LOGIN SUCCESS !  ");
                systemLogService.saveLog("登录成功", userDetails.getUsername());
//                super.onAuthenticationSuccess(request, response, authentication);
                ResponseResult jsonData = new ResponseResult("认证OK", null, 200);
                String json = objectMapper.writeValueAsString(jsonData);
                response.setContentType("application/json;charset=utf-8");
                PrintWriter out = response.getWriter();
                out.write(json);
                out.flush();
                out.close();
            }
        };
    }

    @Bean
    public UserDetailsService userDetailsService() {    //用户登录实现
        return new UserDetailsService() {
            @Autowired
            private SystemUserRepository systemUserRepository;

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

                SystemUser systemUser = systemUserRepository.findByLoginNameAndDel(username, false);
                if (systemUser != null) {
                    return new SecurityUser(systemUser);
                } else throw new UsernameNotFoundException("Username " + username + " not found");
            }
        };
    }
}
