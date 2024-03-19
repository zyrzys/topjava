package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MockMealDBImplementation;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private  MealDao dao;

    public MealServlet() {
        super();
        dao = new MockMealDBImplementation();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("forward to meals");
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.delete(mealId);
            request.setAttribute("mealList", dao.getAll());
            request.getRequestDispatcher("meals?action=mealslist").forward(request, response);
        } else if (action.equalsIgnoreCase("edit")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.get(mealId);
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        } else if (action.equalsIgnoreCase("mealslist")) {
            List<MealTo> mealTos = dao.getAll();
            request.setAttribute("mealList", mealTos);
            request.getRequestDispatcher("meals.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("mealEdit.jsp").forward(request, response);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String description = req.getParameter("description");
        int calories = Integer.parseInt(req.getParameter("calories"));
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String mealId = req.getParameter("mealId");
        Meal meal = new Meal(dateTime, description, calories);
        if (mealId == null || mealId.isEmpty()){
            dao.add(meal);
        } else {
            meal.setId(Integer.parseInt(mealId));
            dao.update(meal);
        }
        List<MealTo> mealTos = dao.getAll();
        req.setAttribute("mealList", mealTos);
        req.getRequestDispatcher("/meals.jsp").forward(req, resp);
    }
}