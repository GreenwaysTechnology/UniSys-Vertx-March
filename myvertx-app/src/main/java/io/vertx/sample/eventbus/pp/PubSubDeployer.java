package io.vertx.sample.eventbus.pp;

import io.vertx.core.Vertx;

public class PubSubDeployer {
	public static void main(String[] args) {
       Vertx vertx =Vertx.vertx();
       vertx.deployVerticle(new Receiver());
       vertx.deployVerticle(new Sender());
	}
}