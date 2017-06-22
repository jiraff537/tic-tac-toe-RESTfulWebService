package com.simbirsoft.tictactoe.SaveLoad;

import java.util.ArrayList;
import java.util.List;

/**
 * Реализация интерфейса сохранения и получения данных
 * легко может быть заменена на сохранение данных в любом месте хранения от базы данных до простой серриализации объекта в файл
 */
public class SaveLoadData<T> implements SaveLoadDataAPI<T> {
    private List<T> data = new ArrayList<>();

    @Override
    public boolean add(T t, int id) {
        data.add(id,t);
        return true;//data.size();
    }

    @Override
    public T get(int id) {
        return data.get(id);
    }

    @Override
    public int size() {
        return data.size();
    }
}
