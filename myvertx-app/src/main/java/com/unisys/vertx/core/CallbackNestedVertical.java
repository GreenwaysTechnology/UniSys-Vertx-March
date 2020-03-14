package com.unisys.vertx.core;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class CallbackNestedVertical extends AbstractVerticle {

	private void getUser(Handler<AsyncResult<String>> aHandler) {
		String fakeUser = "admin";
		if (fakeUser != null) {
			vertx.setTimer(1000, th -> {
				aHandler.handle(Future.succeededFuture(fakeUser));
			});
		} else {
			vertx.setTimer(1000, th -> {
				aHandler.handle(Future.failedFuture("No User found"));
			});
		}
	}

	private void login(String userName, Handler<AsyncResult<String>> aHandler) {

		if (userName.equals("admin")) {

			vertx.setTimer(5000, th -> {
				aHandler.handle(Future.succeededFuture("Login Success!"));
			});
			

		} else {

			vertx.setTimer(1000, th -> {
				aHandler.handle(Future.failedFuture("Login failed"));
			});
		}
	}

	@Override
	public void start() throws Exception {
		getUser(ar -> {
			if (ar.succeeded()) {
				login(ar.result(), ar2 -> {
					if (ar2.succeeded()) {
						System.out.println(ar2.result());
					} else {
						System.out.println(ar2.cause());
					}
				});

			} else {
				System.out.println(ar.cause());

			}
		});
	}

}
