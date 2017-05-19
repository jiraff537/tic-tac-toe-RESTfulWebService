package game.SaveLoad;

import game.Data.User;

/**
 * Created by jiraff537 on 5/19/17.
 */
public interface SaveLoadDataAPI {//интерфейс сохраниния загрузки данных данных

    public int saveUser(User user);

    public User loadUser(int id);

    public int userSize();

}
