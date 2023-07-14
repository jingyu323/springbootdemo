package com.rain.es.test;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.TransportUtils;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import com.sun.xml.internal.ws.api.ResourceLoader;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import sun.misc.Resource;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class EsConnectionTest {
    public static void main(String[] args) throws IOException {
        URL resourceUrl = EsConnectionTest.class.getClassLoader().getResource("certs/http_ca.crt");

        InputStream inputStream = EsConnectionTest.class.getResourceAsStream("/certs/http_ca.crt");


        File certFile = new File(resourceUrl.getPath());

        SSLContext sslContext = TransportUtils
                .sslContextFromHttpCaCrt(certFile);
        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(
                AuthScope.ANY, new UsernamePasswordCredentials("elastic", "V6DycNLQVPeYKhBLPZUO")
        );
        String serverUrl = "https://192.168.99.118:9200";

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

        esClient.indices().create(c -> c
                .index("products")
        );

    }
}
