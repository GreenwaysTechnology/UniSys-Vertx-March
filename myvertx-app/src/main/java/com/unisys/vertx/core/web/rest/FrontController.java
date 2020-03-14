package com.unisys.vertx.core.web.rest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;

//DAO Class
class BooksDAO {

	MongoClient mongo;
	public BooksDAO() {
	}

	BooksDAO(MongoClient mongo) {
		System.out.println("BooksDAO initalized!");
		this.mongo = mongo;
	}

	public Future<String> getBooks() {
		Promise promise = Promise.promise();
		mongo.find("books", new JsonObject(), lookup -> {
			// error handling
			if (lookup.failed()) {
				promise.fail(lookup.cause());
				return;
			}
			// now convert the list to a JsonArray because it will be easier to encode the
			// final object as the response.
			final JsonArray json = new JsonArray();
			for (JsonObject o : lookup.result()) {
				json.add(o);
			}
			promise.complete(json.encode());
		});
		return promise.future();

	}
}

class BookEndPoint extends AbstractVerticle {

	MongoClient mongo;
	BooksDAO bookdao;

	public BookEndPoint() {
		// TODO Auto-generated constructor stub
	}

	public BookEndPoint(MongoClient mongo) {
		// TODO Auto-generated constructor stub
		this.mongo = mongo;
		bookdao = new BooksDAO(mongo);
	}

	public Router getBooksApi() {
		// /api/greeter /hello /hai /welcome /goodbye
		// Creater Router Object
		Router bookRouter = Router.router(vertx);

		// EndPoints
		bookRouter.get("/list").handler(ctx -> {

			// Mongo code
			bookdao.getBooks().setHandler(h -> {
				// since we are producing json we should inform the browser of the correct
				// content type.
				ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
				// encode to json string
				ctx.response().end(h.result());
			});
		});

		return bookRouter;
	}

}

//Greeter End

class GreeterEndPoint extends AbstractVerticle {

	public Router getGreeterApi() {
		// /api/greeter /hello /hai /welcome /goodbye
		// Creater Router Object
		Router gapi = Router.router(vertx);

		// EndPoints
		gapi.route(HttpMethod.GET, "/hello").handler(routingctx -> {
			routingctx.response().end("Hello");
		});
		gapi.get("/hai").handler(routingctx -> {
			routingctx.response().end("Hai");
		});

		gapi.get("/goodbye").handler(routingctx -> {
			routingctx.response().end("Goodbye");
		});

		return gapi;
	}

}

public class FrontController extends AbstractVerticle {

	HttpServer httpServer;
	HttpServerOptions options;
	MongoClient mongo;

	@Override
	public void start() throws Exception {
		options = new HttpServerOptions();
		options.setPort(3000);
		options.setHost("localhost");
		httpServer = vertx.createHttpServer(options);

		// Create Mongo DB Connection
		mongo = MongoClient.createShared(vertx, new JsonObject().put("db_name", "BooksDb"));

		// Create Main router
		Router mainRouter = Router.router(vertx);

		mainRouter.mountSubRouter("/api/greeter", new GreeterEndPoint().getGreeterApi());

		mainRouter.mountSubRouter("/api/messages", new GreeterEndPoint().getGreeterApi());

		BookEndPoint bookapi = new BookEndPoint(mongo);
		mainRouter.mountSubRouter("/api/books", bookapi.getBooksApi());

		// binding router with routing context.
		// httpServer.requestHandler(gapi::accept);
		httpServer.requestHandler(mainRouter);

		// Server start
		httpServer.listen(myserver -> {
			String res = myserver.succeeded() ? "Server is Listening @ " : "Server Failed";
			System.out.println(res);
		});
	}

}
