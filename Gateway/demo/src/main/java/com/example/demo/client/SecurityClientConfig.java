package com.example.demo.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.beans.factory.annotation.Value;

@Configuration
@EnableAspectJAutoProxy
public class SecurityClientConfig {
	
  @Value("${security.default.uri}")	
  private String securityDefaultUri;	

  @Bean
  public Jaxb2Marshaller marshaller() {
    Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
    marshaller.setContextPath("com.example.demo.client.gen");
    return marshaller;
  }

  @Bean
  public SecurityClient securityClient(Jaxb2Marshaller marshaller) {
    SecurityClient client = new SecurityClient();
    client.setDefaultUri(this.securityDefaultUri);
    client.setMarshaller(marshaller);
    client.setUnmarshaller(marshaller);
    return client;
  }
}
