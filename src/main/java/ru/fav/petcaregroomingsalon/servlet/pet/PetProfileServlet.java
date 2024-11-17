package ru.fav.petcaregroomingsalon.servlet.pet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.fav.petcaregroomingsalon.dao.PetDAO;
import ru.fav.petcaregroomingsalon.entity.Pet;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/petProfile")
public class PetProfileServlet extends HttpServlet {
    private final PetDAO petDao = new PetDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int petId = Integer.parseInt(request.getParameter("petId"));

        try {
            Pet pet = petDao.findById(petId);
            request.setAttribute("pet", pet);
            request.getRequestDispatcher("pet/petProfile.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
