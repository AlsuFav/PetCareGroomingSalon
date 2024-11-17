package ru.fav.petcaregroomingsalon.servlet.appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.fav.petcaregroomingsalon.dao.AppointmentDAO;
import ru.fav.petcaregroomingsalon.entity.Appointment;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/appointmentDetails")
public class AppointmentDetailsServlet extends HttpServlet {
    private final AppointmentDAO appointmentDao = new AppointmentDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));

        try {
            Appointment appointment = appointmentDao.findById(appointmentId);

            if (appointment != null) {
                request.setAttribute("appointment", appointment);
                request.getRequestDispatcher("appointment/appointmentDetails.jsp").forward(request, response);
            } else {
                response.sendRedirect("clientProfile");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
