import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

//changed the class name from loan to Loan by Malinga
@SuppressWarnings("serial")
public class Loan implements Serializable {
	
	//changed the enum name from LOAN_STATE to LoanState by Malinga
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };
	
	//changed the variable name ID to loanId by Malinga
	private int loanId;
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
		//changed the variable name ID to loanId by Malinga
		this.loanId = loanId;
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
		//changed the variable type LOAN_STATE to LoanState by Malinga
		//changed the variable name D to date by Malinga
		if (state == LoanState.CURRENT &&
			Calendar.getInstance().Date().after(date)) {
			this.state = LoanState.OVER_DUE;			
		}
	}

	
	//changed the variable type LOAN_STATE to LoanState by Malinga
	public boolean isOverDue() {
		return state == LoanState.OVER_DUE;
	}


	//changed the variable name ID to loanId by Malinga
	//changed the method name getId to getLoanId by Malinga
	public Integer getLoanId() {
		return loanId;
	}


	//changed the variable name D to date by Malinga
	public Date getDueDate() {
		return date;
	}
	
	
	public String toString() {

		//changed the variable name sdf to simpleDateFormat by Malinga
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

		//changed the variable name sb to stringBuilder by Malinga
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Loan:  ").append(id).append("\n")
		  .append("  Borrower ").append(member.getId()).append(" : ")
		  .append(member.getLastName()).append(", ").append(M.getFirstName()).append("\n")
		  .append("  Book ").append(B.ID()).append(" : " )
		  .append(B.Title()).append("\n")
		  .append("  DueDate: ").append(simpleDateFormat.format(D)).append("\n")
		  .append("  State: ").append(state);		
		return stringBuilder.toString();
	}


	//changed the method name Member to getMember by Malinga
	public member getMember() {
		//changed the variable name M to member by Malinga
		return member;
	}


	//changed the method name Book to GetBook by Malinga
	public book getBook() {
		//changed the variable name B to book by Malinga
		return book;
	}


	//changed method name Loan to setLoanState by Malinga
	public void setLoanState() {
		//changed the variable type LOAN_STATE to LoanState by Malinga
		state = LoanState.DISCHARGED;		
	}

}
