package com.unisys.fp;

@FunctionalInterface
interface Inventory {
	int getStockValue();
}

@FunctionalInterface
interface Product {
	int getQty(int qty);
}

public class LambdaReturnValues {
	public static void main(String[] args) {

		Inventory inv = null;
		inv = () -> {
			return 100;
		};
		System.out.println(inv.getStockValue());

		// nomore body only return statement:drop {}
		inv = () -> 100;
		System.out.println(inv.getStockValue());

		Product product = null;
		product = (int qty) -> {
			return qty;
		};
		System.out.println("Qty : " + product.getQty(10));

		// Args can be type inferenced
		product = (qty) -> {
			return qty;
		};
		System.out.println("Qty : " + product.getQty(10));
		// if arg is single drop ()
		product = qty -> {
			return qty;
		};
		System.out.println("Qty : " + product.getQty(10));
		// only one return statement : drop {} and return statement
		product = qty -> qty;
		System.out.println("Qty : " + product.getQty(10));

	}
}
