package com.kpo.restaurantsystem.config;

import com.kpo.restaurantsystem.web.security.SecurityTokenFilter;
import com.kpo.restaurantsystem.web.security.SecurityTokenProvider;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableWebSecurity
@EnableAsync
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class AppConfig {
    private final SecurityTokenProvider personTokenProvider;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("auth"))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        "auth",
                                        new SecurityScheme()
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")
                                )
                )
                .info(
                        new Info()
                                .title("Homework on КПО (Restaurant)")
                                .description("An application for processing orders in a restaurant.")
                                .version("1.0")
                );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(final AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(sessionManagement ->
                        sessionManagement
                                .sessionCreationPolicy(
                                        SessionCreationPolicy.STATELESS
                                )
                )
                .exceptionHandling(configurer ->
                        configurer
                                .authenticationEntryPoint(
                                        (request, response, authException) ->
                                        {
                                            response.setStatus(
                                                    HttpStatus.UNAUTHORIZED.value()
                                            );
                                            response.getWriter().write("Unauthorized");

                                        })
                                .accessDeniedHandler(
                                        ((request, response, accessDeniedException) -> {
                                            response.setStatus(
                                                    HttpStatus.UNAUTHORIZED.value()
                                            );
                                            response.getWriter().write("Access denied");
                                        }
                                        )
                                ))
                .authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/api/v1/dishes/admin/**")
                                .hasRole("ADMIN")
                                .requestMatchers("/api/v1/auth/**")
                                .permitAll()
                                .requestMatchers("/swagger-ui/**")
                                .permitAll()
                                .requestMatchers("/v3/api-docs/**")
                                .permitAll()
                                .anyRequest().authenticated()
                )
                .anonymous(AbstractHttpConfigurer::disable)
                .addFilterBefore(new SecurityTokenFilter(personTokenProvider),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    /*@Bean(name = "orderProcessExecutor")
    public AsyncTaskExecutor onePriorityExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("OrderProcess-");
        executor.setThreadPriority(5);
        executor.initialize();
        return executor;
    }*/

    @Bean(name = "orderProcessExecutor")
    public ExecutorService orderProcessExecutor() {
        return Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors(),
                runnable -> {
                    Thread thread = Executors.defaultThreadFactory().newThread(runnable);
                    thread.setPriority(5);
                    return thread;
                }
        );
    }

    @Bean(name = "addDishAmountExecutor")
    public ExecutorService addDishAmountExecutor() {
        return Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors(),
                runnable -> {
                    Thread thread = Executors.defaultThreadFactory().newThread(runnable);
                    thread.setPriority(6);
                    return thread;
                }
        );
    }

    @Bean(name = "cancelOrderExecutor")
    public ExecutorService cancelOrderExecutor() {
        return Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors(),
                runnable -> {
                    Thread thread = Executors.defaultThreadFactory().newThread(runnable);
                    thread.setPriority(7);
                    return thread;
                }
        );
    }
}
