import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//changed the class name from library to Library by Malinga
@SuppressWarnings("serial")
public class Library implements Serializable {
	
	private static final String LIBRARY_FILE = "library.obj";
	private static final int LOAN_LIMIT = 2;
	private static final int LOAN_PERIOD = 2;
	private static final double FINE_PER_DAY = 1.0;
	private static final double MAX_FINES_OWED = 5.0;
	private static final double DAMAGE_FEE = 2.0;
	
	/*
	changed the variable type from library to Library
	changed the Library variable name self to libraryInstance
	by Malinga
	*/
	private static Library libraryInstance;

	//changed the variable name BID to bookId by Malinga
	private int bookId;
	//changed the variable name MID to memberId by Malinga
	private int memberId;
	//changed the variable name LID to loanId by Malinga
	private int loanId;
	private Date loadDate;
	
	private Map<Integer, book> catalog;
	private Map<Integer, member> members;
	private Map<Integer, loan> loans;
	private Map<Integer, loan> currentLoans;
	private Map<Integer, book> damagedBooks;
	

	//changed the constructor from library to Library by Malinga
	private Library() {
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		//changed the variable name BID to bookId by Malinga
		bookId = 1;
		//changed the variable name MID to memberId by Malinga
		memberId = 1;
		//changed the variable name LID to loanId by Malinga		
		loanId = 1;		
	}


	/*
	changed the type from library to Library
	changed the method name from INSTANCE to getInstance
	by Malinga
	*/
	public static synchronized Library getInstance() {	
		//changed the variable self to libraryInstance by Malinga	
		if (libraryInstance == null) {
			Path path = Paths.get(LIBRARY_FILE);			
			if (Files.exists(path)) {	
				//changed the variable lof to objectInputStream by Malinga
				try (
					//no method calls within argument lists of any method calls - by Malinga
					FileInputStream fileInputStream = new FileInputStream(LIBRARY_FILE);
					ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
					) {
			    	//changed the variable self to libraryInstance by Malinga
					libraryInstance = (library) objectInputStream.readObject();
					//changed the variable self to libraryInstance by Malinga
					Calendar.getInstance().setDate(libraryInstance.loadDate);
					//changed the variable lof to objectInputStream by Malinga
					//below statement is not needed with try-with-resource statement
					objectInputStream.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			/*
			changed the variable self to libraryInstance 
			changed the constructor library to Library
			added parentheses to the else statement
			by Malinga
			*/
			else {
				libraryInstance = new Library();
			}
		}
		//changed the variable self to libraryInstance by Malinga
		return libraryInstance;
	}


	//changed the method name SAVE to save by Malinga
	public static synchronized void save() {
		//changed the variable self to libraryInstance by Malinga
		if (libraryInstance != null) {
			libraryInstance.loadDate = Calendar.getInstance().Date();

			//changed the variable lof to objectOutputStream by Malinga			
			try (
				//no method calls within argument lists of any method calls - by Malinga
				FileOutputStream fileOutputStream = new FileOutputStream(LIBRARY_FILE);
				ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
				) {
				//changed the variable lof to objectOutputStream by Malinga
				objectOutputStream.writeObject(libraryInstance);
				//changed the variable lof to objectOutputStream by Malinga
				objectOutputStream.flush();
				//changed the variable lof to objectOutputStream by Malinga
				//below statement is not needed with try-with-resource statement
				objectOutputStream.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	//Changed the method name BookID to getBookId by Malinga
	public int getBookId() {
		//Changed the variable BID to bookId by Malinga
		return bookId;
	}
	
	
	//Changed the method name MemberID to getMemberId by Malinga
	public int getMemberId() {
		//Changed the variable MID to memberId by Malinga
		return memberId;
	}
	
	
	//Changed the method name nextBID to getNextBookId
	private int getNextBookId() {
		//Changed the variable BID to bookId by Malinga
		return bookId++;
	}

	
	//Changed the method name nextMID to getNextMemberId by Malinga
	private int getNextMemberId() {
		//Changed the variable MID to memberId by Malinga
		return memberId++;
	}

	
	//Changed the method name nextLID to getNextLoanId by Malinga	
	private int getNextLoanId() {
		//Changed the variable LID to loanId by Malinga
		return loanId++;
	}


	//Changed the method name Members to getMembersList
	public List<member> getMembersList() {		
		return new ArrayList<member>(members.values()); 
	}


	//Changed the method name Books to getBooksList
	public List<book> getBooksList() {		
		return new ArrayList<book>(catalog.values()); 
	}


	//Changed the method name CurrentLoans to getCurrentLoansList
	public List<loan> getCurrentLoansList() {
		return new ArrayList<loan>(currentLoans.values());
	}


	
	//Changed the method name Add_mem to addNewMember by Malinga
	public member addNewMember(String lastName, String firstName, String email, int phoneNo) {
		int memberId = member.getId();		
		//Changed the method name nextMID to getNextMemberId by Malinga
		member member = new member(lastName, firstName, email, phoneNo, getNextMemberId());
		//no method calls within argument lists of any method calls - by Malinga
		members.put(memberId, member);		
		return member;
	}


	/*
	Changed the method name Add_book to addNewBook	
	Changed the parameter variable a to author, t to title, c to callNo and b to book
	by Malinga
	*/
	public book addNewBook(String author, String title, String callNo) {	
		int bookId = book.ID();
		//Changed the method name nextBID to getNextBookId  by Malinga
		book book = new book(author, title, callNo, getNextBookId());
		//no method calls within argument lists of any method calls - by Malinga
		catalog.put(bookId, book);		
		return book;
	}

	
	//Change the method name Member to getMember by Malinga
	public member getMember(int memberId) {
		//Added parentheses to the if statement by Malinga
		if (members.containsKey(memberId)) {
			return members.get(memberId);
		}
		return null;
	}

	
	//Change the method name Book to getBook by Malinga	
	public book getBook(int bookId) {
		//Added parentheses to the if statement by Malinga
		if (catalog.containsKey(bookId)) {
			return catalog.get(bookId);		
		}
		return null;
	}

	//Changed the method name loanLimit to getLoanLimit
	public int getLoanLimit() {
		return LOAN_LIMIT;
	}

	//Changed the method name memberCanBorrow to canMemberBorrowBooks by Malinga
	public boolean canMemberBorrowBooks(member member) {	

		//Added parentheses to the if statement by Malinga
		if (member.getNumberOfCurrentLoans() == LOAN_LIMIT ) {
			return false;
		}

		//Added parentheses to the if statements by Malinga
		if (member.getFinesOwed() >= MAX_FINES_OWED) {
			return false;
		}

		//Added parentheses to the for loop by Malinga		
		for (loan loan : member.getLoans()) {
			if (loan.isOverDue()) {
				return false;
			}
		}
			
		return true;
	}


	//Changed the method name loansRemainingForMember to getLoansRemainingForMember by Malinga
	public int getLoansRemainingForMember(member member) {		
		return LOAN_LIMIT - member.getNumberOfCurrentLoans();
	}

	
	public loan issueLoan(book book, member member) {
		int bookId = book.ID();
		int loanId = loan.getId();
		Date dueDate = Calendar.getInstance().getDueDate(LOAN_PERIOD);
		loan loan = new loan(nextLID(), book, member, dueDate);
		member.takeOutLoan(loan);
		book.Borrow();
		//no method calls within argument lists of any method calls - by Malinga
		loans.put(loanId, loan);
		//no method calls within argument lists of any method calls - by Malinga
		currentLoans.put(bookId, loan);
		return loan;
	}
	
	
	public loan getLoanByBookId(int bookId) {
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) {
		if (loan.isOverDue()) {
			//no method calls within argument lists of any method calls - by Malinga
			Date dueDate = loan.getDueDate();
			long daysOverDue = Calendar.getInstance().getDaysDifference(dueDate);
			double fine = daysOverDue * FINE_PER_DAY;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		int bookId = book.ID();
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	
		
		member.dischargeLoan(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);
			//no method calls within argument lists of any method calls - by Malinga
			damagedBooks.put(bookId, book);
		}
		currentLoan.Loan();
		//no method calls within argument lists of any method calls - by Malinga
		currentLoans.remove(bookId);
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) {
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {
		int currentBookId = currentBook.ID();
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();
			//no method calls within argument lists of any method calls - by Malinga
			damagedBooks.remove(currentBookId);
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
