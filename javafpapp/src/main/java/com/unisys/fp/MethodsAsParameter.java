package com.unisys.fp;

@FunctionalInterface
interface Handler {
	void handle();
}

@FunctionalInterface
interface AsyncHandler {
	void handle(String connectionString);
}

class Connector {
	public void connect(Handler handler) {
		// call Hanlder handle mehtod
		handler.handle();
	}

	public void asynConnect(AsyncHandler handler) {
		handler.handle("host:localhost,port:8080");
	}
}

@FunctionalInterface
interface Resolver {
	void resolve(String message);
}

@FunctionalInterface
interface Rejection {
	void reject(String err);
}

class HttpServer {
	public void connect(Resolver resolver, Rejection rejection) {
		boolean isConnected = true;
		if (isConnected) {
			resolver.resolve("Http Server Connected : 200 ");
		} else {
			rejection.reject("Server Connection failed : 500");
		}

	}
}

public class MethodsAsParameter {
	public static void main(String[] args) {

		Connector connector = new Connector();
		// Passing implementation of interface as "method parameter inner class"
		connector.connect(new Handler() {

			@Override
			public void handle() {
				System.out.println("Connection is Ready!");
			}
		});
		// Lambda
		connector.connect(() -> {
			System.out.println("HTTP Connection is Ready");
		});
		connector.asynConnect(connectionInfo -> {
			System.out.println(connectionInfo);
		});
		//
		HttpServer http = new HttpServer();
		http.connect(r -> System.out.println(r), err -> System.out.println(err));

	}
}
