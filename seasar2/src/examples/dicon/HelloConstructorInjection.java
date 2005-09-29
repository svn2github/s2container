package examples.dicon;

public class HelloConstructorInjection implements Hello {

	private String message;
	
	public HelloConstructorInjection(String message) {
		this.message = message;
	}
	
	public void showMessage() {
		System.out.println(message);
	}

}
