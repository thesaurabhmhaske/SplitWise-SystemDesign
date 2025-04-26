import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        User userA = new User(1, "Saurabh", "saurabh@abc.com", "123456789");
        User userB = new User(2, "Mohit", "mohit@abc.com", "987456321");
        User userC = new User(3, "Sandeep", "sandeep@abc.com", "654789321");

        Group groupA = userA.createGroup("Gaur City Wale");

        userA.addGroupMembers(groupA, userB);
        userA.addGroupMembers(groupA, userC);

        userB.removeGroupMembers(groupA, userC);
        userA.removeGroupMembers(groupA, userB);

        userA.addGroupMembers(groupA, userB);

        userA.addExpenseInGroup(groupA, "25th Morning Vegetables", 60L);
        System.out.println("First Split details:");
        printExpenses(groupA.getExpenses());
        printBalance(groupA.getBalanceDetails());
        
        userB.addExpenseInGroup(groupA, "25th Evening Vegetables", 45L);
        System.out.println("Second Split details:");
        printExpenses(groupA.getExpenses());
        printBalance(groupA.getBalanceDetails());

        userC.addExpenseInGroup(groupA, "26th Morning Food", 30L);
        System.out.println("Third Split details:");
        printExpenses(groupA.getExpenses());
        printBalance(groupA.getBalanceDetails());

    }

    private static void printExpenses(List<Expense> expense){
        for(Expense it:expense){
            System.out.println("    "+it.userPaid.name);
            System.out.print("        "+it.description);
            System.out.println(" "+it.amount);
        }
    }

    private static void printBalance(Balance balance){
        for (Map.Entry<User, List<Debts>> entry : balance.finalDebtsMap.entrySet()) {
            User user = entry.getKey();
            List<Debts> debtsList = entry.getValue();
        
            System.out.println("    User: " + user.name); 
        
            for (Debts debt : debtsList) {
                System.out.println("        Debt: " + debt.user.name + " owes " + debt.amountOwes);
            }
        }
    }
}