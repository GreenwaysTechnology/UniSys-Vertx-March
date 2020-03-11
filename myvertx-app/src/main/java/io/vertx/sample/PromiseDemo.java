package io.vertx.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class PromiseDemo extends AbstractVerticle {

	public Promise<Void> getPromise() {
		Promise promise = Promise.promise();
		promise.complete();
		return promise;
	}

	public Promise<Void> getError() {
		Promise promise = Promise.promise();
		promise.fail("Something went wrong!");
		return promise;
	}

	public Promise<String> getMessage() {
		Promise promise = Promise.promise();
		promise.complete("Hello Promise!");
		return promise;
	}

	private Promise<String> sayHello(String message) {
		Promise<String> promise = Promise.promise();
		// mimic something that take times
		vertx.setTimer(100, l -> promise.complete("hello " + message));
		return promise;
	}

	private Promise<String> sayWorld() {
		Promise<String> promise = Promise.promise();
		// mimic something that take times
		vertx.setTimer(100, l -> promise.complete("world Vertx"));
		return promise;
	}

	private Promise<String> login(String userName, String password) {
		Promise promise = Promise.promise();
		if (userName.equals("admin") && password.equals("admin")) {
			promise.complete("Login Success");
		} else {
			promise.fail(new RuntimeException("Login Failed"));
		}
		return promise;
	}

	private Promise<Void> prepareDataBase() {
		System.out.println("Prepare Database...");
		Promise<Void> promise = Promise.promise();
		promise.complete();
		return promise;
	}

	private Future<Void> startServer() {
		System.out.println("Start HTTP Server...");
		Promise<Void> promise = Promise.promise();
		promise.complete();
		return promise.future();
	}

	@Override
	public void start(Promise<Void> startFuture) throws Exception {

		// Basic
		Future future = getPromise().future();
		if (future.succeeded()) {
			System.out.println("Promise Ready!");
		}

		// Grab Error
		getError().future().setHandler(ar -> {
			if (ar.failed()) {
				System.out.println("Got It -Inside handler error : " + ar.cause().getMessage());
			}
		});

		// Get The Result
		getMessage().future().setHandler(ar -> {
			System.out.println(ar.result());
		});

		// Some Logic
		login("admin", "admin").future().setHandler(handler -> {

			if (handler.succeeded()) {
				System.out.println(handler.result());
			} else {
				System.out.println(handler.cause());
			}
		});
		// nested calls
		sayWorld().future().compose(v -> sayHello(v).future()).setHandler(ar -> {
			if (ar.failed()) {
				System.out.println("Something bad happened");
				ar.cause().printStackTrace();
			} else {
				System.out.println("Result: " + ar.result());
			}
		});

		prepareDataBase().future().compose(v -> startServer()).setHandler(ar -> {
			if (ar.succeeded()) {
				startFuture.complete();
				System.out.println("System is Up!");
			} else {

				System.out.println("Something went wrong!");

				startFuture.fail(ar.cause());
			}
		});
		//prepareDataBase().future().compose(v -> startServer()).setHandler(ar -> startFuture.future().completer());

	}

	@Override
	public void stop() throws Exception {

	}
}
