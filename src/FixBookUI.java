import java.util.Scanner;


public class FixBookUI {

	//enum UI_STATE is changed into UiState
	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED };

	//variable control changed into fixBookControl
	private FixBookControl fixBookControl;
	//Change Scanner object input into inputScanner
	private Scanner inputScanner;
	//enum UI_STATE is changed into UiState
	//Change UiState object state into uiState
	private UiState uiState;

	//variable control changed into fixBookControl
	public FixBookUI(FixBookControl fixBookControl) {
		//variable control changed into fixBookControl
		this.fixBookControl = fixBookControl;
		//Change Scanner object input into inputScanner
		inputScanner = new Scanner(System.in);
		//enum UI_STATE is changed into UiState
		//Change UiState object state into uiState
		state = UiState.INITIALISED;
		//variable control changed into fixBookControl
		fixBookControl.setUI(this);
	}


	//enum UI_STATE is changed into UiState
	//Change UiState object state into uiState
	public void setState(UiState uiState) {
		//Change UiState object state into uiState
		this.uiState = uiState;
	}

	
	public void run() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			//Change UiState object state into uiState
			switch (uiState) {
			
			case READY:
				//Change String variable bookStr to bookName
				String bookName = input("Scan Book (<enter> completes): ");
				//Change String variable bookStr to bookName
				if (bookName.length() == 0) {
					//variable control changed into fixBookControl
					fixBookControl.scanningComplete();
				}
				else {
					try {
						//Change String variable bookStr to bookName
						int bookId = Integer.valueOf(bookName).intValue();
						//variable control changed into fixBookControl
						fixBookControl.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				//Change String variable ans to answerString
				String answerString = input("Fix Book? (Y/N) : ");
				boolean fix = false;
				//Change String variable ans to answerString
				if (answerString.toUpperCase().equals("Y")) {
					fix = true;
				}
				//variable control changed into fixBookControl
				fixBookControl.fixBook(fix);
				break;
								
			case COMPLETED:
				output("Fixing process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + uiState);			
			
			}		
		}
		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		//Change Scanner object input into inputScanner
		return inputScanner.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}
	

	public void display(Object object) {
		output(object);
	}
	
	
}
