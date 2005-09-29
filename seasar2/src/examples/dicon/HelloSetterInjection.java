package examples.dicon;

public class HelloSetterInjection implements Hello {

	private String message;
	
	public HelloSetterInjection() {
	}
	
	public void setMessage(String message) {
		this.message = message;
	}

	public void showMessage() {
		System.out.println(message);
	}
}
