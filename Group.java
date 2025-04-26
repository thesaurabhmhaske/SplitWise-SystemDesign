import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Group {
    // Entity Attributes..
    private String groupName;
    private User groupAdmin;
    private List<User> groupMembers = new ArrayList<>();
    private List<Expense> expensesList = new ArrayList<>();
    private Balance balanceDetails;

    public Group(String groupName, User groupAdmin) {
        this.groupName = groupName;
        this.groupAdmin = groupAdmin;
        this.groupMembers.add(groupAdmin);
        this.balanceDetails = new Balance();
    }

    public void addUsers(User user) {
        this.groupMembers.add(user);
    }

    public User getGroupAdmin() {
        return this.groupAdmin;
    }

    public void removeUser(User user) {
        this.groupMembers.remove(user);
    }

    public List<Expense> getExpenses() {
        return this.expensesList;
    }

    public Balance getBalanceDetails() {
        return this.balanceDetails;
    }

    public void addExpense(User user, String description, Long amount) {
        Expense expense = new Expense(user, description, amount);
        List<Debts> debtsPerUser = expense.splitEqually(this.groupMembers);
        expensesList.add(expense);
        calculateTotalExpense(debtsPerUser);
    }

    private void calculateTotalExpense(List<Debts> debts) {
        List<Debts> debtsList = this.balanceDetails.getFinalDebtsList();
        if (debtsList.isEmpty()) {
            this.balanceDetails.setFinalDebtsList(debts);
        } else {
            for (Debts d : debts) {
                for (Debts d1 : debtsList) {
                    if (d1.user.equals(d.user)) {
                        d1.amountOwes += d.amountOwes;
                    }
                }
            }
            this.balanceDetails.setFinalDebtsList(debtsList);
        }
        List<Debts> finalDebtsList = this.balanceDetails.getFinalDebtsList();
        PriorityQueue<Debts> positiveDebts = new PriorityQueue<Debts>(new PositiveDebtsComparator());
        PriorityQueue<Debts> negativeDebts = new PriorityQueue<Debts>(new NegativeDebtsComparator());
        for (Debts d : finalDebtsList) {
            if (d.amountOwes > 0) {
                positiveDebts.add(d);
            } else if (d.amountOwes < 0) {
                negativeDebts.add(d);
            }
        }
        Map<User, List<Debts>> debtsMap = new HashMap<>();
        while (!positiveDebts.isEmpty()) {
            Debts positiveDebt = positiveDebts.poll();
            double amt = 0.0;
            List<Debts> applicableNegDebts = new ArrayList<>();

            while (!negativeDebts.isEmpty()) {
                Debts negativDebt = new Debts(negativeDebts.peek().user,negativeDebts.peek().amountOwes);
                negativeDebts.poll();
                if (Math.abs(negativDebt.amountOwes) >= (amt + positiveDebt.amountOwes)) {
                    Debts fixUserDebts = new Debts(negativDebt.user, 0 - amt - positiveDebt.amountOwes);
                    negativDebt.amountOwes += amt + positiveDebt.amountOwes;
                    negativeDebts.add(negativDebt);
                    applicableNegDebts.add(fixUserDebts);
                    break;
                } else {
                    amt += negativDebt.amountOwes;
                    applicableNegDebts.add(negativDebt);
                    if (positiveDebt.amountOwes.equals(Math.abs(amt))) {
                        break;
                    }

                }
            }
            debtsMap.put(positiveDebt.user, applicableNegDebts);
        }
        this.balanceDetails.setFinalDebtsMap(debtsMap);
    }
}
