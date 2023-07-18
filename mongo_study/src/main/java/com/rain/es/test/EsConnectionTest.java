package com.rain.es.test;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
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
import java.util.List;

public class EsConnectionTest {
    private static Logger logger = LoggerFactory.getLogger(EsConnectionTest.class);
    static ElasticsearchClient esClient = null;

    static {
        SSLContext sslContext = null;
        try {
            sslContext = TransportUtils
                    .sslContextFromHttpCaCrt(new File("src/main/java/com/rain/es/test/http_ca.crt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials("elastic", "V6DycNLQVPeYKhBLPZUO")
        );


        SSLContext finalSslContext = sslContext;
        RestClient restClient = RestClient
                .builder(new HttpHost("192.168.99.118", 9200, "https"))
                .setHttpClientConfigCallback(hc -> hc
                        .setSSLContext(finalSslContext)
                        .setDefaultCredentialsProvider(credsProv)
                )
                .build();

// Create the transport and the API client
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        esClient = new ElasticsearchClient(transport);
    }

    public static void main(String[] args) throws IOException {
        String index = "raintest";

//        createIndex(index);
        addElementToIndex(index);

    }

    public static void addElementToIndex(String index) throws IOException {
        Product product = new Product("bk-31", "City bik31", 325.0);

        IndexResponse response = esClient.index(i -> i
                .index(index)
                .id(product.getName())
                .document(product)
        );

        logger.info("Indexed with version " + response.version());
    }

    public static void searchByterm() throws IOException {
        // term 查询是 index 对象中的字段才行
        SearchResponse<Product> search = esClient.search(s -> s
                        .index("products")
                        .query(q -> q
                                .term(t -> t
                                        .field("_id")
                                        .value(v -> v.stringValue("bk-2"))
                                )),
                Product.class);

        for (Hit<Product> hit : search.hits().hits()) {
            System.out.println("==================");
            System.out.println(hit.source().getName());

        }
    }

    public static void checkItemExist() throws IOException {
        if (esClient.exists(b -> b.index("products").id("bk-2")).value()) {
            logger.info("product exists");
        }

    }

    public static void createIndex(String indexName) {
        try {
            esClient.indices().create(c -> c
                    .index(indexName)
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void queryItemByMatch() throws IOException {
        String searchText = "bk-2";

        //  match 可以查询
        SearchResponse<Product> response = esClient.search(s -> s
                        .index("products")
                        .query(q -> q
                                .match(t -> t
                                        .field("name")
                                        .query(searchText)
                                )
                        ),
                Product.class
        );


        TotalHits total = response.hits().total();
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        if (isExactResult) {
            logger.info("There are " + total.value() + " results");
        } else {
            logger.info("There are more than " + total.value() + " results");
        }

        List<Hit<Product>> hits = response.hits().hits();
        for (Hit<Product> hit : hits) {
            Product product = hit.source();
            logger.info("Found product " + product.getName() + ", score " + hit.score());
        }
    }
}
