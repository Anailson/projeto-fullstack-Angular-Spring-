package com.example.algamoney.api.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configurable
@EnableAuthorizationServer
public class AuthorizationServerConfig  extends AuthorizationServerConfigurerAdapter{
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {

		clients.inMemory()
				.withClient("angular")//nome do cliente
				.secret("@ngul@r0")
				.scopes("read", "write")//scope limpar o acesso do cliente angular
				.authorizedGrantTypes("password", "refresh_token")
				.accessTokenValiditySeconds(1800)//tempo para cessar app
				.refreshTokenValiditySeconds(3600 * 24)  // defini os dias 
				
				//OUTRO CLIENT : mobile
				.and()
					.withClient("mobile")
					.secret("m0b1l30")
					.scopes("read")
					.authorizedGrantTypes("password", "refresh_token")
					.accessTokenValiditySeconds(1800)
					.refreshTokenValiditySeconds(3600 * 24);
					
								
	}	
	

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		
		endpoints
				.tokenStore(tokenStore())
				.accessTokenConverter(acessTokenConverter())
				.reuseRefreshTokens(false)
				.authenticationManager(authenticationManager);
	}
	
	@Bean
	public JwtAccessTokenConverter acessTokenConverter() {
		JwtAccessTokenConverter acceAccessTokenConverter = new JwtAccessTokenConverter();
		acceAccessTokenConverter.setSigningKey("algaworks");//chave que valida o token
		
		return acceAccessTokenConverter;
	}


	@Bean
	public TokenStore tokenStore() {
		
		return new JwtTokenStore(acessTokenConverter());
			
	}
	
}
//6.6. Renovando o access token com o refresh token
