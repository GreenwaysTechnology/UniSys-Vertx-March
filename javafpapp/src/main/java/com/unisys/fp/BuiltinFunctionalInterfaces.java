package com.unisys.fp;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class BuiltinFunctionalInterfaces {
	public static void main(String[] args) {

		Consumer<String> consumer = null;
		consumer = new Consumer<String>() {
			@Override
			public void accept(String t) {
				System.out.println(t);

			}
		};
		consumer.accept("Hello");
		consumer = t -> System.out.println(t);
		consumer.accept("Hai");
		consumer = System.out::println;
		consumer.accept("Welcome");
		// consumer interface in list iteration
		List<String> names = Arrays.asList("Ram", "Subramanian", "Divya");
		names.forEach(name -> System.out.println(name));
		names.forEach(System.out::println);


	}
}
