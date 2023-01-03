package com.example.demo;

import java.util.List;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.server.EndpointInterceptor;
import org.springframework.ws.soap.server.endpoint.interceptor.PayloadValidatingInterceptor;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@EnableAsync
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

  @Override
  public void addInterceptors(List<EndpointInterceptor> interceptors) {
    PayloadValidatingInterceptor validatingInterceptor = new PayloadValidatingInterceptor();
    validatingInterceptor.setValidateRequest(true);
    validatingInterceptor.setValidateResponse(true);
    validatingInterceptor.setXsdSchema(securitySchema());
    interceptors.add(validatingInterceptor);
  }

  @Bean
  public ServletRegistrationBean messageDispatcherServlet(ApplicationContext applicationContext) {
    MessageDispatcherServlet servlet = new MessageDispatcherServlet();
    servlet.setApplicationContext(applicationContext);
    servlet.setTransformWsdlLocations(true);
    return new ServletRegistrationBean(servlet, "/ws/*");
  }

  @Bean(name = "security")
  public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema schema) {
    DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
    wsdl11Definition.setPortTypeName("SecurityPort");
    wsdl11Definition.setLocationUri("/ws");
    wsdl11Definition.setTargetNamespace("http://localhost:8080/demo/gen");
    wsdl11Definition.setSchema(schema);
    return wsdl11Definition;
  }

  @Bean
  public XsdSchema securitySchema() {
    return new SimpleXsdSchema(new ClassPathResource("security.xsd"));
  }
}