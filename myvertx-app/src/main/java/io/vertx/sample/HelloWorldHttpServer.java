package io.vertx.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;

public class HelloWorldHttpServer extends AbstractVerticle {

	HttpServer server;

	@Override
	public void start() throws Exception {
		server = vertx.createHttpServer();
		server.requestHandler(req -> req.response().end("welcome"));
		server.listen(8083);
	}

	@Override
	public void stop() throws Exception {
		System.out.println("Stopped");
		server.close();
	}

}
