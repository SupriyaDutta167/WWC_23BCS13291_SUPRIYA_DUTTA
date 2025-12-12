import java.util.*;

class InvalidTransactionException extends Exception{
    public InvalidTransactionException(String message){
        super(message);
    }
}

class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String message){
        super(message);
    }
}

public class BankAccount{
    protected double balance;

    public BankAccount(double balance){
        this.balance = balance;
    }

    public void processTransaction(String type, double amount)
            throws InsufficientBalanceException, InvalidTransactionException{

        if(amount<=0){
            throw new InvalidTransactionException("Invalid Transaction");
        }

        if(type.equals("Deposit")){
            balance += amount;
        }
        else if(type.equals("Withdrawal")){
            if(balance < amount){
                throw new InsufficientBalanceException("Insufficient Balance");
            }
            balance -= amount;
        }
        else{
            throw new InvalidTransactionException("Invalid Transaction Balance");
        }
    }

    public double getBalance(){
        return balance;
    }
}

// we will be using composition
class SavingsAccount extends BankAccount {
    private double InterestRate;

    public SavingsAccount(double InitialBalance, double InterestRate){
        super(InitialBalance);             
        this.InterestRate = InterestRate;
    }

    @Override
    public void processTransaction(String type, double amount)
            throws InsufficientBalanceException, InvalidTransactionException {

        System.out.println("[SavingsAccount Override Called]");
        super.processTransaction(type, amount); 
    }

    public void addInterest(){
        double interest = balance * InterestRate;
        try{
            super.processTransaction("Deposit", interest);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}

class BankingSystem{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        BankAccount bankAccount = new SavingsAccount(5000, 0.05);  // FIX 4: use SavingsAccount

        System.out.println("Enter the type:");
        String type = input.nextLine();

        System.out.println("Enter the amount:");
        double amount  = input.nextDouble();

        try{
            bankAccount.processTransaction(type, amount);
        }
        catch (InvalidTransactionException e){
            System.out.println(e.getMessage());
        }
        catch (InsufficientBalanceException e){
            System.out.println(e.getMessage());
        }

        System.out.println("The balance is: " + bankAccount.getBalance());
    }
}
