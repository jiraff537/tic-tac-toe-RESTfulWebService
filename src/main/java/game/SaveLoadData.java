package game;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiraff537 on 5/19/17.
 */
public class SaveLoadData implements SaveLoadDataAPI {
    List<User> users = new ArrayList<>();
    List<Game> games = new ArrayList<>();

//    SaveLoadData() {
//        users=null;
//    }

    @Override
    public int saveUser(User user) {
        users.add(user);
        return user.id;
    }

    @Override
    public User loadUser(int id) {
        return users.get(id);
    }

    @Override
    public int userSize() {
        return users.size();
    }
}
