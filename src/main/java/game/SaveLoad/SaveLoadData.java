package game.SaveLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiraff537 on 5/19/17.
 */
public class SaveLoadData<T> implements SaveLoadDataAPI<T> {
    List<T> data = new ArrayList<>();

    @Override
    public int save(T t,int id) {
        data.add(t);
        return data.size();
    }

    @Override
    public T load(int id) {
        return data.get(id);
    }

    @Override
    public int size() {
        return data.size();
    }
}
