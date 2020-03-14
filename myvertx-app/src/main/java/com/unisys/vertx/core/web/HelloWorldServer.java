package com.unisys.vertx.core.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;

public class HelloWorldServer extends AbstractVerticle {

	int port = 3002;

	@Override
	public void start() throws Exception {

		// configure webservers
		HttpServerOptions options = new HttpServerOptions();
		options.setPort(port);

		HttpServer httpServer = vertx.createHttpServer(options);

		// Handling Client Request
		httpServer.requestHandler(request -> {
			HttpServerResponse response = request.response();
			response.end("<h1>Hello Vertx Web Server</h1>");
		});

		// Start the http Server
//		httpServer.listen(port, listenHandler -> {
//			if (listenHandler.succeeded()) {
//				System.out.println("Server is Up : " + listenHandler.result());
//			} else {
//				System.out.println(listenHandler.cause());
//			}
//		});
		httpServer.listen(listenHandler -> {
			if (listenHandler.succeeded()) {
				System.out.println("Server is Up : " + listenHandler.result());
			} else {
				System.out.println(listenHandler.cause());
			}
		});

	}
}
