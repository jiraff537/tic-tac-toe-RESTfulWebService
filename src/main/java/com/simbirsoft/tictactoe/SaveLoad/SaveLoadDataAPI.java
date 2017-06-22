package com.simbirsoft.tictactoe.SaveLoad;

/**
 * Интерфейс сохранения и получения данных
 */
public interface SaveLoadDataAPI<T> {//интерфейс сохраниния загрузки данных/ Дженерик для USER/GAME


    boolean add(T t, int id);

    T get(int id);

    int size();

}
