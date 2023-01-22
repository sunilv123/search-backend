package com.assignment.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories(
  basePackages = "com.assignment.es"
)
@ComponentScan(basePackages = { "com.assignment.es" })
@Configuration
public class ElasticConfig {

  @Value("${aws.es.endpoint}")
  private String endpoint;

  @Value("${aws.es.port}")
  private int port;
   @Bean
   public RestClient getRestClient() {
      RestClient restClient = RestClient.builder(new HttpHost(endpoint, port)).build();
      return restClient;
   }
   @Bean
   public ElasticsearchTransport getElasticsearchTransport() {
      return new RestClientTransport(getRestClient(), new JacksonJsonpMapper());
   }
   @Bean
   public ElasticsearchClient getElasticsearchClient(){
      ElasticsearchClient client = new ElasticsearchClient(getElasticsearchTransport());
      return client;
   }
}
