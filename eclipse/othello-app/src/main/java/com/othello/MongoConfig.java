package com.othello;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.util.StringUtils;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

@Configuration
@EnableMongoRepositories(basePackageClasses = {Display.class})
@PropertySource({ "classpath:othello.properties" })
public class MongoConfig extends AbstractMongoClientConfiguration  {

  private static final Logger LOG = LoggerFactory.getLogger(MongoConfig.class);

  @Value("${mongo.db.servers}")
  private String mongoServers;
  @Value("${mongo.db.authenticationdb}")
  private String mongoDbAuthenticationDB;
  @Value("${mongo.db.login}")
  private String mongoDbLogin;
  @Value("${mongo.db.password}")
  private String mongoDbPassword;

  private SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd-HHmmss");
  
  private String mongoDBName="orion-test-acl-" + format.format(new Date());

  @Override
  protected String getDatabaseName() {
    return mongoDBName;
  }

  @Bean
  public MongoTemplate mongoTemplate() throws Exception {
    return mongoTemplate(mongoDbFactory(), mappingMongoConverter(mongoDbFactory(), new MongoCustomConversions(new ArrayList<>()), new MongoMappingContext()));
  }

  @Bean
  public MongoRepositoryFactory mongoRepositoryFactory() throws Exception {
    return new MongoRepositoryFactory(mongoTemplate());
  }

  @Override
  @Bean
  public MongoClient mongoClient() {
    final String[] mongoServersList = mongoServers.split(",");
    List<ServerAddress> serverAdr = new ArrayList<>();
    for (String mongoServer : mongoServersList) {
      String host = mongoServer.split(":")[0].trim();
      String port = mongoServer.split(":")[1].trim();
      serverAdr.add(new ServerAddress(host, Integer.parseInt(port)));
    }
    if (!StringUtils.isEmpty(mongoDbLogin)) {
      if (LOG.isDebugEnabled()) {
        LOG.debug("mongoClient : Configuring mongo with credentials.");
      }
      MongoCredential credential = MongoCredential.createCredential(mongoDbLogin, mongoDbAuthenticationDB, mongoDbPassword.toCharArray());
      return MongoClients.create(MongoClientSettings.builder()
              .applyToClusterSettings(builder -> builder.hosts(serverAdr).build())
              .credential(credential)
              .writeConcern(WriteConcern.ACKNOWLEDGED)
              .build());
    }
    else {
      if (LOG.isDebugEnabled()) {
        LOG.debug("mongoClient : Configuring mongo without credentials.");
      }
      return MongoClients.create(MongoClientSettings.builder().applyConnectionString(new ConnectionString("mongodb://"+serverAdr))
              .writeConcern(WriteConcern.ACKNOWLEDGED)
              .build());
    }
  }

public String getMongoServers() {
    return mongoServers;
}

public void setMongoServers(String mongoServers) {
    this.mongoServers = mongoServers;
}

public String getMongoDbAuthenticationDB() {
    return mongoDbAuthenticationDB;
}

public void setMongoDbAuthenticationDB(String mongoDbAuthenticationDB) {
    this.mongoDbAuthenticationDB = mongoDbAuthenticationDB;
}

public String getMongoDbLogin() {
    return mongoDbLogin;
}

public void setMongoDbLogin(String mongoDbLogin) {
    this.mongoDbLogin = mongoDbLogin;
}

public String getMongoDbPassword() {
    return mongoDbPassword;
}

public void setMongoDbPassword(String mongoDbPassword) {
    this.mongoDbPassword = mongoDbPassword;
}

public String getMongoDBName() {
    return mongoDBName;
}

public void setMongoDBName(String mongoDBName) {
    this.mongoDBName = mongoDBName;
}

}
