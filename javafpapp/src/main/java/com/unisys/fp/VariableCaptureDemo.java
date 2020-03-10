package com.unisys.fp;

@FunctionalInterface
interface Variable {
	void doIt();
}

public class VariableCaptureDemo {

	private int counter;

	// capaturing instance variables inside lambda
	public void increment() {
		Variable variable = () -> {
			System.out.println(++counter);
		};
		variable.doIt();
	}

	public static void main(String[] args) {
		new VariableCaptureDemo().increment();
		// Capaturing local variables inside lambda
		String name = "Subramanian";
		Variable variable = () -> {
			System.out.println(name);
		};
		variable.doIt();
	}
}
