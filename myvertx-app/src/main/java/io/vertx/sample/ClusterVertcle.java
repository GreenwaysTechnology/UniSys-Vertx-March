package io.vertx.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.example.util.Runner;

public class ClusterVertcle extends AbstractVerticle {

	@Override
	public void start() {
		HttpServer server = vertx.createHttpServer();

		server.requestHandler(req -> {

			long id = Thread.currentThread().getId();
			req.response().end("Cluster App : " + id);
		});

		server.listen(8888, "localhost", handler -> {
			if (handler.succeeded()) {
				System.out.println("Server is Ready! " + Thread.currentThread().getId());
			} else {
				System.out.println("Server failed to Start");
			}
		});
	}

	@Override
	public void stop() throws Exception {
	}

}