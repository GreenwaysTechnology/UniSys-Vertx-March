package com.unisys.fp;

interface Greeter {
	String greet();
}

//how to implement Greeter interface?

//Way -1 : declaring class which implements Greeter
class GreeterImpl implements Greeter {

	public String greet() {
		// TODO Auto-generated method stub
		return "Hello";
	}

}

public class GreeterImplMain {
	public static void main(String[] args) {
		Greeter g = new GreeterImpl();
		System.out.println(g.greet());

		// way 2:
		Greeter welcome = new Greeter() {

			public String greet() {
				// TODO Auto-generated method stub
				return "Welcome";
			}
		};
		System.out.println(welcome.greet());

		Runnable runnable = new Runnable() {

			public void run() {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread().getName());
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();

		// Way 3 : Java 8 functional Programming Style:lambda expressions.
		Greeter mygreeter = () -> {
			return "Hello Lambda";
		};
		System.out.println(mygreeter.greet());

	}
}
