import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UI_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	//meaning full variable --SW
	private BorrowBookControl controlBookBorrow;
	//meaning full variable --SW
	private Scanner ScannerInput;
	//meaning full variable --SW
	private UiState state;

	
	public BorrowBookUI(BorrowBookControl control) {
		this.control = control;
		ScannerInput = new Scanner(System.in);
		//meaning full variable --SW
		////correct variable name changed --SW
		bookState = UiState.INITIALISED;
		controlBookBorrow.setUI(this);
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		//correct variable name changed --SW
		return ScannerInput.nextLine();
	}	
	
		
	private void output(Object object) {
		System.out.println(object);
	}
	
			
	public void setState(UI_STATE state) {
		//correct variable name changed --SW
		this.state = state;
	}

	//meaning full function name changed--SW
	public void systemRun() {
		output("Borrow Book Use Case UI\n");
		
		while (true) {
			//correct variable name changed --SW
			switch (bookState) {			
			
			case CANCELLED:
				output("Borrowing Cancelled");
				return;

				
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): ");
				if (memStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					control.Swiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");
				}
				break;

				
			case RESTRICTED:
				input("Press <any key> to cancel");
				control.cancel();
				break;
			
				
			case SCANNING:
			//change variable name  bookStr to bookIdScan
			//Change method input to getInput
				String bookIdScan = getinput("Scan Book (<enter> completes): ");
				//change variable name
				if (bookIdScan.length() == 0) {
					control.Complete();
					break;
				}
				try {
					int bookId = Integer.valueOf(bookStr).intValue();
					control.Scanned(bookId);
					
				} catch (NumberFormatException e) {
					output("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
			//change string variable name to get input
				String ans = getinput("Commit loans? (Y/N): ");
				if (ans.toUpperCase().equals("N")) {
					control.cancel();
					
				} else {
					control.commitLoans();
					input("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
			//Change method output to getoutput
				getoutput("Borrowing Completed");
				return;
	
				
			default:
			//Change method output to getoutput
				getoutput("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + state);			
			}
		}		
	}

//meaningful function name changed--SW
	public void outputDisplay(Object object) {
		output(object);		
	}


}
