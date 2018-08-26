

//Import necessary libraries
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar 
{
	
	private static Calendar self;
	
	//changed the variable name cal to calender by Hashan
	private static java.util.Calendar calender;
	
	//private constructor
	private Calendar() 
	{
		//changed the variable name cal to calender by Hashan
		calender = java.util.Calendar.getInstance();
	}
	
	public static Calendar getInstance() 
	{
		if (self == null) 
		{
			self = new Calendar();
		}
		return self;
	}
	
	public void incrementDate(int days) 
	{
		//changed the variable name cal to calender, DATE-> date by Hashan
		calender.add(java.util.Calendar.date, days);		
	}
	
	public synchronized void setDate(Date date) 
	{
		//try block starts here
		try 
		{
			//changed the variable name cal to calender by Hashan
			calender.setTime(date);
			
			//changed the variable name cal to calender,HOUR_OF_DAY -> hourOfDay by Hashan
	        calender.set(java.util.Calendar.hourOfDay, 0);  
			
			//changed the variable name cal to calender by, MINUTE -> minute Hashan
	        calender.set(java.util.Calendar.minute, 0);  
			
			//changed the variable name cal to calender, SECOND-> second by Hashan
	        calender.set(java.util.Calendar.second, 0);  
			
			//changed the variable name cal to calender, MILLISECOND -> millisecond by Hashan
	        calender.set(java.util.Calendar.millisecond, 0);
		}// catches exceptions if occured
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date Date() 
	{
		//try block starts here
		try {
			//changed the variable name cal to calender,HOUR_OF_DAY -> hourOfDay  by Hashan
	        calender.set(java.util.Calendar.hourOfDay, 0);  
			
			//changed the variable name cal to calender MINUTE -> minute  by Hashan
	        calender.set(java.util.Calendar.minute, 0);  
			
			//changed the variable name cal to calender, SECOND-> second  by Hashan
	        calender.set(java.util.Calendar.second, 0);  
			
			//changed the variable name cal to calender, MILLISECOND -> millisecond  by Hashan
	        calender.set(java.util.Calendar.millisecond, 0);
			
			//changed the variable name cal to calender by Hashan
			return calender.getTime();
		}// catches exceptions if occured
		catch (Exception e) 
		{
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date getDueDate(int loanPeriod) 
	{
		Date now = Date();
		
		//changed the variable name cal to calender, DATE -> date by Hashan
		calender.add(java.util.Calendar.date, loanPeriod);
		
		//changed the variable name cal to calender by Hashan
		Date dueDate = calender.getTime();
		
		//changed the variable name cal to calender by Hashan
		calender.setTime(now);
		
		//returns the due date
		return dueDate;
	}
	
	public synchronized long getDaysDifference(Date targetDate) 
	{
		//changed the variable name diffMillis to differenceMillisecounds by Hashan
		long differenceMillisecounds = Date().getTime() - targetDate.getTime();
		
		//changed the variable name diffDays to diffenceDays by Hashan
		//Added meaningful variable names by hashan diffMillis-> differenceMillisecounds, DAYS -> days
	    long diffenceDays = TimeUnit.days.convert(differenceMillisecounds, TimeUnit.MILLISECONDS);
		
		//changed the variable name diffDays to diffenceDays by Hashan
	    return diffenceDays;
	}

}
