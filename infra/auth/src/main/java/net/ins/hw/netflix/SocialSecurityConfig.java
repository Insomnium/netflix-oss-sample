package net.ins.hw.netflix;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SocialSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    private AuthApplicationProperties properties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/", "/login")
                    .permitAll()
                .anyRequest()
                    .authenticated();
    }

//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//
//        properties.getOrigins().forEach(config::addAllowedOrigin);
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
}
