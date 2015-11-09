package com.banking.api;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

@Configuration
public class OAuth2ServerConfiguration {

    private static final String RESOURCE_ID = "test_resource";

    private enum Scope {

        READ, WRITE;

        public String getLabel() {
            return name().toLowerCase();
        }
    }

    @Configuration
    @EnableWebSecurity
    protected static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
        
        @Autowired
        private DataSource dataSource;
        
        @Autowired
        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.authenticationProvider(new AuthenticationProvider() {
//                
//                @Override
//                public boolean supports(Class<?> authentication) {
//                    // TODO Auto-generated method stub
//                    return false;
//                }
//                
//                @Override
//                public Authentication authenticate(Authentication authentication)
//                        throws AuthenticationException {
//                    
//                    // TODO Auto-generated method stub
//                    return null;
//                }
//            });
            auth.jdbcAuthentication().dataSource(dataSource);//.passwordEncoder(passwordEncoder());
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
        
        @Bean
        public PasswordEncoder passwordEncoder() {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder;
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
        
        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private DataSource dataSource;
        
        @Bean
        public JdbcTokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }
        
        @Bean
        public AuthorizationCodeServices authorizationCodeServices() {
            return new JdbcAuthorizationCodeServices(dataSource);
        }
        
        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints
            .authorizationCodeServices(authorizationCodeServices())
            .authenticationManager(authenticationManager)
            .tokenStore(tokenStore())
            .approvalStoreDisabled();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients.jdbc(dataSource);
        }
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

        @Autowired
        private TokenStore tokenStore;
        
        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources
                .resourceId(RESOURCE_ID)
                .tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            String readAndWrite = "#oauth2.isUser() and (#oauth2.hasScope('" + 
                                        Scope.READ.getLabel() + 
                                    "') or #oauth2.hasScope('" + 
                                        Scope.WRITE.getLabel() + 
                                    "'))";
            String write = "#oauth2.isUser() and #oauth2.hasScope('" + 
                                Scope.WRITE.getLabel() + 
                            "')";
            
            http    
                .requestMatchers()
                    .antMatchers(	
                                    "/me", 
                                    "/accounts", 
                                    "/accounts/*/transfers"
                                )
            .and()
                .authorizeRequests()
                    .antMatchers(HttpMethod.GET, 
                                    "/me", 
                                    "/accounts",
                                    "/accounts/*/transfers"
                                ).access(readAndWrite)
                    .antMatchers(HttpMethod.POST, 
                                    "/me", 
                                    "/accounts",
                                    "/accounts/*/transfers"
                                ).access(write);
        }
    }
}
