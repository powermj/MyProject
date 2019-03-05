package com.zhxg.framework.base.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpHost;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EsRestClient {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private RestClient restClient;
	private static EsRestClient esRestClient;

	public EsRestClient() {
		try {
			String ecn = PropertiesUtil.getValue("elasticsearch_cluster_nodes");
			String ip = ecn.split(":")[0];
			String port = ecn.split(":")[1];
			restClient = RestClient.builder(new HttpHost(ip, Integer.parseInt(port), "http")).build();
		} catch (Exception e) {
			this.logger.error("ERROR restClient init", e);
		}
	}

	public static EsRestClient getInstance() {
		if (null == esRestClient) {
			esRestClient = new EsRestClient();
		}
		return esRestClient;
	}

	public void close() {
		if(null!=restClient) {
			try {
				restClient.close();
			} catch (IOException e) {
				this.logger.error("ERROR restClient close", e); 
			}
		}
	}
	public static String readResposne(Response response) throws Exception {
        BufferedReader brd = new BufferedReader(new BufferedReader(new InputStreamReader(response.getEntity().getContent())));
        String line;
        StringBuilder respongseContext = new StringBuilder();

        while ((line = brd.readLine()) != null) {
            respongseContext.append(line).append("\n");
        }
        if (respongseContext.length() > 0) {
            respongseContext.deleteCharAt(respongseContext.length() - 1);
        }
        return respongseContext.toString();
    }

	public RestClient getRestClient() {
		return restClient;
	}

	public void setRestClient(RestClient restClient) {
		this.restClient = restClient;
	}

	public EsRestClient getEsRestClient() {
		return esRestClient;
	}
}
