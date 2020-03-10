package com.unisys.fp;
//passing instance method /static method as parameter to lambda

//interface takes input prints the same
@FunctionalInterface
interface Printer {
	void print(String var);
}

class MicroTaskExecutor {

	public static void startMicroTask() {
		for (int i = 0; i < 3; i++) {
			System.out.println(MicroTaskExecutor.class.getName() + "" + i);
		}
	}
}

class Loop {

	private void startMicroTask() {
		for (int i = 0; i < 5; i++) {
			System.out.println("micro task thread -" + i);
		}
	}

	public void startLoop() {
		Thread thread = null;
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					System.out.println("thread-" + i);
				}
			}
		});
		thread.start();
		// lambda
		thread = new Thread(() -> {
			for (int i = 0; i < 5; i++) {
				System.out.println("thread-" + i);
			}
		});
		thread.start();
		// passing method as parameter
		thread = new Thread(() -> startMicroTask());
		thread.start();
		// passing method as parameter using : methodreference
		// this::methodName
		thread = new Thread(this::startMicroTask);
		thread.start();
		// Passing method from util class
		// methodReference className::methodName
		thread = new Thread(MicroTaskExecutor::startMicroTask);
		thread.start();

	}

}

public class MethodReferences {
	public static void main(String[] args) {
		Loop loop = new Loop();
		loop.startLoop();
		// How to simplfify taking input and print same

		Printer printer = null;
		printer = name -> {
			System.out.println(name);
		};
		printer.print("Subramanian");
		// using method referecence
		printer = System.out::println;
		printer.print("Geetha");

	}
}
