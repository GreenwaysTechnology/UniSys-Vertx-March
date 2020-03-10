package com.unisys.streams;

import io.reactivex.Observable;

public class GreeterObservable {
	public static void main(String[] args) {

		// create Stream from scratch
		Observable<String> stream = Observable.create(source -> {
			// Pushing Data into Stream
			source.onNext("Subramanian");
			source.onNext("Geetha");
			source.onNext("John");
			source.onNext("Arun");
			// im thinking i need to inform subscribe stream over
			source.onComplete();

		});

		// Subscribe the Stream : Three channels are ready now to get data,error,comple

//		stream.subscribe(data -> {
//			System.out.println(data);
//		}, err -> {
//			System.out.println(err);
//		}, () -> {
//			System.out.println("Stream done!");
//		});
		stream.subscribe(System.out::println, 
				System.out::println, () -> System.out.println("Stream done"));

	}
}
