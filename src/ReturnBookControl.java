public class ReturnBookControl 
{

	private ReturnBookUI ui;
	private enum CONTROL_STATE { INITIALISED, READY, INSPECTING };
	private CONTROL_STATE state;
	
	private library library;
	private loan currentLoan;
	

	//public constructor
	public ReturnBookControl() 
	{
		this.library = library.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	
	
	public void setUI(ReturnBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) {
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("ReturnBookControl: Cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("ReturnBookControl: Cannot call bookScanned except in READY state");
		}	
		book currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			ui.display("Invalid Book Id");
			return;
		}
		if (!currentBook.On_loan()) {
			ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) {
			overDueFine = library.calculateOverDueFine(currentLoan);
		}
		ui.display("Inspecting");
		ui.display(currentBook.toString());
		ui.display(currentLoan.toString());
		
		if (currentLoan.isOverDue()) {
			ui.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		ui.setState(ReturnBookUI.UI_STATE.INSPECTING);
		state = CONTROL_STATE.INSPECTING;		
	}


	public void scanningComplete() {
		if (!state.equals(CONTROL_STATE.READY)) {
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("ReturnBookControl: Cannot call scanningComplete except in READY state");
		}	
		ui.setState(ReturnBookUI.UI_STATE.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(CONTROL_STATE.INSPECTING)) {
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("ReturnBookControl: Cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		ui.setState(ReturnBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;				
	}


}
