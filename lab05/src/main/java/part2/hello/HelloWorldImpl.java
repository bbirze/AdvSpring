package part2.hello;

public class HelloWorldImpl implements HelloWorld {

	@Override
	public String sayHello(String name) {
		return "Hello, " + name;
	}

}
