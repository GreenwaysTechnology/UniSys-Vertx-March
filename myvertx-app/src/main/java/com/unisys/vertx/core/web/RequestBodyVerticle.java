package com.unisys.vertx.core.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;

public class RequestBodyVerticle extends AbstractVerticle {
	int port = 3002;

	@Override
	public void start() throws Exception {

		// configure webservers
		HttpServerOptions options = new HttpServerOptions();
		options.setPort(port);

		HttpServer httpServer = vertx.createHttpServer(options);

		// Handling Client Request
		httpServer.requestHandler(req -> {
			HttpServerResponse response = req.response();

			// read data from client
			req.handler(chunk -> {
				System.out.println(chunk.toString());
			});

			response.end("<h1>Hello Vertx Web Server</h1>");
		});

		httpServer.listen(listenHandler -> {
			if (listenHandler.succeeded()) {
				System.out.println("Server is Up : " + listenHandler.result());
			} else {
				System.out.println(listenHandler.cause());
			}
		});

	}

}
