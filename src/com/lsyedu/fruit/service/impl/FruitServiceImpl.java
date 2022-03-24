package com.lsyedu.fruit.service.impl;

import com.lsyedu.fruit.service.FruitService;
import com.lsyedu.fruit.dao.FruitDAO;
import com.lsyedu.fruit.pojo.Fruit;

import java.util.List;

/**
 * @author lsy
 * @version 1.0
 */
public class FruitServiceImpl implements FruitService {
    private FruitDAO fruitDAO = null;
    @Override
    public List<Fruit> getFruitList(String keyword, Integer pageNo) {
        return fruitDAO.getFruitList(keyword, pageNo);
    }

    @Override
    public void addFruit(Fruit fruit) {
        fruitDAO.addFruit(fruit);
    }

    @Override
    public Fruit getFruitByFid(Integer fid) {
        return fruitDAO.getFruitByFid(fid);
    }

    @Override
    public void delFruit(Integer fid) {
        fruitDAO.delFruit(fid);
    }

    @Override
    public Integer getPageCount(String keyword) {
        int fruitCount = fruitDAO.getFruitCount(keyword);
        int pageCount = (fruitCount + 4) / 5;
        return pageCount;
    }

    @Override
    public void updateFruit(Fruit fruit) {
        fruitDAO.updateFruit(fruit);
    }
}
