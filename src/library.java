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
	changed the variable type from library to Library by Malinga
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
	changed the method name from INSTANCE to getLibraryInstance
	by Malinga
	*/
	public static synchronized Library getLibraryInstance() {	
		//changed the variable self to libraryInstance by Malinga	
		if (libraryInstance == null) {
			Path path = Paths.get(LIBRARY_FILE);			
			if (Files.exists(path)) {	
				//changed the variable lof to objectInputStream by Malinga
				try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(LIBRARY_FILE));) {
			    	//changed the variable self to libraryInstance by Malinga
					libraryInstance = (library) objectInputStream.readObject();
					//changed the variable self to libraryInstance by Malinga
					Calendar.getInstance().setDate(libraryInstance.loadDate);
					//changed the variable lof to objectInputStream by Malinga
					objectInputStream.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			/*
			changed the variable self to libraryInstance 
			changed the constructor library to Library
			added paranthesis to the else statement
			by Malinga
			*/
			else {
				libraryInstance = new Library();
			}
		}
		//changed the variable self to libraryInstance by Malinga
		return libraryInstance;
	}

	//changed the method name SAVE to saveLibraryInstance by Malinga
	public static synchronized void saveLibraryInstance() {
		//changed the variable self to libraryInstance by Malinga
		if (libraryInstance != null) {
			libraryInstance.loadDate = Calendar.getInstance().Date();

			//changed the variable lof to objectOutputStream by Malinga
			try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(LIBRARY_FILE));) {
				objectOutputStream.writeObject(libraryInstance);
				objectOutputStream.flush();
				objectOutputStream.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	/*
	Changed the method name BookID to getBookId
	Changed the variable BID to bookId
	by Malinga
	*/
	public int getBookId() {
		return bookId;
	}
	
	/*
	Changed the method name MemberID to getMemberId
	Changed the variable MID to memberId
	by Malinga
	*/
	public int getMemberId() {
		return memberId;
	}
	
	/*
	Changed the method name nextBID to getNextBookId
	Changed the variable BID to bookId
	by Malinga
	*/
	private int getNextBookId() {
		return bookId++;
	}

	/*
	Changed the method name nextMID to getNextMemberId
	Changed the variable MID to memberId
	by Malinga
	*/
	private int getNextMemberId() {
		return memberId++;
	}

	/*
	Changed the method name nextLID to getNextLoanId
	Changed the variable LID to loanId
	by Malinga
	*/
	private int getNextLoanId() {
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

	/*
	Changed the method name Add_mem to addNewMember
	Changed the method name nextMID to getNextMemberId
	by Malinga
	*/
	public member addNewMember(String lastName, String firstName, String email, int phoneNo) {		
		member member = new member(lastName, firstName, email, phoneNo, getNextMemberId());
		members.put(member.getId(), member);		
		return member;
	}

	/*
	Changed the method name Add_book to addNewBook
	Changed the method name nextBID to getNextBookId
	Changed the parameter variable a to author, t to title, c to callNo and b to book
	by Malinga
	*/
	public book addNewBook(String author, String title, String callNo) {		
		book book = new book(author, title, callNo, getNextBookId());
		catalog.put(book.ID(), book);		
		return book;
	}

	//Added paranthesis to the if statement by Malinga
	public member getMember(int memberId) {
		if (members.containsKey(memberId)) {
			return members.get(memberId);
		}
		return null;
	}

	/*
	Added paranthesis to the if statement
	Change the method name Book to getBook
	by Malinga
	*/
	public book getBook(int bookId) {
		if (catalog.containsKey(bookId)) {
			return catalog.get(bookId);		
		}
		return null;
	}

	//Changed the method name loanLimit to getLoanLimit
	public int getLoanLimit() {
		return LOAN_LIMIT;
	}

	/*
	Added paranthesis to the if statements and for loop
	Change the method name memberCanBorrow to canMemberBorrowBooks
	by Malinga
	*/
	public boolean canMemberBorrowBooks(member member) {		
		if (member.getNumberOfCurrentLoans() == LOAN_LIMIT ) {
			return false;
		}
				
		if (member.getFinesOwed() >= MAX_FINES_OWED) {
			return false;
		}
				
		for (loan loan : member.getLoans()) {
			if (loan.isOverDue()) {
				return false;
			}
		}
			
		return true;
	}

	
	public int loansRemainingForMember(member member) {		
		return LOAN_LIMIT - member.getNumberOfCurrentLoans();
	}

	
	public loan issueLoan(book book, member member) {
		Date dueDate = Calendar.getInstance().getDueDate(LOAN_PERIOD);
		loan loan = new loan(nextLID(), book, member, dueDate);
		member.takeOutLoan(loan);
		book.Borrow();
		loans.put(loan.getId(), loan);
		currentLoans.put(book.ID(), loan);
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
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate());
			double fine = daysOverDue * FINE_PER_DAY;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	
		
		member.dischargeLoan(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(DAMAGE_FEE);
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.Loan();
		currentLoans.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) {
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
