package com.othello.config;

import java.io.File;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.ajp.AbstractAjpProtocol;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class TomcatConfig {



 @Bean
  public WebServerFactoryCustomizer<TomcatServletWebServerFactory> servletContainer() {
    return server -> {
      if (server != null) {
        server.setBaseDirectory(new File("./public"));
        server.addAdditionalTomcatConnectors(redirectConnector());
      }
    };
  }



 private Connector redirectConnector() {
    Connector connector = new Connector("AJP/1.3");
    connector.setScheme("http");
    connector.setPort(7654);
    connector.setSecure(true);
    connector.setAllowTrace(false);
    ((AbstractAjpProtocol<Object>) connector.getProtocolHandler()).setSecretRequired(false);
    return connector;
  }
}