package com.unisys.vertx.core.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

public class GreeterService extends AbstractVerticle {

	HttpServer server;

	@Override
	public void start() throws Exception {
		server = vertx.createHttpServer();
		// Request Handler
		server.requestHandler(req -> {
			System.out.println("Request is Received");
			req.response().end("Welcome to Vertx");
		});
		// start the server

		server.listen(8888, status -> {
			String res = status.succeeded() ? "Http Server is Listening" : "Server Failed";
			System.out.println(res);
		});
	}
}
