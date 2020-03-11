package io.vertx.sample;

import io.vertx.core.AbstractVerticle;

public class MyFirstVerticle extends AbstractVerticle {
	@Override
	public void start() {
		System.out.println("Verticle Started");

	}

	@Override
	public void stop() throws Exception {
		System.out.println("Verticle stopped");
	}
}