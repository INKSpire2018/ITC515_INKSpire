public class ReturnBookControl 
{

	private ReturnBookUI ui;
	//changed the variable name CONTROL_STATE to controlState,INITIALISED -> initialised, READY -> ready, INSPECTING-> inspecting by Hashan
	private enum controlState { initialised, ready, inspecting };
	//changed the variable name CONTROL_STATE to controlState by Hashan
	private controlState state;
	
	private library library;
	private loan currentLoan;
	

	//public constructor
	public ReturnBookControl() 
	{
		//changed the method name INSTANCE to instance
		this.library = library.instance();
		//changed the variable name CONTROL_STATE to controlState,INITIALISED -> initialised by Hashan
		state = controlState.initialised;
	}
	
	
	public void setUI(ReturnBookUI ui) 
	{
		//changed the variable name CONTROL_STATE to controlState,INITIALISED -> initialised by Hashan
		if (!state.equals(controlState.initialised)) 
		{
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("ReturnBookControl: Cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		//changed the variable name UI_STATE to uiState, READY -> ready
		ui.setState(ReturnBookUI.uiState.ready);
		
		//changed the variable name CONTROL_STATE to controlState, READY -> ready by Hashan
		state = controlState.ready;		
	}


	public void bookScanned(int bookId) 
	{
		//changed the variable name CONTROL_STATE to controlState, READY -> ready by Hashan
		if (!state.equals(controlState.ready)) 
		{
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("ReturnBookControl: Cannot call bookScanned except in READY state");
		}	
		book currentBook = library.Book(bookId);
		
		if (currentBook == null) 
		{
			ui.display("Invalid Book Id");
			return;
		}
		if (!currentBook.On_loan()) 
		{
			ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	
		double overDueFine = 0.0;
		
		if (currentLoan.isOverDue()) 
		{
			overDueFine = library.calculateOverDueFine(currentLoan);
		}
		ui.display("Inspecting");
		ui.display(currentBook.toString());
		ui.display(currentLoan.toString());
		
		if (currentLoan.isOverDue()) 
		{
			ui.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		//changed the variable name UI_STATE to uiState
		ui.setState(ReturnBookUI.uiState.INSPECTING);
		
		//changed the variable name CONTROL_STATE to controlState by Hashan
		state = controlState.INSPECTING;		
	}


	public void scanningComplete() 
	{
		//changed the variable name CONTROL_STATE to controlState, READY -> ready by Hashan
		if (!state.equals(controlState.ready)) 
		{
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("ReturnBookControl: Cannot call scanningComplete except in READY state");
		}	
		//changed the variable name UI_STATE to uiState
		ui.setState(ReturnBookUI.uiState.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) 
	{
		//changed the variable name CONTROL_STATE to controlState by Hashan
		if (!state.equals(controlState.INSPECTING))
		{
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("ReturnBookControl: Cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		//changed the variable name UI_STATE to uiState, READY -> ready
		ui.setState(ReturnBookUI.uiState.ready);
		
		//changed the variable name CONTROL_STATE to controlState, READY -> ready by Hashan
		state = controlState.READY;				
	}


}
