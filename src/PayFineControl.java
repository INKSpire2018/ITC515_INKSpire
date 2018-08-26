//first
public class PayFineControl {
	
	//Change object ui to payFineUi
	private PayFineUI payFineUi;
	//enum name CONTROL_STATE changed into ControlState
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	//enum name CONTROL_STATE changed into ControlState
	private ControlState state;
	
	//A class name cannot be started from simple letter.library changed to Library
	private Library library;
	//A class name cannot be started from simple letter.member changed to Member
	//Unnecessary semicolon removed
	private Member member;


	public PayFineControl() {
		//method name INSTANCE changed to getInstance
		this.library = library.getInstance();
		//enum name CONTROL_STATE changed into ControlState
		state = ControlState.INITIALISED;
	}
	
		
	//Change parameter ui to payFineUi
	public void setUI(PayFineUI payFineUi) {
		//enum name CONTROL_STATE changed into ControlState
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
			
		//Change objects ui to payFineUi
		this.payFineUi = payFineUi;
		//Change object ui to payFineUi
		payFineUi.setState(PayFineUI.UI_STATE.READY);
		//enum name CONTROL_STATE changed into ControlState
		state = ControlState.READY;		
	}


	public void cardSwiped(int memberId) {
		//enum name CONTROL_STATE changed into ControlState
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		member = library.getMember(memberId);
		
		if (member == null) {
			//Change object ui to payFineUi
			payFineUi.display("Invalid Member Id");
			return;
		}
		//Change object ui to payFineUi
		payFineUi.display(member.toString());
		//Change object ui to payFineUi
		payFineUi.setState(PayFineUI.UI_STATE.PAYING);
		//enum name CONTROL_STATE changed into ControlState
		state = ControlState.PAYING;
	}
	
	
	public void cancel() {
		//Change object ui to payFineUi
		payFineUi.setState(PayFineUI.UI_STATE.CANCELLED);
		//enum name CONTROL_STATE changed into ControlState
		state = ControlState.CANCELLED;
	}


	public double payFine(double amount) {
		//enum name CONTROL_STATE changed into ControlState
		if (!state.equals(ControlState.PAYING)) {
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = member.payFine(amount);
		if (change > 0) {
			//Change object ui to payFineUi
			payFineUi.display(String.format("Change: $%.2f", change));
		}
		//Change object ui to payFineUi
		payFineUi.display(member.toString());
		//Change object ui to payFineUi
		payFineUi.setState(PayFineUI.UI_STATE.COMPLETED);
		//enum name CONTROL_STATE changed into ControlState
		state = ControlState.COMPLETED;
		return change;
	}
	
//Unnecessary new line removed, shouldnt be more than 2 new lines between metods and classes.
}
