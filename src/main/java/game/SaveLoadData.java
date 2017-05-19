package game;

/**
 * Created by jiraff537 on 5/19/17.
 */
public interface SaveLoadData {//интерфейс сохраниния загрузки данных данных

    public int saveUser(User user);
    public User loadUser(int id);

}
