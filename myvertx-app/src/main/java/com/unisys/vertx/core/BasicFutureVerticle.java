package com.unisys.vertx.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class BasicFutureVerticle extends AbstractVerticle {

	// return the basic success future
	private Future<Void> getEmptyFuture() {
		Future future = Future.future();
		// invoke async completion event
		future.complete();
		return future;
	}

	// return the basic success future with payload(data)
	private Future<String> getDataFuture() {
		Future future = Future.future();
		// invoke async completion event
		future.complete("Hello Future!!!");
		return future;
	}
	// return the basic future with error

	private Future<String> getError() {
		Future future = Future.future();
		// invoke async completion event
		future.fail("Something went wrong!!!");
		return future;
	}

	// Future biz logic
	private Future<String> validate(String userName, String password) {
		Future future = Future.future();
		// biz logic
		if (userName.equals("admin") && password.equals("admin")) {
			future.complete("Login Successful : - " + userName);
		} else {
			future.fail("Login failed");
		}

		return future;
	}

	@Override
	public void start() throws Exception {

		Future future = null;
		future = getEmptyFuture();
		if (future.succeeded()) {
			System.out.println("Future succesfully returned");
		} else {
			System.out.println("Future not Returned");
		}
		// data future
		;
		// Grab the result of async operation
		getDataFuture().setHandler(new Handler<AsyncResult<String>>() {

			@Override
			public void handle(AsyncResult<String> event) {
				// TODO Auto-generated method stub
				if (event.succeeded()) {
					System.out.println(event.result());
				}
			}
		});
		// java Lambda Syntax
		getDataFuture().setHandler(h -> {
			if (h.succeeded()) {
				System.out.println(h.result());
			}
		});
		getDataFuture().onSuccess(new Handler<String>() {

			@Override
			public void handle(String event) {
				// TODO Auto-generated method stub
				System.out.println(event);
			}
		});
		getDataFuture().onSuccess(result -> System.out.println(result));
		getDataFuture().onSuccess(System.out::println);
		//////////////////
		System.out.println("Error Handling");
		getError().setHandler(h -> {
			if (h.failed()) {
				System.out.println(h.cause());
			}
		});
		getError().onFailure(System.out::println);
		// Validate
		validate("admin", "admin").setHandler(h -> {
			String r = h.succeeded() ? h.result() : h.cause().toString();
			System.out.println(r);
		});
	}

}
