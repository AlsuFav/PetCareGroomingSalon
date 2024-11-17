package ru.fav.petcaregroomingsalon.servlet.pet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.fav.petcaregroomingsalon.dao.AppointmentDAO;
import ru.fav.petcaregroomingsalon.dao.PetDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deletePet")
public class PetDeletionServlet extends HttpServlet {
    private final PetDAO petDao = new PetDAO();
    private final AppointmentDAO appointmentDao = new AppointmentDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int petId = Integer.parseInt(request.getParameter("petId"));

        try {
            if (!appointmentDao.findUpcomingByPetId(petId).isEmpty()) {
                response.sendRedirect("petProfile?petId=" + petId + "&error=cancel_appointments_first");
                return;
            }
            petDao.delete(petId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("clientProfile");
    }
}
