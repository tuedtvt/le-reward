package com.ptc.loyalty.reward.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsProcessor;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.DefaultCorsProcessor;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class CustomCorsFilter extends OncePerRequestFilter {

  private final String[] allowedMethods = {"OPTIONS", "GET", "POST", "DELETE", "PUT", "PATCH"};

  @Value("${corsConfiguration.allowedOrigins:}")
  private final List<String> allowedOrigins;

  @Value("${corsConfiguration.allowedAllOrigin:false}")
  private Boolean allowedAllOrigin;

  private final CorsProcessor processor = new DefaultCorsProcessor();

  @Override
  protected void doFilterInternal(@NonNull final HttpServletRequest request,
      @NonNull final HttpServletResponse response,
      @NonNull final FilterChain filterChain) throws ServletException, IOException {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    final CorsConfiguration config = new CorsConfiguration();
    config.setAllowCredentials(true);
    if (allowedAllOrigin){
      config.addAllowedOriginPattern("*");
    }
    else {
      config.setAllowedOrigins(allowedOrigins);
    }
    config.setAllowedMethods(List.of(allowedMethods));
    config.addAllowedHeader("*");
    source.registerCorsConfiguration("/**", config);
    final CorsConfiguration corsConfiguration = source.getCorsConfiguration(request);
    final boolean isValid = this.processor.processRequest(corsConfiguration, request, response);
    if (!isValid || CorsUtils.isPreFlightRequest(request)) {
      return;
    }
    filterChain.doFilter(request, response);
  }
}
