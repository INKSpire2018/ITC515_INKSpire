

//Import necessary libraries
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self;
	
	//changed the variable name cal to calender by Hashan
	private static java.util.Calendar calender;
	
	//private constructor
	private Calendar() 
	{
		//changed the variable name cal to calender by Hashan
		calender = java.util.Calendar.getInstance();
	}
	
	public static Calendar getInstance() {
		if (self == null) {
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) 
	{
		//changed the variable name cal to calender by Hashan
		calender.add(java.util.Calendar.DATE, days);		
	}
	
	public synchronized void setDate(Date date) {
		try {
			//changed the variable name cal to calender by Hashan
			calender.setTime(date);
			
			//changed the variable name cal to calender by Hashan
	        calender.set(java.util.Calendar.HOUR_OF_DAY, 0);  
			
			//changed the variable name cal to calender by Hashan
	        calender.set(java.util.Calendar.MINUTE, 0);  
			
			//changed the variable name cal to calender by Hashan
	        calender.set(java.util.Calendar.SECOND, 0);  
			
			//changed the variable name cal to calender by Hashan
	        calender.set(java.util.Calendar.MILLISECOND, 0);
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() {
		try {
			//changed the variable name cal to calender by Hashan
	        calender.set(java.util.Calendar.HOUR_OF_DAY, 0);  
			
			//changed the variable name cal to calender by Hashan
	        calender.set(java.util.Calendar.MINUTE, 0);  
			
			//changed the variable name cal to calender by Hashan
	        calender.set(java.util.Calendar.SECOND, 0);  
			
			//changed the variable name cal to calender by Hashan
	        calender.set(java.util.Calendar.MILLISECOND, 0);
			
			//changed the variable name cal to calender by Hashan
			return calender.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDate(int loanPeriod) {
		Date now = Date();
		
		//changed the variable name cal to calender by Hashan
		calender.add(java.util.Calendar.DATE, loanPeriod);
		
		//changed the variable name cal to calender by Hashan
		Date dueDate = calender.getTime();
		
		//changed the variable name cal to calender by Hashan
		calender.setTime(now);
		
		//returns the due date
		return dueDate;
	}
	
	public synchronized long getDaysDifference(Date targetDate) {
		long diffMillis = Date().getTime() - targetDate.getTime();
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);
	    return diffDays;
	}

}
