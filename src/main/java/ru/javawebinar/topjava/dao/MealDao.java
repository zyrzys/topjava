package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealDao {
    public void add(Meal meal);

    public void delete(int id);

    public void update(Meal meal);
    public List<MealTo> getAll();

    public Meal get(int id);
}
