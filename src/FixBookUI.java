import java.util.Scanner;


public class FixBookUI {

	//enum UI_STATE is changed into UiState
	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED };

	//variable control changed into fixBookControl
	private FixBookControl fixBookControl;
	//Change Scanner object input into inputScanner
	private Scanner inputScanner;
	//enum UI_STATE is changed into UiState
	private UiState state;

	//variable control changed into fixBookControl
	public FixBookUI(FixBookControl fixBookControl) {
		//variable control changed into fixBookControl
		this.fixBookControl = fixBookControl;
		//Change Scanner object input into inputScanner
		inputScanner = new Scanner(System.in);
		//enum UI_STATE is changed into UiState
		state = UiState.INITIALISED;
		//variable control changed into fixBookControl
		fixBookControl.setUI(this);
	}


	//enum UI_STATE is changed into UiState
	public void setState(UiState state) {
		this.state = state;
	}

	
	public void run() {
		output("Fix Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					//variable control changed into fixBookControl
					fixBookControl.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						//variable control changed into fixBookControl
						fixBookControl.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}
				}
				break;	
				
			case FIXING:
				String ans = input("Fix Book? (Y/N) : ");
				boolean fix = false;
				if (ans.toUpperCase().equals("Y")) {
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
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
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
