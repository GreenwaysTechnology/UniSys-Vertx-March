package com.unisys.streams;

import io.reactivex.Observable;

public class LoginStream {
	public static void main(String[] args) {
		// create Stream from scratch
		Observable.create(source -> {

			// biz logic
			String userName = "admin";
			String password = "adminxx";
			if (userName.equals(userName) && password.equals("admin")) {
				source.onNext("Login Success");
			} else {
				source.onError(new Exception("Login failed"));
			}

			// im thinking i need to inform subscribe stream over
			source.onComplete();

		}).subscribe(System.out::println, System.out::println, () -> System.out.println("Stream done"));

	}
}
