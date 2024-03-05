package com.rain.test.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;

public class EsTest {
    public static String apiKey = "OGRjdTZJMEI0cnRZclEyNGpzN2g6Q016aGJzR2RTOS1TanV2My16WFIyQQ==";
    public static String serverUrl = "https://192.168.99.187:9200";
    public static String serverIp = "192.168.99.187";
    static ElasticsearchClient esClient = null;

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
        esClient.indices().create(c -> c
                .index("products0023")
        );
    }
}
