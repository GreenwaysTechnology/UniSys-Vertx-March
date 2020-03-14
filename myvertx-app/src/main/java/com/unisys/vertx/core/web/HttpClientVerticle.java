package com.unisys.vertx.core.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpMethod;

public class HttpClientVerticle extends AbstractVerticle {
	HttpClientOptions options;
	HttpClient client;

	@Override
	public void start() throws Exception {
		options = new HttpClientOptions();
		client = vertx.createHttpClient(options);
		// making request
		//Way -1
		client.getNow(8888, "localhost", "/", response -> {
			System.out.println("Received response with status code " + response.statusCode());
			response.bodyHandler(result -> {
				System.out.println(result);
			});
		});
        //way 2
		client.request(HttpMethod.GET, 8888, "localhost", "/", response -> {
			System.out.println("Received response with status code " + response.statusCode());
			response.bodyHandler(result -> {
				System.out.println(result);
			});
		}).end();
        //way 3
		client.get(8888, "localhost", "/", response -> {
			System.out.println("Received response with status code " + response.statusCode());
			response.bodyHandler(result -> {
				System.out.println(result);
			});
		}).end();
	}

	@Override
	public void stop() throws Exception {
	}
}