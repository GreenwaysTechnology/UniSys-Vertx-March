package com.unisys.vertx.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class PromiseVerticle extends AbstractVerticle {

	public Future<Void> getPromise() {
		Promise promise = Promise.promise();
		promise.complete();
		return promise.future();
	}

	public Future<Void> getError() {
		Promise promise = Promise.promise();
		promise.fail("Something went wrong!");
		return promise.future();
	}

	public Future<String> getMessage() {
		Promise promise = Promise.promise();
		promise.complete("Hello Promise!");
		return promise.future();
	}


	@Override
	public void start() throws Exception {
		// Basic
		// Future future = getPromise().future();
		Future future = getPromise();
		if (future.succeeded()) {
			System.out.println("Promise success");
		}
		getMessage().setHandler(ar -> {
			System.out.println(ar.result());
		});

	}
}
