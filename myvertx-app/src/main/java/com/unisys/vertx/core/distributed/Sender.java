package com.unisys.vertx.core.distributed;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.eventbus.EventBus;
import io.vertx.ext.web.Router;

public class Sender extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		EventBus evBus = vertx.eventBus();
		Router router = Router.router(vertx);
		router.route("/hello").handler(ctx -> {
			evBus.publish("news.uk.sport", "hello called");
			ctx.response().end("done");

		});

		vertx.createHttpServer().requestHandler(router::accept).listen(8080);

		System.out.println("HTTP server started on port 8080");
	}
}
