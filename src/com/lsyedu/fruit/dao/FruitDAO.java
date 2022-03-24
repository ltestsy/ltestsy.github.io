package com.lsyedu.fruit.dao;

import com.lsyedu.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author lsy
 * @version 1.0
 */
public interface FruitDAO {
    List<Fruit> getFruitList(String keyword, Integer pageNo);

    Fruit getFruitByFid(Integer fid);

    void updateFruit(Fruit fruit);

    void delFruit(Integer fid);

    void addFruit(Fruit fruit);

    int getFruitCount(String keyword);
}
