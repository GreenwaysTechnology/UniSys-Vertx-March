package io.vertx.sample.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;

class ProductsApi extends AbstractVerticle {
	public Router productRouter() {
		// Users
		Router productsRouter = Router.router(vertx);
		// url
		productsRouter.get("/").handler(rh -> {
			HttpServerResponse response = rh.response();
			response.end("Products");
		});
		return productsRouter;
	}
}

public class GreetingsApi extends AbstractVerticle {
	HttpServer httpServer;
	HttpServerOptions options;
	Router Mainrouter;

	private Router greeterRouter() {
		Router greeterRouter = Router.router(vertx);
		// url
		greeterRouter.get("/greet").handler(rh -> {
			HttpServerResponse response = rh.response();
			response.end("Greet");
		});

		greeterRouter.get("/hello").handler(rh -> {
			HttpServerResponse response = rh.response();
			response.end("Hello");
		});
		return greeterRouter;
	}

	private Router userRouter() {
		// Users
		Router userRouter = Router.router(vertx);
		// url
		userRouter.get("/").handler(rh -> {
			HttpServerResponse response = rh.response();
			response.end("Users");
		});
		return userRouter;
	}

	@Override
	public void start() throws Exception {
		options = new HttpServerOptions();
		options.setPort(8080);
		options.setHost("localhost");
		httpServer = vertx.createHttpServer(options);
		Mainrouter = Router.router(vertx);

		Mainrouter.mountSubRouter("/api/greeter", greeterRouter());
		Mainrouter.mountSubRouter("/api/users", userRouter());
		Mainrouter.mountSubRouter("/api/products", new ProductsApi().productRouter());

//		router.route().handler(routingContext -> {
//			HttpServerResponse response = routingContext.response();
//			response.end("Welcome to Routers!!@@@!!");
//		});

		httpServer.requestHandler(Mainrouter);

		// server start
		httpServer.listen(myserver -> {
			String res = myserver.succeeded() ? "Server is Listening @ " : "Server Failed";
			System.out.println(res);
		});

	}

}
