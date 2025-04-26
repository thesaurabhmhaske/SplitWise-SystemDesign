import java.util.Comparator;

public class NegativeDebtsComparator implements Comparator<Debts> {
    public int compare(Debts d1, Debts d2) {
        if (d1.amountOwes < d2.amountOwes)
            return 1;
        else if (d1.amountOwes > d2.amountOwes)
            return -1;
        return 0;
    }
}

