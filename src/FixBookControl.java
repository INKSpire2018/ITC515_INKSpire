// Class neme declaretion
public class FixBookControl {
	
	//Variables
	private FixBookUI ui;
	//changed the variable name CONTROL_STATE to controlState, INITIALISED -> initialised, READY -> ready, FIXING -> fixing
	private enum controlState { initialised, ready, fixing };
	//changed the variable name CONTROL_STATE to controlState
	private controlState state;
	
	private library library;
	private book currentBook;


	public FixBookControl() 
	{
		//changed the method name INSTANCE to instance
		this.library = library.instance();
		
		//changed the variable name CONTROL_STATE to controlState, INITIALISED -> initialised
		state = controlState.initialised;
	}
	
	
	public void setUI(FixBookUI ui) 
	{
		//changed the variable name CONTROL_STATE to controlState, INITIALISED -> initialised
		if (!state.equals(controlState.initialised)) {
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("FixBookControl: Cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		//changed the variable name UI_STATE to uiState, READY -> ready
		ui.setState(FixBookUI.uiState.ready);
		//changed the variable name CONTROL_STATE to controlState, READY -> ready
		state = controlState.ready;		
	}


	public void bookScanned(int bookId) 
	{	//changed the variable name CONTROL_STATE to controlState, READY -> ready
		if (!state.equals(controlState.ready)) {
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("FixBookControl: Cannot call bookScanned except in READY state");
		}	
		currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!currentBook.Damaged()) {
			ui.display("\"Book has not been damaged");
			return;
		}
		ui.display(currentBook.toString());
		//changed the variable name UI_STATE to uiState, FIXING-> fixing
		ui.setState(FixBookUI.uiState.fixing);
		//changed the variable name CONTROL_STATE to controlState, FIXING-> fixing
		state = controlState.fixing;		
	}


	public void fixBook(boolean fix) 
	{
		//changed the variable name CONTROL_STATE to controlState, FIXING-> fixing
		if (!state.equals(controlState.fixing)) {
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("FixBookControl: Cannot call fixBook except in FIXING state");
		}	
		if (fix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		//changed the variable name UI_STATE to uiState, READY -> ready
		ui.setState(FixBookUI.uiState.ready);
		//changed the variable name CONTROL_STATE to controlState, READY -> ready
		state = controlState.ready;		
	}

	
	public void scanningComplete() 
	{
		//changed the variable name CONTROL_STATE to controlState, READY -> ready
		if (!state.equals(controlState.ready)) {
			// Change the wording in the Exception -> to start with a capital letter "Cannot"
			throw new RuntimeException("FixBookControl: Cannot call scanningComplete except in READY state");
		}	
		//changed the variable name UI_STATE to uiState, COMPLETED -> completed
		ui.setState(FixBookUI.uiState.completed);		
	}






}
