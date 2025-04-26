import java.util.ArrayList;
import java.util.List;

public class User {
    Integer userId;
    String name;
    String email;
    String mobileNumber;
    List<Group> groupList = new ArrayList<>();

    public User(Integer userId, String name, String email, String mobileNumber){
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.mobileNumber = mobileNumber;
    }

    public Group createGroup(String groupName){
        Group groupA = new Group(groupName, this);
        this.groupList.add(groupA);
        return groupA;
    }

    public void addGroupMembers(Group group, User user){
        if(group.getGroupAdmin().userId.equals(this.userId)){
            group.addUsers(user);
        }
        else System.out.println("Only Admin can add Users"); 
    }

    public void removeGroupMembers(Group group, User user){
        if(group.getGroupAdmin().userId.equals(this.userId)){
            group.removeUser(user);
        }
        else System.out.println("Only Admin can remove Users");
    }

    public void addExpenseInGroup(Group group, String description, Long amount){
        group.addExpense(this, description, amount);
    }


}
