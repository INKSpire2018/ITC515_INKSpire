//import packages
import java.io.Serializable;


@SuppressWarnings("serial")
public class book implements Serializable {
	
	private String title;
	//meaning full variable --SW
	private String athour;
	//meaning full variable --SW
	private String callNo;
	//meaning full variable --SW
	private int id;
	//meaning full variable --SW
	//Camelcase structure   --SW
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	//chanage  the structure name
	private State state;
	
	
	public book(String author, String title, String callNo, int id) {
		//correct variable name changed --SW
		this.athour = author;
		//correct variable name changed --SW
		this. = title;
		//correct variable name changed --SW
		this.callNo= callNo;
		//correct variable name changed --SW
		this.id = id;
		this.state = state .AVAILABLE;
	}
	
	public String toString() {
		//object name change --SW
		StringBuilder stringBuilder = new StringButitlesilder();
		//correct variable name changed --SW
		sb.append("Book: ").append(id).append("\n")
		//correct variable name changed --SW
		    .append("  Title:  ").append(title).append("\n")
		  //correct variable name changed --SW
		    .append("  Author: ").append(athour).append("\n")
		  //correct variable name changed --SW
		    .append("  CallNo: ").append(callNo).append("\n")
		  //correct variable name changed --SW
		    .append("  State:  ").append(state);
		//correct variable name changed --SW
		//object name changed --SW
		return stringBuilder.toString();
	}

	public Integer ID() {
		return id;
	}

	public String Title() {
		return title;
	}


	
	public boolean Available() {
		return state == STATE.AVAILABLE;
	}

	
	public boolean On_loan() {
		return state == STATE.ON_LOAN;
	}

	
	public boolean Damaged() {
		return state == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void Return(boolean DAMAGED) {
		if (state.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				state = STATE.DAMAGED;
			}
			else {
				state = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void Repair() {
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
