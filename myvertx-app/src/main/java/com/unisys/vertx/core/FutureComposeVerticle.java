package com.unisys.vertx.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class FutureComposeVerticle extends AbstractVerticle {

	private Future<Void> prepareDataBase() {
		System.out.println("Prepare Database...");
		Future<Void> f = Future.future();
		f.complete();
		return f;
	}

	private Future<Void> startHttpServer() {
		System.out.println("Start HTTP Server...");
		Future<Void> f = Future.future();
		f.complete();
		return f;
	}

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

	private Future<String> sayHello() {
		return null;
	}

	private Future<String> getWorld() {
		return null;
	}

	@Override
	public void start() throws Exception {
		// i want to startHttpServer once prepareDatabse is completed

		// callback hell code
		prepareDataBase().setHandler(h -> {
			if (h.succeeded()) {
				startHttpServer().setHandler(httpHandler -> {
					if (httpHandler.succeeded()) {
						System.out.println("Server is Up!");
					}
				});
			}
		});

		// Refactored code
		prepareDataBase().compose(handler -> startHttpServer()).setHandler(http -> {
			if (http.succeeded()) {
				System.out.println("Server is Up!");
			}
		});
		/////////////////////////////////////
		sayWorld().setHandler(world -> {
			if (world.succeeded()) {
				sayHello(world.result()).setHandler(hello -> {
					System.out.println(hello.result());
				});
			}
		});
		
		sayWorld().compose(m -> sayHello(m)).setHandler(hello -> {
			System.out.println(hello.result());
		});
		sayWorld().compose(this::sayHello).setHandler(hello -> {
			System.out.println(hello.result());
		});

	}
}
