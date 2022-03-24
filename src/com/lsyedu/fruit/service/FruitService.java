package com.lsyedu.fruit.service;

import com.lsyedu.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author lsy
 * @version 1.0
 */
public interface FruitService {
    List<Fruit> getFruitList(String keyword, Integer pageNo);
    void addFruit(Fruit fruit);
    Fruit getFruitByFid(Integer fid);
    void delFruit(Integer fid);
    Integer getPageCount(String keyword);
    void updateFruit(Fruit fruit);
}
