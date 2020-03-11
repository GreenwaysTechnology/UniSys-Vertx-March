package com.unisys.vertx.core;

import io.vertx.core.AbstractVerticle;

public class HelloWorldVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		System.out.println("Verticle is being deployed!");
	}
}
