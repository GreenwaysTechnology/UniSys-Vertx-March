package com.unisys.vertx.core;

import java.util.Arrays;
import java.util.List;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class CompositFutureVerticle extends AbstractVerticle {

	public Future<String> getUserName1() {
		Promise promise = Promise.promise();
		promise.complete("Ram");
		return promise.future();
	}

	public Future<String> getUserName2() {
		Promise promise = Promise.promise();
		promise.complete("Subramanian");
		return promise.future();
	}

	public Future<String> getUserName3() {
		Promise promise = Promise.promise();
		promise.complete("Karthik");
		return promise.future();
	}

	@Override
	public void start() throws Exception {
		List<Future> futures = Arrays.asList(getUserName1(), getUserName2(), getUserName3());

		CompositeFuture.all(futures).list().forEach(System.out::println);

		CompositeFuture.all(futures).setHandler(handler -> {
			handler.result().list().forEach(System.out::println);
		});

	}
}
