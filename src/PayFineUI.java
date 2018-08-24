import java.util.Scanner;


public class PayFineUI {


	public static enum UI_STATE { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };

	//Chanege object conitrol to payFineControl
	private PayFineControl payFineControl;
	//Change object input to inputScanner
	private Scanner inputScanner;
	private UI_STATE state;

	//Chanege argument conitrol to payFineControl
	public PayFineUI(PayFineControl payFineControl) {
		//Chanege object conitrol to payFineControl
		this.payFineControl = payFineControl;
		//Change object input to inputScanner
		inputScanner = new Scanner(System.in);
		state = UI_STATE.INITIALISED;
		//Chanege object conitrol to payFineControl
		payFineControl.setUI(this);
	}
	
	
	public void setState(UI_STATE state) {
		this.state = state;
	}


	public void run() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				// Change memStr to memberIdString
				String memberIdString = input("Swipe member card (press <enter> to cancel): ");
				// Change memStr to memberIdString
				if (memberIdString.length() == 0) {
					//Chanege object conitrol to payFineControl
					payFineControl.cancel();
					break;
				}
				try {
					// Change memStr to memberIdString
					int memberId = Integer.valueOf(memberIdString).intValue();
					//Chanege object conitrol to payFineControl
					payFineControl.cardSwiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;
				//Change amtStr to amountString
				String amountString = input("Enter amount (<Enter> cancels) : ");
				//Change amtStr to amountString
				if (amountString.length() == 0) {
					//Chanege object conitrol to payFineControl
					payFineControl.cancel();
					break;
				}
				try {
					//Change amtStr to amountString
					amount = Double.valueOf(amountString).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				//Chanege object conitrol to payFineControl
				payFineControl.payFine(amount);
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		//Change object input to inputScanner
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void display(Object object) {
		output(object);
	}


}
