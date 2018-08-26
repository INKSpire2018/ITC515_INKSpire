import java.util.Scanner;

//Change class name PayFineUI to PayFineUi 
public class PayFineUi {

	//Change enum UI_STATE to UiState 
	public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };

	//Chanege object conitrol to payFineControl
	private PayFineControl payFineControl;
	//Change object input to inputScanner
	private Scanner inputScanner;
	//Change enum UI_STATE to UiState 
	//Change object state to uiState
	private UiState uiState;

	//Chanege argument conitrol to payFineControl
	//Change class name PayFineUI to PayFineUi 
	public PayFineUi(PayFineControl payFineControl) {
		//Chanege object conitrol to payFineControl
		this.payFineControl = payFineControl;
		//Change object input to inputScanner
		inputScanner = new Scanner(System.in);
		//Change enum UI_STATE to UiState 
		//Change object state to uiState
		uiState = UiState.INITIALISED;
		//Chanege object conitrol to payFineControl
		payFineControl.setUI(this);
	}
	
	//Change enum UI_STATE to UiState 
	//Change parameter state to uiState
	public void setState(UiState uiState) {
		//Change object state to uiState
		this.uiState = uiState;
	}


	public void run() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			//Change object state to uiState
			switch (uiState) {
			
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
				//Change object state to uiState
				throw new RuntimeException("FixBookUI : unhandled state :" + uiState);			
			
			}		
		}		
	}

	//Change string prompt to promptString
	private String input(String promptString) {
		//Change string prompt to promptString
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
