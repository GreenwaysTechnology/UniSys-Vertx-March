package io.vertx.sample;

import io.vertx.core.Vertx;

public class ExecuteBlockDemo {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();

		vertx.setTimer(1000, h -> {
			
		});

//		vertx.createHttpServer().requestHandler(request -> {
//
//			// Let's say we have to call a blocking API (e.g. JDBC) to execute a query for
//			// each
//			// request. We can't do this directly or it will block the event loop
//			// But you can do this using executeBlocking:
//
//			vertx.<String>executeBlocking(future -> {
//
//				// Do the blocking operation in here
//
//				// Imagine this was a call to a blocking API to get the result
//				try {
//					Thread.sleep(50000);
//				} catch (Exception ignore) {
//				}
//				String result = "xxxxx!";
//
//				future.complete(result);
//
//			}, res -> {
//
//				if (res.succeeded()) {
//
//					request.response().putHeader("content-type", "text/plain").end(res.result());
//
//				} else {
//					res.cause().printStackTrace();
//				}
//			});
//
//		}).listen(8080);

	}
}
