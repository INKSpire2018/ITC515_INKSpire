import java.util.Scanner;


public class ReturnBookUI {

	public static enum UI_STATE { INITIALISED, READY, INSPECTING, COMPLETED };

	//Change object control to returnBookControl
	private ReturnBookControl returnBookControl;
	//Change object input to inputScanner
	private Scanner inputScanner;
	private UI_STATE state;

	//Change parameter control to returnBookControl
	public ReturnBookUI(ReturnBookControl returnBookControl) {
		//Change object control to returnBookControl
		this.returnBookControl = returnBookControl;
		//Change object input to inputScanner
		inputScanner = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		//Change object control to returnBookControl
		returnBookControl.setUI(this);
	}


	public void run() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					//Change object control to returnBookControl
					returnBookControl.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						//Change object control to returnBookControl
						returnBookControl.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true;
				}
				//Change object control to returnBookControl
				returnBookControl.dischargeLoan(isDamaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		//Change object input to inputScanner
		return inputScanner.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void display(Object object) {
		output(object);
	}
	
	public void setState(UI_STATE state) {
		this.state = state;
	}

	
}
