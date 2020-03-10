package com.unisys.fp;

@FunctionalInterface
interface Stock {
	void getValue(int a);
}

public class ParamsAndArgs {
	public static void main(String[] args) {

		Stock stock = null;

		// args
		stock = (int a) -> System.out.println("Stock Value " + a);

		stock.getValue(10); // param

		// type inference ; Arg type is understand based on parameter value

		stock = (a) -> System.out.println("Stock Value " + a);

		stock.getValue(30); // param

		// if arg is single variable, you can omit () as welcome
		stock = a -> System.out.println("Stock Value " + a);

		stock.getValue(30); // param
	}
}
