package com.unisys.vertx.core;

import io.vertx.core.Vertx;

public class FirstVertxInstance {
	public static void main(String[] args) {

		// Get the Vertx Instance
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle("com.unisys.vertx.core.HelloWorldVerticle");

	}
}
