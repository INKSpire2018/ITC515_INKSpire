import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	//meaning full variable --SW
	private BorrowBookUI bookuserinterface
	//meaning full variable --SW
	private library library;
    //meaning full variable --SW
	private member member;
	
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;
	
	private List<book> PENDING;
	private List<loan> COMPLETED;
	//meaning full variable --SW
	private book book;
	
	
	public BorrowBookControl() {
		//correct variable name changed --SW
		this. library= library.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	

	public void setUI(BorrowBookUI bookuserinterface) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) 
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
		//correct variable name changed --SW
		this.bookuserinterface = bookuserinterface;
		bookuserinterface.setState(BorrowBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

		
	public void Swiped(int memberId) {
		if (!state.equals(CONTROL_STATE.READY)) 
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		M = L.getMember(memberId);
		if (M == null) {
			ui.display("Invalid memberId");
			return;
		}
		if (L.memberCanBorrow(M)) {
			PENDING = new ArrayList<>();
			//correct variable name changed --SW
			bookuserinterface.setState(BorrowBookUI.UI_STATE.SCANNING);
			state = CONTROL_STATE.SCANNING; }
		else 
		{   //correct variable name changed --SW
			bookuserinterface.display("Member cannot borrow at this time");
			//correct variable name changed --SW
			bookuserinterface.setState(BorrowBookUI.UI_STATE.RESTRICTED); }}
	
	
	public void Scanned(int bookId) {
		//correct variable name changed --SW
		book = null;
		if (!state.equals(CONTROL_STATE.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}
       //correct variable name changed --SW		
		book = library.Book(bookId);
	   //correct variable name changed --SW	
		if (book == null) {
			//correct variable name changed --SW
			bookuserinterface.display("Invalid bookId");
			return;
		}    //correct variable name changed --SW
		if (!book.Available()) {
			//correct variable name changed --SW
			bookuserinterface.display("Book cannot be borrowed");
			return;
		}//correct variable name changed --SW
		PENDING.add(book);
		//correct variable name changed --SW
		for (book book : PENDING) {
			//correct variable name changed --SW
			bookuserinterface.display(book.toString());
		}   //correct variable name changed --SW
		if (library.loansRemainingForMember(M) - PENDING.size() == 0) {
			//correct variable name changed --SW
			bookuserinterface.display("Loan limit reached");
			Complete();
		}
	}
	
	
	public void Complete() {
		if (PENDING.size() == 0) {
			cancel();
		}
		else {
			bookuserinterface.display("\nFinal Borrowing List");
				//correct variable name changed --SW
			for (book book : PENDING) {
					//correct variable name changed --SW
				bookuserinterface.display(b.toString());
			}
			COMPLETED = new ArrayList<loan>();
				//correct variable name changed --SW
			bookuserinterface.setState(BorrowBookUI.UI_STATE.FINALISING);
			state = CONTROL_STATE.FINALISING;
		}
	}


	public void commitLoans() {
		if (!state.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		//correct variable name changed --SW
		for (book book : PENDING) {
			//correct variable name changed --SW
			loan loan = library.issueLoan(b, M);
			COMPLETED.add(loan);			
		}
		bookuserinterface.display("Completed Loan Slip");
		for (loan loan : COMPLETED) {
			//correct variable name changed --SW
			bookuserinterface.display(loan.toString());
		}
	    //correct variable name changed --SW
		bookuserinterface.setState(BorrowBookUI.UI_STATE.COMPLETED);
		state = CONTROL_STATE.COMPLETED;
	}

	
	public void cancel() {
		//change correct object
		bookuserinterface.setState(BorrowBookUI.UI_STATE.CANCELLED);
		state = CONTROL_STATE.CANCELLED;
	}	//change correct object
	
	
}
