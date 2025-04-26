import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Expense {
     User userPaid;
     String description;
     Long amount;
     Timestamp dateTime;
     List<Debts> splitDetails = new ArrayList<>();

    public Expense(User user, String description, Long amount) {
        this.userPaid = user;
        this.description = description;
        this.amount = amount;
        this.dateTime = new Timestamp(System.currentTimeMillis());
    }

    public List<Debts> splitEqually(List<User> groupUsers) {
        int groupSize = groupUsers.size();
        Double amountPerUser = (double) (this.amount / groupSize);
        for (User user : groupUsers) {
            if (user.equals(this.userPaid)) {
                this.splitDetails.add(new Debts(user, this.amount - amountPerUser));
            } else {
                this.splitDetails.add(new Debts(user, 0 - amountPerUser));
            }
        }
        return this.splitDetails;
    }

}
