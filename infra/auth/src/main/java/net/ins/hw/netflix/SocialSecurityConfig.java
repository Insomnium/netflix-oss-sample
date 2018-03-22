package net.ins.hw.netflix;

import net.ins.hw.netflix.config.AuthApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.boot.autoconfigure.security.oauth2.resource.UserInfoTokenServices;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientAuthenticationProcessingFilter;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CompositeFilter;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableOAuth2Client // Exactly on a class that extends WebSecurityConfigurerAdapter!
public class SocialSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthApplicationProperties properties;

    @Autowired
    private OAuth2ClientContext oAuth2ClientContext;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/", "/login**", "/index.html")
                    .permitAll()
                .anyRequest()
                    .authenticated()
                .and()
                    .addFilterBefore(ssoFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }

    private Filter ssoFilter() {
        CompositeFilter filter = new CompositeFilter();
        List<Filter> filters = new ArrayList<>();

        filters.add(ssoFilter(github(), githubResource(), "/login/github"));
        filters.add(ssoFilter(facebook(), facebookResource(), "/login/facebook"));

        filter.setFilters(filters);
        return filter;
    }

    private OAuth2ClientAuthenticationProcessingFilter ssoFilter(OAuth2ProtectedResourceDetails client,
                                                                 ResourceServerProperties resource,
                                                                 String loginUri) {
        OAuth2ClientAuthenticationProcessingFilter socialFilter = new OAuth2ClientAuthenticationProcessingFilter(loginUri);
        OAuth2RestTemplate socialTemplate = new OAuth2RestTemplate(client, oAuth2ClientContext);
        socialFilter.setRestTemplate(socialTemplate);
        UserInfoTokenServices tokenServices = new UserInfoTokenServices(resource.getUserInfoUri(), client.getClientId());
        tokenServices.setRestTemplate(socialTemplate);
        socialFilter.setTokenServices(tokenServices);
        return socialFilter;
    }

    @Bean
    @ConfigurationProperties("github.client")
    public OAuth2ProtectedResourceDetails github() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("github.resource")
    public ResourceServerProperties githubResource() {
        return new ResourceServerProperties();
    }

    @Bean
    @ConfigurationProperties("facebook.client")
    public OAuth2ProtectedResourceDetails facebook() {
        return new AuthorizationCodeResourceDetails();
    }

    @Bean
    @ConfigurationProperties("facebook.resource")
    public ResourceServerProperties facebookResource() {
        return new ResourceServerProperties();
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        properties.getOrigins().forEach(config::addAllowedOrigin);
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
}
