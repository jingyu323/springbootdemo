package com.rain.test.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.aggregations.HistogramBucket;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.SearchTemplateResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import co.elastic.clients.json.JsonData;
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
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class EsConnectionTest {
    private static Logger logger = LoggerFactory.getLogger(EsConnectionTest.class);
    static ElasticsearchClient esClient = null;
    public static String serverIp = "192.168.99.187";

    static {
        SSLContext sslContext = null;
        try {
            sslContext = TransportUtils
                    .sslContextFromHttpCaCrt(new File("src/main/resources/cert/http_ca.crt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials("elastic", "BzevJY1OL-kEBnw*ZJBu")
        );


        SSLContext finalSslContext = sslContext;
        RestClient restClient = RestClient
                .builder(new HttpHost(serverIp, 9200, "https"))
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
        String index = "products001";


//        testaggregations();

//        createIndex("products001");
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

    public static void getIndex(String index) throws IOException {


    }

    public static void nestSearch(String index) throws IOException {

        String searchText = "bike";
        double maxPrice = 200.0;

// Search by product name
        Query byName = MatchQuery.of(m -> m
                .field("name")
                .query(searchText)
        )._toQuery();

// Search by max price
        Query byMaxPrice = RangeQuery.of(r -> r
                .field("price")
                .gte(JsonData.of(maxPrice))
        )._toQuery();

// Combine name and price queries to search the product index
        SearchResponse<Product> response = esClient.search(s -> s
                        .index("products")
                        .query(q -> q
                                .bool(b -> b
                                        .must(byName)
                                        .must(byMaxPrice)
                                )
                        ),
                Product.class
        );

        List<Hit<Product>> hits = response.hits().hits();
        for (Hit<Product> hit : hits) {
            Product product = hit.source();
            logger.info("Found product " + product.getName() + ", score " + hit.score());
        }
    }

    public static void rawJson(String index) throws IOException {
        Reader input = new StringReader(
                "{'@timestamp': '2022-04-08T13:55:32Z', 'level': 'warn', 'message': 'Some log message'}"
                        .replace('\'', '"'));

        IndexRequest<JsonData> request = IndexRequest.of(i -> i
                .index("logs")
                .withJson(input)
        );

        IndexResponse response = esClient.index(request);
        logger.info("Indexed with version " + response.version());

    }

    /**
     * 使用模板查询
     *
     * @throws IOException
     */
    public static void templateSearch() throws IOException {
        SearchTemplateResponse<Product> response = esClient.searchTemplate(r -> r
                        .index("products")
                        .id("my-search-template")
                        .params("query_string", JsonData.of("125.0"))
                        .params("from", JsonData.of("0"))
                        .params("size", JsonData.of("10")),
                Product.class
        );

        List<Hit<Product>> hits = response.hits().hits();
        for (Hit<Product> hit : hits) {
            Product product = hit.source();
            logger.info("Found product " + product.getName() + ", score " + hit.score());
        }
    }

    public static void testaggregations() throws IOException {
        String searchText = "bk";

        Query query = MatchQuery.of(m -> m
                .field("name")
                .query(searchText)
        )._toQuery();

        SearchResponse<Void> response = esClient.search(b -> b
                        .index("products")
                        .size(0)
                        .query(query)
                        .aggregations("price-histogram", a -> a
                                .histogram(h -> h
                                        .field("price")
                                        .interval(10.0)
                                )
                        ),
                Void.class
        );


        List<HistogramBucket> buckets = response.aggregations()
                .get("price-histogram")
                .histogram()
                .buckets().array();

        for (HistogramBucket bucket : buckets) {
            logger.info("There are " + bucket.docCount() +
                    " bikes under " + bucket.key());
        }

    }


    public void testIndexJson() {
        String source = "{\"name\":\"will\",\"age\":18}";

        Product product = new Product("bk-31", "City bik31", 325.0);
 
    }


}