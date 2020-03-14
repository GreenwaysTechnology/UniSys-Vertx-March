package com.unisys.vertx.core.web;

import com.unisys.vertx.core.web.rest.FrontController;

import io.vertx.core.Vertx;

public class MainWebApp {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		//vertx.deployVerticle(new GreeterService());
		vertx.deployVerticle(new FrontController());

	}
}
