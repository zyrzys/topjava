package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MockMealDBImplementation implements MealDao{
    private static AtomicInteger mealId = new AtomicInteger(0);

    public static Map<Integer, Meal> mealsDB = new ConcurrentHashMap<>();
    static {
        for (Meal meal : MealsUtil.meals) {
            mealsDB.put(mealId.get(), new Meal(mealId.getAndIncrement(), meal));
        }
    }

    @Override
    public void add(Meal meal) {
        mealsDB.putIfAbsent(mealId.getAndIncrement(), meal);
    }

    @Override
    public void delete(int id) {
        mealsDB.remove(id);
    }

    @Override
    public void update(Meal meal) {
        mealsDB.put(meal.getId(), meal);
    }

    @Override
    public List<MealTo> getAll() {
        return MealsUtil.filteredByStreams(new ArrayList<>(mealsDB.values()), LocalTime.MIN, LocalTime.MAX, MealsUtil.caloriesPerDay);
    }

    @Override
    public Meal get(int id) {
        return mealsDB.get(id);
    }
}
