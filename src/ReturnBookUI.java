import java.util.Scanner;


public class ReturnBookUI {
	
	//Change enum name UI_STATE to UiState
	public static enum UiState { INITIALISED, READY, INSPECTING, COMPLETED };

	//Change object control to returnBookControl
	private ReturnBookControl returnBookControl;
	//Change object input to inputScanner
	private Scanner inputScanner;
	//Change enum name UI_STATE to UiState
	private UiState state;

	//Change parameter control to returnBookControl
	public ReturnBookUI(ReturnBookControl returnBookControl) {
		//Change object control to returnBookControl
		this.returnBookControl = returnBookControl;
		//Change object input to inputScanner
		inputScanner = new Scanner(System.in);
		//Change enum name UI_STATE to UiState
		state = UiState.INITIALISED;
		//Change object control to returnBookControl
		returnBookControl.setUI(this);
	}


	public void run() {		
		//Change method output to setOutput
		setOutput("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				//Change method input to getInput
				String bookStr = getInput("Scan Book (<enter> completes): ");
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
						//Change method output to setOutput	
						setOutput("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				//Change method input to getInput
				//Change String variable ans to answerString
				String answerString = getInput("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				//Change String variable ans to answerString
				if (answerString.toUpperCase().equals("Y")) {					
					isDamaged = true;
				}
				//Change object control to returnBookControl
				returnBookControl.dischargeLoan(isDamaged);
			
			case COMPLETED:
				//Change method output to setOutput	
				setOutput("Return processing complete");
				return;
			
			default:
				//Change method output to setOutput	
				setOutput("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	//Change method input to getInput
	//Change parameter prompt to promptString
	private String getInput(String promptString) {
		//Change parameter prompt to promptString
		System.out.print(promptString);
		//Change object input to inputScanner
		return inputScanner.nextLine();
	}	
		
	//Change method output to setOutput	
	private void setOutput(Object object) {
		System.out.println(object);
	}
	
	//Change method display to setDisplay		
	public void setDisplay(Object object) {
		output(object);
	}
	//Change enum name UI_STATE to UiState
	public void setState(UiState state) {
		this.state = state;
	}

	
}
