package com.serverless.ems.app.config;

import com.amazonaws.client.builder.AwsClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

@Configuration
public class DynamoDBConfig {

        @Bean
        public AmazonDynamoDB amazonDynamoDB() {
            return AmazonDynamoDBClientBuilder.standard()
                    .build();
        }

        @Bean
        public DynamoDBMapper dynamoDBMapper() {
            return new DynamoDBMapper(amazonDynamoDB());
        }
}
