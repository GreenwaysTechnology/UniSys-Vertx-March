package io.vertx.sample;

import io.vertx.core.*;

public class Offload extends AbstractVerticle {

	// private final Logger logger = LoggerFactory.getLogger(Offload.class);

	@Override
	public void start() {
		vertx.setPeriodic(5000, id -> {
			System.out.println("Tick");
			vertx.executeBlocking(this::blockingCode, this::resultHandler);
		});
	}

	private void blockingCode(Promise<String> promise) {
		System.out.println("Blocking code running");
		try {
			Thread.sleep(4000);
			System.out.println("done!");
			promise.complete("Ok!");
			
		} catch (InterruptedException e) {
			promise.fail(e);
		}
	}

	private void resultHandler(AsyncResult<String> ar) {
		if (ar.succeeded()) {
			System.out.println("Blocking code result:" + ar.result());
		} else {
			System.out.println("Woops" + ar.cause());
		}
	}

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new Offload());
	}
}