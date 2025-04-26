import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Balance {
    List<Debts> finalDebtsList = new ArrayList<>();
    Map<User, List<Debts>> finalDebtsMap = new HashMap<>();

    public List<Debts> getFinalDebtsList() {
        return this.finalDebtsList;
    }

    public Map<User, List<Debts>> getFinalDebtsMap() {
        return this.finalDebtsMap;
    }

    public void setFinalDebtsList(List<Debts> finalDebtsList) {
        this.finalDebtsList = finalDebtsList;
    }

    public void setFinalDebtsMap(Map<User, List<Debts>> finalDebtsMap) {
        this.finalDebtsMap = finalDebtsMap;
    }
}
