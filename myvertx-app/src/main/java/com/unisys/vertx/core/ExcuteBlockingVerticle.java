package com.unisys.vertx.core;

import io.vertx.core.AbstractVerticle;

public class ExcuteBlockingVerticle extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.<String>executeBlocking(blockingCodeHandler -> {
			try {
				System.out.println("Waiting in blocking mode!");
				Thread.sleep(5000);
			} catch (Exception ignore) {
			}
			String result = "xxxxx!";

			blockingCodeHandler.complete(result);

		}, resultHandler -> {
			System.out.println("Blocking Opertion Completed!");
			System.out.println(resultHandler.result());
		});
	}
}
