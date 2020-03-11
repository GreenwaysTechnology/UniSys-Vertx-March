package io.vertx.sample;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Promise;

public class CompositFutureDemo extends AbstractVerticle {

	public CompositeFuture collect() {

		Promise p1 = Promise.promise();
		p1.complete("Ram");
		Promise p2 = Promise.promise();
		p2.complete("Subramanian");
		Promise p3 = Promise.promise();
		p3.complete("Geetha");

		return CompositeFuture.all(p1.future(), p2.future(), p3.future());
	}

	@Override
	public void start(Promise starterFuture) throws Exception {
		//collect().compose(System.out::println,System.out::println);
		collect().list().forEach(System.out::println);
	}

	@Override
	public void stop() throws Exception {

	}
}
