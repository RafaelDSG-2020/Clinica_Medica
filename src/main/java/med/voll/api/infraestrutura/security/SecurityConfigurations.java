package med.voll.api.infraestrutura.security;


import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        return http.csrf()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().authorizeRequests()
//                .antMatchers(HttpMethod.POST, "/login").permitAll()
//                .anyRequest().authenticated()
//                .and().build();
//    }

//@Bean // colocando que apenas usuario ADMIN pode deletar medicos e pacientes
//public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//    return http.csrf().disable()
//            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//            .and().authorizeHttpRequests()
//            .requestMatchers(HttpMethod.POST, "/login").permitAll()
//            .requestMatchers(HttpMethod.DELETE, "/medicos").hasRole("ADMIN")
//            .requestMatchers(HttpMethod.DELETE, "/pacientes").hasRole("ADMIN")
//            .anyRequest().authenticated()
//            .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
//            .build();
//}




    @Bean //Nova forma de colocar STATELESS
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return
                http.csrf(csrf -> csrf.disable())
                        .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                        .authorizeRequests(req -> {
                            req.antMatchers("/login").permitAll();
                            req.anyRequest().authenticated();
                        })
                        .build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{

        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
