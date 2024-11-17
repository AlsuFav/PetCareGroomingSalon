package ru.fav.petcaregroomingsalon.servlet.pet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.fav.petcaregroomingsalon.dao.BreedDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/addPetInfo")
public class PetInfoAdditionServlet extends HttpServlet {
    private final BreedDAO breedDao = new BreedDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        String species = (String) request.getSession().getAttribute("species");

        if ("собака".equals(species)) {
            try {
                request.setAttribute("breeds", breedDao.findAll());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        request.getRequestDispatcher("pet/addPetInfo.jsp").forward(request, response);

    }
}
