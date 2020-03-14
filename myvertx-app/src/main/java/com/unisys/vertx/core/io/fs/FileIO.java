package com.unisys.vertx.core.io.fs;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.file.FileSystem;
import io.vertx.core.json.JsonObject;

class FileService {

	static Vertx vertx = Vertx.vertx();

	public static Future<String> read() {
		Promise<String> promise = Promise.promise();

		FileSystem file = vertx.fileSystem();
		file.readFile("assets/hello.txt", fsHandler -> {
			if (fsHandler.succeeded()) {
				// System.out.println(fsHandler.result());
				promise.complete(fsHandler.result().toString());
			} else {
				// System.out.println(fsHandler.cause());
				promise.fail(fsHandler.cause());
			}
		});
		return promise.future();
	}

	public void write() {
		FileSystem file = vertx.fileSystem();
		file.writeFile("assets/hello_copy.txt", Buffer.buffer("Welcome"), result -> {
			if (result.succeeded()) {
				System.out.println("File written");
			} else {
				System.err.println("Oh oh ..." + result.cause());
			}
		});
		System.out.println("end");
	}
}

class JSONHandler {

	public void createJSON() {
		JsonObject json = new JsonObject();
		json.put("id", 1);
		json.put("name", "Subramanian");
		json.put("city", "Chennai");
		
		//COnvert JSON(Object) into string
		System.out.println(json.encodePrettily());
	}
}

class BufferService {

	public void pushDataInfoBuffer() {
		Buffer buffer = Buffer.buffer();
		buffer.appendString("Hello");
		buffer.appendByte((byte) 127);
		buffer.appendShort((short) 127);
		buffer.appendInt(127);
		buffer.appendLong(127);
		buffer.appendFloat(127.0F);
		buffer.appendDouble(127.0D);
		System.out.println("buffer.length() = " + buffer.length());
		for (int i = 0; i < buffer.length(); i += 4) {
			System.out.println("int value at " + i + " is " + buffer.getInt(i));
		}

		byte[] bytes = new byte[] { 1, 3, 5 };
		Buffer buff = Buffer.buffer(bytes);
		for (int j = 0; j < buff.length(); j++) {
			System.out.println(buff.getByte(j));
		}
	}
}

public class FileIO extends AbstractVerticle {

	@Override
	public void start() throws Exception {

		FileService.read().setHandler(h -> {
			if (h.succeeded()) {
				System.out.println(h.result());
			} else {
				System.out.println(h.cause());
			}
		});

		new BufferService().pushDataInfoBuffer();

		new JSONHandler().createJSON();
		
	}
}
