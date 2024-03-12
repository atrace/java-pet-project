package com.accenture.nge.anjali.petproject.configuration;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.accenture.nge.anjali.petproject.repository")
public class DynamoDBConfig {

  @Value("${config.aws.region}")
  private String region;
  @Value("${config.aws.dynamodb.url}")
  private String dynamoDbEndpointUrl;
  @Value("${config.aws.dynamodb.access-key}")
  private String accessKey;
  @Value("${config.aws.dynamodb.secret-key}")
  private String secretKey;

  @Bean(name = "amazonDynamoDB")
  public AmazonDynamoDB amazonDynamoDB() {
    var amazonDynamoDB = AmazonDynamoDBClientBuilder.standard()
      .withCredentials(
        new AWSStaticCredentialsProvider(
          new BasicAWSCredentials(accessKey, secretKey)
        )
      )
      .withEndpointConfiguration(
        new EndpointConfiguration(dynamoDbEndpointUrl, region)
      )
      .build();
    return amazonDynamoDB;
  }
}
