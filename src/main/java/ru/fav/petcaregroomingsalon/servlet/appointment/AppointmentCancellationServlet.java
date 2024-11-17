package ru.fav.petcaregroomingsalon.servlet.appointment;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.fav.petcaregroomingsalon.dao.AppointmentDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cancelAppointment")
public class AppointmentCancellationServlet extends HttpServlet {
    private final AppointmentDAO appointmentDao = new AppointmentDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

        try {
            appointmentDao.delete(appointmentId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("clientProfile");
    }
}
