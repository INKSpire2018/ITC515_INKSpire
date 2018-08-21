/*
Import necessary libraries
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
//chnaged class name to capital - Mihian
public class Member implements Serializable {

    //change LN -> lastName
    private String lastName;
    //FN->firstName
    private String firstName;
    //EM -> email
    private String email;
    //PN -> phone
    private int phone;
    //ID->id
    private int id;
    //FINES
    private double fines;
    //LNS -> loansList
    private Map<Integer, loan> loansList;

    public member(String lastName, String firstName, String email, int phoneNo, int id) {
        //LN->lastName
        this.lastName = lastName;
        //LN->firstName
        this.firstName = firstName;
        //EM->email
        this.email = email;
        //PN -> phone
        this.phone = phoneNo;
        //ID -> id
        this.id = id;

        //LNS->loansList
        this.loansList = new HashMap<>();
    }

	//override toString method
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //ID->id
        sb.append("Member:  ").append(id).append("\n")
            //LN->lastName
            .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")
            //EM->email
            .append("  Email: ").append(email).append("\n")
            //PN->phone
            .append("  Phone: ").append(phone)
            .append("\n")
            //FINES->fines
            .append(String.format("  Fines Owed :  $%.2f", fines))
            .append("\n");

        //LNS ->loansList
        for (loan loan : loansList.values()) {
            sb.append(loan).append("\n");
        }
        return sb.toString();
    }

	//accessor method for id
    public int getId() {
        //ID->id
        return id;
    }

    public List<loan> getLoans() {
        //LNS->loansList
        return new ArrayList<loan>(loansList.values());
    }

    public int getNumberOfCurrentLoans() {
        //LNS->loansList
        return loansList.size();
    }

    public double getFinesOwed() {
        //FINES->fines
        return fines;
    }

    public void takeOutLoan(loan loan) {
        //LNS->loansList
        if (!loansList.containsKey(loan.getId())) {
            loansList.put(loan.getId(), loan);
        } else {
            throw new RuntimeException("Duplicate loan added to member");
        }
    }

    public String getLastName() {
        //LN->lastName
        return lastName;
    }

    public String getFirstName() {
        //FN -> firstName
        return firstName;
    }

    public void addFine(double fine) {
        //FINES->fines
        fines += fine;
    }

    public double payFine(double amount) {
        if (amount < 0) {
            throw new RuntimeException("Member.payFine: amount must be positive");
        }
        double change = 0;
        //FINES->fines
        if (amount > fines) {
            //FINES->fines
            change = amount - fines;
            //FINES->fines
            fines = 0;
        } else {
            //FINES->fines
            fines -= amount;
        }
        return change;
    }

    public void dischargeLoan(loan loan) {
        //LNS->loansList
        if (loansList.containsKey(loan.getId())) {
            loansList.remove(loan.getId());
        } else {
            throw new RuntimeException("No such loan held by member");
        }
    }

}
