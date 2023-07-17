package com.rain.es.test;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;

public class EsConnectionTest {
    private static Logger logger = LoggerFactory.getLogger(EsConnectionTest.class);

    public static void main(String[] args) throws IOException {


        SSLContext sslContext = TransportUtils
                .sslContextFromHttpCaCrt(new File("src/main/java/com/rain/es/test/http_ca.crt"));
        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials("elastic", "V6DycNLQVPeYKhBLPZUO")
        );


        RestClient restClient = RestClient
                .builder(new HttpHost("192.168.99.118", 9200, "https"))
                .setHttpClientConfigCallback(hc -> hc
                        .setSSLContext(sslContext)
                        .setDefaultCredentialsProvider(credsProv)
                )
                .build();

// Create the transport and the API client
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient esClient = new ElasticsearchClient(transport);

//        esClient.indices().create(c -> c
//                .index("products")
//        );


        Product product = new Product("bk-2", "City bik2e", 125.0);

        IndexResponse response = esClient.index(i -> i
                .index("products")
                .id(product.getName())
                .document(product)
        );

        logger.info("Indexed with version " + response.version());

        if (esClient.exists(b -> b.index("products").id("bk-1")).value()) {
            logger.info("product exists");
        }

        SearchResponse<Product> search = esClient.search(s -> s
                        .index("products")
                        .query(q -> q
                                .term(t -> t
                                        .field("name")
                                        .value(v -> v.stringValue("bk-2"))
                                )),
                Product.class);

        for (Hit<Product> hit : search.hits().hits()) {
            System.out.println("==================");
            System.out.println(hit.source().getName());

        }

    }
}
