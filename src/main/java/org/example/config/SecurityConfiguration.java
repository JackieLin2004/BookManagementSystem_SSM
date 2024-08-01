package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Security配置类
 */
@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
//        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我功能实现
     *
     * @param dataSource
     * @return
     */
    @Bean
    public PersistentTokenRepository tokenRepository(DataSource dataSource) {
        JdbcTokenRepositoryImpl repository = new JdbcTokenRepositoryImpl();
        repository.setDataSource(dataSource);
//        repository.setCreateTableOnStartup(true);
        return repository;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           PersistentTokenRepository repository) throws Exception {
        return http
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/static/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                .formLogin(config -> {
                    config.loginPage("/login");
                    config.loginProcessingUrl("/doLogin");
                    config.defaultSuccessUrl("/");
                    config.permitAll();
                })
                .csrf(AbstractHttpConfigurer::disable)
                .rememberMe(config -> {
                    config.tokenRepository(repository);
                    config.tokenValiditySeconds(3600 * 24 * 7);
                })
                .build();
    }
}
