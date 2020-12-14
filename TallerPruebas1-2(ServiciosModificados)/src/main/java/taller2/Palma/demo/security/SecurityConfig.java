package taller2.Palma.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;

//	@Autowired
//	private MyCustomUserDetailsService userDetailsService;

//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(authenticationProvider());
//	}

//	@Bean
//	public DaoAuthenticationProvider authenticationProvider() {
//		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//		authProvider.setUserDetailsService(userDetailsService);
//		authProvider.setPasswordEncoder(encoder());
//		return authProvider;
//	}
//
//	@Bean
//	public PasswordEncoder encoder() {
//		return new BCryptPasswordEncoder(11);
//	}
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {

//		httpSecurity.authorizeRequests().antMatchers("/**").authenticated().anyRequest().permitAll().and().formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/").failureUrl("/login")
//		.and().logout().permitAll()
//				.and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
		
//		httpSecurity.formLogin().loginPage("/login").permitAll().and().authorizeRequests()
//		.antMatchers("/index").permitAll().antMatchers("/person","/docs","/docinst","/docType").permitAll()
//		.anyRequest().authenticated().and().httpBasic().and().logout().invalidateHttpSession(true).clearAuthentication(true)
//		.and().logout().invalidateHttpSession(true).clearAuthentication(true).logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//		.logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
//		
//		httpSecurity.authorizeRequests().antMatchers("/**").authenticated().anyRequest().permitAll().and().formLogin().and().logout()
//        .invalidateHttpSession(true).clearAuthentication(true)
//        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
//        .permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	/**
		httpSecurity.authorizeRequests().antMatchers("/secure/**").authenticated().anyRequest().permitAll().and().httpBasic().and().logout()
		.invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
		.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);*/
	}
}