package com.unisys.streams;

import io.reactivex.Observable;

public class SequenceStream {
	public static void main(String[] args) {
		// create Stream from scratch
		Observable.create(source -> {

			for (int i = 0; i < 10; i++) {
				source.onNext(i);
			}

			// im thinking i need to inform subscribe stream over
			source.onComplete();

		}).subscribe(System.out::println, System.out::println, () -> System.out.println("Stream done"));

	}
}
