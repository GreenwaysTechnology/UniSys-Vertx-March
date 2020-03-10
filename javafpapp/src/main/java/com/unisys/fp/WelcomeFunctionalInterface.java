package com.unisys.fp;

@FunctionalInterface
interface Welcome {
	void sayWelcome();
}

public class WelcomeFunctionalInterface {
	public static void main(String[] args) {

		Welcome welcome = null;
		
		//Use case 1 : basic lambda
		welcome =() -> {
			System.out.println("SayHello");
		};
		welcome.sayWelcome();
		
		//Use case 2 : if function having only one line of code.
		 //drop {}
		welcome = ()->System.out.println("SayHello");
		welcome.sayWelcome();


	}
}
