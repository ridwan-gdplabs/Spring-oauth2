package com.banking.api;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
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

import com.banking.api.models.oauth2.Authority;
import com.banking.api.repositories.oauth2.AuthorityRepository;

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
    protected static class WebSecurityConfiguration extends
            WebSecurityConfigurerAdapter {

        @Autowired
        private DataSource dataSource;

        @Autowired
        @Override
        public void configure(AuthenticationManagerBuilder auth)
                throws Exception {
            // auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder());
            auth.userDetailsService(userDetailsService()).passwordEncoder(
                    passwordEncoder());
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean()
                throws Exception {
            return super.authenticationManagerBean();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            String groupAuthoritiesQuery = "select g.group_id, g.group_name, ga.authority "
                    + "from groups g, group_members gm, group_authorities ga, users u "
                    + "where u.username = ? "
                    + "and g.group_id = ga.group_id "
                    + "and g.group_id = gm.group_id "
                    + "and gm.username = u.username "
                    + "and g.enabled = true "
                    + "and u.enabled = true";

            JdbcDaoImpl jdbcDaoImpl = new JdbcDaoImpl();
            jdbcDaoImpl.setDataSource(dataSource);
            jdbcDaoImpl.setEnableGroups(true);
            jdbcDaoImpl
                    .setGroupAuthoritiesByUsernameQuery(groupAuthoritiesQuery);
            jdbcDaoImpl.setEnableAuthorities(false);
            return jdbcDaoImpl;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            return encoder;
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {

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
        public void configure(AuthorizationServerEndpointsConfigurer endpoints)
                throws Exception {
            endpoints.authorizationCodeServices(authorizationCodeServices())
                    .authenticationManager(authenticationManager)
                    .tokenStore(tokenStore()).approvalStoreDisabled();
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients)
                throws Exception {
            clients.jdbc(dataSource);
        }
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Autowired
        private AuthorityRepository authorityRepository;

        @Autowired
        private TokenStore tokenStore;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources)
                throws Exception {
            resources.resourceId(RESOURCE_ID).tokenStore(tokenStore);
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            String readOrWrite = "#oauth2.isUser() and (#oauth2.hasScope('"
                    + Scope.READ.getLabel() + "') or #oauth2.hasScope('"
                    + Scope.WRITE.getLabel() + "'))";
            String write = "#oauth2.isUser() and #oauth2.hasScope('"
                    + Scope.WRITE.getLabel() + "')";

            String readOrWriteAccess = "";
            String writeAccess = "";
            List<String> readUrl = new ArrayList<String>();
            List<String> writeUrl = new ArrayList<String>();
            List<String> requestUrl = new ArrayList<String>();

            Iterable<Authority> authorities = authorityRepository.findAll();
            for (Authority authority : authorities) {
                requestUrl.add(authority.getAntPattern());

                if ("READ".equals(authority.getScope())) {
                    if ("".equals(readOrWriteAccess)) {
                        readOrWriteAccess = " hasRole('"
                                + authority.getAuthority() + "')";
                    } else {
                        readOrWriteAccess += " or hasRole('"
                                + authority.getAuthority() + "')";
                    }
                    readUrl.add(authority.getAntPattern());
                } else if ("WRITE".equals(authority.getScope())) {
                    if ("".equals(writeAccess)) {
                        writeAccess = " hasRole('" + authority.getAuthority()
                                + "')";
                    } else {
                        writeAccess += " or hasRole('"
                                + authority.getAuthority() + "')";
                    }
                    writeUrl.add(authority.getAntPattern());
                }
            }

            readOrWriteAccess = readOrWrite + " and (" + readOrWriteAccess
                    + ")";
            writeAccess = write + " and (" + writeAccess + ")";
            System.out.println(readOrWriteAccess);
            System.out.println(writeAccess);

            String requestUrlArray[] = requestUrl.toArray(new String[0]);
            String readUrlArray[] = readUrl.toArray(new String[0]);
            String writeUrlArray[] = writeUrl.toArray(new String[0]);

            http.requestMatchers().antMatchers(requestUrlArray).and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.GET, readUrlArray)
                    .access(readOrWriteAccess)
                    .antMatchers(HttpMethod.POST, writeUrlArray)
                    .access(writeAccess);
        }
    }
}
