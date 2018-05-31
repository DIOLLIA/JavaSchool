package schedule.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
              /*  .usersByUsernameQuery(
                        "select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from user_roles where username=?");*/
                .usersByUsernameQuery(
                        "SELECT email,password,enable FROM users WHERE email=?")
                .authoritiesByUsernameQuery(
                        "SELECT email, role FROM users WHERE email=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
// hasRole('ROLE_ADMIN') заменено на hasRole('1')
        http.authorizeRequests()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')").antMatchers("/station/**").access("hasRole('ROLE_ADMIN')").antMatchers("/train/**").access("hasRole('ROLE_ADMIN')").antMatchers("/user/**").access("hasRole('ROLE_ADMIN')").antMatchers("/ticket/**").access("hasRole('ROLE_USER')")
                .and()
                .formLogin().loginPage("/signIn").failureUrl("/signIn?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/signIn?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403")
                .and()
                .csrf();
    }
}