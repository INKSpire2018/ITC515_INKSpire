import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//changed the class name from loan to Loan by Malinga
@SuppressWarnings("serial")
public class Loan implements Serializable {
	
	//changed the enum name from LOAN_STATE to LoanState by Malinga
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };
	
	//changed the variable name ID to id by Malinga
	private int id;
	//changed the variable name B to book by Malinga
	private book book;
	//changed the variable name M to member by Malinga
	private member member;
	//changed the variable name D to date by Malinga
	private Date date;
	//changed the variable type LOAN_STATE to LoanState by Malinga
	private LOAN_STATE state;

	
	//changed the constructor name from loan to Loan by Malinga
	public Loan(int loanId, book book, member member, Date dueDate) {
		//changed the variable name ID to id by Malinga
		this.id = loanId;
		//changed the variable name B to book by Malinga
		this.book = book;
		//changed the variable name M to member by Malinga
		this.member = member;
		//changed the variable name D to date by Malinga
		this.date = dueDate;
		//changed the variable type LOAN_STATE to LoanState by Malinga
		this.state = LoanState.CURRENT;
	}

	
	public void checkOverDue() {
		if (state == LOAN_STATE.CURRENT &&
			Calendar.getInstance().Date().after(D)) {
			this.state = LOAN_STATE.OVER_DUE;			
		}
	}

	
	public boolean isOverDue() {
		return state == LOAN_STATE.OVER_DUE;
	}

	
	public Integer getId() {
		return ID;
	}


	public Date getDueDate() {
		return D;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(ID).append("\n")
		  .append("  Borrower ").append(M.getId()).append(" : ")
		  .append(M.getLastName()).append(", ").append(M.getFirstName()).append("\n")
		  .append("  Book ").append(B.ID()).append(" : " )
		  .append(B.Title()).append("\n")
		  .append("  DueDate: ").append(sdf.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member Member() {
		return M;
	}


	public book Book() {
		return B;
	}


	public void Loan() {
		state = LOAN_STATE.DISCHARGED;		
	}

}
