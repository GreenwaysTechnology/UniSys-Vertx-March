package com.unisys.vertx.core.distributed;

import io.vertx.core.Vertx;

public class Main {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new Receiver());
		vertx.deployVerticle(new Sender());
	}

}
