package com.unisys.vertx.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;

public class TimerAsyncFutureVerticle extends AbstractVerticle {

	private Future<String> blockingFuture() {
		Future future = Future.future();
		future.complete("Blocking future!!!");
		return future;
	}

	private Future<String> getName() {
		Future future = Future.future();
		future.complete("Blocking getName!!!");
		return future;
	}

	private Future<String> getName1() {
		Future future = Future.future();
		future.complete("Blocking getName1!!!");
		return future;
	}

	// async api
	private Future<String> delay(long time, String message) {
		Future future = Future.future();
		// trigger async call
		vertx.setTimer(time, h -> {
			future.complete(message);
		});
		return future;
	}

	@Override
	public void start() throws Exception {

	
		blockingFuture().setHandler(h -> {
			String r = h.succeeded() ? h.result() : h.cause().toString();
			System.out.println(r);
		});
		delay(1000, "Hai-1000").setHandler(h -> {
			String r = h.succeeded() ? h.result() : h.cause().toString();
			System.out.println(r);
		});
		blockingFuture().setHandler(h -> {
			String r = h.succeeded() ? h.result() : h.cause().toString();
			System.out.println(r);
		});

		delay(5000, "Hello-5000").setHandler(h -> {
			String r = h.succeeded() ? h.result() : h.cause().toString();
			System.out.println(r);
			
		});
		blockingFuture().setHandler(h -> {
			String r = h.succeeded() ? h.result() : h.cause().toString();
			System.out.println(r);
		});
	}
}
