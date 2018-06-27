package schedule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Bean(name = "userDetailsService")
    public UserDetailsService userDetailsService() {
        JdbcDaoImpl jdbcImpl = new JdbcDaoImpl();
        jdbcImpl.setDataSource(dataSource);
        jdbcImpl.setUsersByUsernameQuery("SELECT email,password,true FROM users WHERE email=?");
        jdbcImpl.setAuthoritiesByUsernameQuery("SELECT email, role FROM users u LEFT join role ON u.role_id = role.id WHERE email=?");
        return jdbcImpl;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/searchForUser/**").access("hasRole('ROLE_USER')").
                antMatchers("/mainSearch/**").access("hasAnyRole('ROLE_USER','ROLE_ADMIN','ROLE_ANONYMOUS')")
                .antMatchers("/station/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/train/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/ticket/**").access("hasRole('ROLE_USER')")
                .antMatchers("/userInfo/").access("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
                .antMatchers("/schedule/**").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/signIn").failureUrl("/signIn?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/signIn?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .addFilterAfter(new MyFilter(), BasicAuthenticationFilter.class)
                .csrf();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }
}