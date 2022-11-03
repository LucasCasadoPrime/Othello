package com.othello.config;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.util.StringUtils;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;


@Configuration
//@EnableMongoRepositories(basePackages = {"com.othello"})
public class MongoConfig extends AbstractMongoClientConfiguration {
    
 private static final Logger LOG = LoggerFactory.getLogger(MongoConfig.class);
 @Value("${mongo.db.servers}")
  private String mongoServers;
  @Value("${mongo.db.authenticationdb}")
  private String mongoDbAuthenticationDB;
  @Value("${mongo.db.login}")
  private String mongoDbLogin;
  @Value("${mongo.db.password}")
  private String mongoDbPassword;
  @Value("${mongo.db.name}")
  private String mongoDBName;

 @Override
  protected String getDatabaseName() {
    return mongoDBName;
  }
 
 @Bean
  @Lazy
  public MongoRepositoryFactory mongoRepositoryFactory(MongoTemplate mongoTemplate) throws Exception {
    return new MongoRepositoryFactory(mongoTemplate);
  }
 
 @Override
  public MongoClient mongoClient() {
    final String[] mongoServersList = mongoServers.split(",");
    List<ServerAddress> serverAdr = new ArrayList<>();
    for (String mongoServer : mongoServersList) {
      String host = mongoServer.split(":")[0].trim();
      String port = mongoServer.split(":")[1].trim();
      serverAdr.add(new ServerAddress(host, Integer.parseInt(port)));
    }
    MongoClientSettings.Builder settings = MongoClientSettings.builder()
            .writeConcern(WriteConcern.ACKNOWLEDGED)
            .applyToClusterSettings(builder -> builder.hosts(serverAdr).build());
    if (!StringUtils.isEmpty(mongoDbLogin)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("mongoClient : Configuring mongo with credentials.");
      }
      settings.credential(MongoCredential.createCredential(mongoDbLogin, mongoDbAuthenticationDB, mongoDbPassword.toCharArray()));
      return MongoClients.create(settings.build());
    }
    else {
      if (LOG.isDebugEnabled()) {
        LOG.debug("mongoClient : Configuring mongo without credentials.");
      }
      return MongoClients.create(settings.build());
    }
  }

}
