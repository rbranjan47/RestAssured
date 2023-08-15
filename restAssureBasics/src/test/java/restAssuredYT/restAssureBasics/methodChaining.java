package restAssuredYT.restAssureBasics;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class methodChaining {
	public static void main(String[] args) {
		// Fruits names
		List<String> names = Arrays.asList("Apple", "Orange", "Banana", "Grape");
		// Stream
		//Stream<String> namesStream = names.stream();
		//Stream<String> nameMapStream = namesStream.map(e -> e + " is Fruit");
		
		//using method chanining
		Stream<String> namesStream = names.stream().map(e-> e+" is fruit!");
		List<String> newName = namesStream.collect(Collectors.toList());
		System.out.println(newName);
		
		//without method chaining
		methodChaniningNormalMethod methodChainingsObject = new methodChaniningNormalMethod();
		methodChainingsObject.printFirst("John Smith");
		methodChainingsObject.printSecond(25);
		
		//>>With Method chaining
		methodChainingsObject.printThird('a').printFourth(22.56);
	}
}


class methodChaniningNormalMethod{
	public void printFirst(String name) {
		System.out.println("Name is: "+ name);
	}
	
	public void printSecond(int age) {
		System.out.println("Age is: "+age);
	}
	
	public methodChaniningNormalMethod printThird(char c) {
		System.out.println("Character is: "+ c);
		return new methodChaniningNormalMethod();
	}
	
	public methodChaniningNormalMethod printFourth(double d) {
		System.out.println("Amount is: "+ d);
		return new methodChaniningNormalMethod();
	}
}