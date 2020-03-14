package com.unisys.vertx.core.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;

public class HttpServerOptionsVerticle extends AbstractVerticle {
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

			System.out.println("Method : " + req.method());
			System.out.println("Version : " + req.version());
			System.out.println("Scheme : " + req.scheme());
			System.out.println("Uri :" + req.uri());
			System.out.println("Path :" + req.path());
			System.out.println("Query String : " + req.query());
			System.out.println("Host :" + req.host());
			System.out.println(".....Headers..... ");
			req.headers().forEach(System.out::println);
			System.out.println();

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
