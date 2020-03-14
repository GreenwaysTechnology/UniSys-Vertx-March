package io.vertx.sample.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.example.util.Runner;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;;

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

public class App extends AbstractVerticle {

	MongoClient mongo;

	// Convenience method so you can run it in your IDE
	public static void main(String[] args) {
		Runner.runExample(App.class);
	}

	@Override
	public void start() throws Exception {
		// Create a mongo client using all defaults (connect to localhost and default
		// port) using the database name "demo".
		mongo = MongoClient.createShared(vertx, new JsonObject().put("db_name", "BooksDb"));
		// To simplify the development of the web components we use a Router to route
		// all HTTP requests
		// to organize our code in a reusable way.
		final Router router = Router.router(vertx);
		// Enable the body parser to we can get the form data and json documents in out
		// context.
		router.route().handler(BodyHandler.create());
		BooksDAO booksDao = new BooksDAO(mongo);

		router.get("/api/books").handler(ctx -> {
			booksDao.getBooks().setHandler(h -> {
				// since we are producing json we should inform the browser of the correct
				// content type.
				ctx.response().putHeader(HttpHeaders.CONTENT_TYPE, "application/json");
				// encode to json string
				ctx.response().end(h.result());
			});
		});
		// Serve the non private static pages
		// start a HTTP web server on port 8080
		vertx.createHttpServer().requestHandler(router).listen(8080);
	}
}
