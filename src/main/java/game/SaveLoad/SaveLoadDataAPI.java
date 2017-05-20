package game.SaveLoad;

import game.Data.User;

/**
 * Created by jiraff537 on 5/19/17.
 */
public interface SaveLoadDataAPI<T> {//интерфейс сохраниния загрузки данных/ Дженерик для USER/GAME/TURN


    public int save(T user,int id);

    public T load(int id);

    public int size();

}
