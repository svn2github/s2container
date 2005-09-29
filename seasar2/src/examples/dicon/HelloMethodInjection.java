package examples.dicon;

public class HelloMethodInjection implements Hello {

	private StringBuffer buf = new StringBuffer();
	
	public HelloMethodInjection() {
	}
	
	public void addMessage(String message) {
		this.buf.append(message);
	}

	public void showMessage() {
		System.out.println(buf.toString());
	}
}
