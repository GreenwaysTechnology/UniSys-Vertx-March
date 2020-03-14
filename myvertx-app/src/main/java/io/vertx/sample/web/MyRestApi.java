package io.vertx.sample.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.ext.web.Router;

public class MyRestApi extends AbstractVerticle {
	HttpServer httpServer;
	HttpServerOptions options;
	Router Mainrouter;

	@Override
	public void start() throws Exception {
		options = new HttpServerOptions();
		options.setPort(8080);
		options.setHost("localhost");
		httpServer = vertx.createHttpServer(options);
		Mainrouter = Router.router(vertx);

		//Mainrouter.mountSubRouter("/api/books", new BooksEndPoint().findAll());

		httpServer.requestHandler(Mainrouter);

		// server start
		httpServer.listen(myserver -> {
			String res = myserver.succeeded() ? "Server is Listening @ " : "Server Failed";
			System.out.println(res);
		});
	}
}
