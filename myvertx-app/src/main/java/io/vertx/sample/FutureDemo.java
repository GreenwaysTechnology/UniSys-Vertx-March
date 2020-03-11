package io.vertx.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class FutureDemo extends AbstractVerticle {

	// Basic Future
	public Future<Void> getFuture() {
		Future future = Future.future();
		future.complete();
		return future;
	}

//Future with Message
	public Future<String> getMessage() {
		Future future = Future.future();
		future.complete("Hello Future!");
		return future;
	}

//Basic Error
	public Future<Void> getError() {
		Future future = Future.future();
		future.fail("Something went wrong!");
		return future;
	}

	
	
	// here name variables holds the result of sayWorld :output of the sayWorld
	// becomes
	// input.
	private Future<String> sayHello(String name) {
		Future<String> future = Future.future();
		// mimic something that take times
		vertx.setTimer(100, l -> future.complete("hello " + name));
		return future;
	}

	private Future<String> sayWorld() {
		Future<String> future = Future.future();
		// mimic something that take times
		vertx.setTimer(100, l -> future.complete("world"));
		return future;
	}
	private Future<Void> prepareDataBase() {
		System.out.println("Prepare Database...");
		Future<Void> f = Future.future();
		f.complete();
		return f;
	}

	private Future<Void> startServer() {
		System.out.println("Start HTTP Server...");
		Future<Void> f = Future.future();
		f.complete();
		return f;
	}

//with some logic
	private Future<String> login(String userName, String password) {
		Future<String> future = Future.future();
		if (userName.equals("admin") && password.equals("admin")) {
			future.complete("Login Success");
		} else {
			future.fail(new RuntimeException("Login Failed"));
		}
		return future;
	}

	@Override
	public void start() throws Exception {
		Future successFuture = getFuture();
		if (successFuture.succeeded()) {
			System.out.println("Success");
		}
		// failure
		Future failedFurture = getError();
		if (failedFurture.failed()) {
			System.out.println("Something went wrong...");

		}
		// With message

		// Get Result
		getMessage().setHandler(ar -> {
			if (ar.succeeded()) {
				System.out.println("Got It -Inside handler : " + ar.result());
			}

		});
		getError().setHandler(ar -> {
			if (ar.failed()) {
				System.out.println("Got It -Inside handler error : " + ar.cause().getMessage());
			}
		});
		// some logic
		login("admin", "admin").setHandler(handler -> {

			if (handler.succeeded()) {
				System.out.println(handler.result());
			} else {
				System.out.println(handler.cause());
			}
		});
		
		///////////// Composing Nested function calls
		prepareDataBase().compose(v -> startServer()).setHandler(handler -> {
			if (handler.succeeded()) {
				System.out.println("System is Up");
			} else {
				System.out.println(handler.cause().getMessage());
			}
		});
		

		sayWorld().compose(this::sayHello).setHandler(ar -> {
			if (ar.failed()) {
				System.out.println("Something bad happened");
				ar.cause().printStackTrace();
			} else {
				System.out.println("Result: " + ar.result());
			}
		});



	}

	@Override
	public void stop() throws Exception {
	}

}
