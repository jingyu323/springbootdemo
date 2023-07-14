package com.rain.es.test;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.raintest.pojo.Product;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import java.io.IOException;

public class EsConection {
    public static void main(String[] args) throws IOException {
        // URL and API key
        String serverUrl = "https://192.168.99.118:9200";
        String apiKey = "OWxUM1Rva0JqcU5ocUI1bVVzRkM6VUZUWlZPeFhTclNSRGpLanNPRm9lUQ==";


// Create the low-level client
        RestClient restClient = RestClient
                .builder(HttpHost.create(serverUrl))
                .setDefaultHeaders(new Header[]{
                        new BasicHeader("Authorization", "ApiKey " + apiKey)
                })
                .build();

// Create the transport with a Jackson mapper
        ElasticsearchTransport transport = new RestClientTransport(
                restClient, new JacksonJsonpMapper());

// And create the API client
        ElasticsearchClient esClient = new ElasticsearchClient(transport);


        esClient.indices().create(c -> c
                .index("products")
        );


//        Product product = new Product("bk-1", "City bike", 123.0);
//
//        IndexResponse response = esClient.index(i -> i
//                .index("products")
//                .id(product.getSku())
//                .document(product)
//        );
    }
}
