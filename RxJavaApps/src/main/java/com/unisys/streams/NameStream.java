package com.unisys.streams;

import io.reactivex.Observable;

public class NameStream {
	public static void main(String[] args) {

		// create Stream from scratch
		Observable.create(source -> {
			// Pushing Data into Stream
			source.onNext("Subramanian");
			source.onNext("Geetha");
			source.onNext("John");
			source.onNext("Arun");
			// im thinking i need to inform subscribe stream over
			source.onComplete();

		}).subscribe(System.out::println, System.out::println, () -> System.out.println("Stream done"));

	}
}
