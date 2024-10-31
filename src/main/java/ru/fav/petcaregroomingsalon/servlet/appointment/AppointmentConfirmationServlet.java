package ru.fav.petcaregroomingsalon.servlet.appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.fav.petcaregroomingsalon.dao.AppointmentDAO;
import ru.fav.petcaregroomingsalon.dao.ServicePriceDAO;
import ru.fav.petcaregroomingsalon.dao.TimeSlotDAO;
import ru.fav.petcaregroomingsalon.entity.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/confirmAppointment")
public class AppointmentConfirmationServlet extends HttpServlet {
    private final AppointmentDAO appointmentDao = new AppointmentDAO();
    private final ServicePriceDAO servicePriceDao = new ServicePriceDAO();
    private final TimeSlotDAO timeSlotDao = new TimeSlotDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("client") != null) {
            Pet pet = (Pet) request.getSession().getAttribute("selectedPet");
            Service service = (Service) request.getSession().getAttribute("selectedService");

            try {
                int price = servicePriceDao.findServicePriceForPetAndService(pet.getId(), service.getId());
                request.setAttribute("price", price);
                request.getRequestDispatcher("appointment/confirmAppointment.jsp").forward(request, response);

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            response.sendRedirect("login.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Pet pet = (Pet) request.getSession().getAttribute("selectedPet");
        Service service = (Service) request.getSession().getAttribute("selectedService");
        TimeSlot timeSlot = (TimeSlot) request.getSession().getAttribute("selectedTimeSlot");
        int price = Integer.parseInt(request.getParameter("price"));

        Appointment appointment = new Appointment();
        appointment.setPet(pet);
        appointment.setGroomer(timeSlot.getGroomer());
        appointment.setService(service);
        appointment.setPrice(price);
        appointment.setDate(timeSlot.getStartTime());

        try {
            appointmentDao.create(appointment);
            timeSlotDao.setTaken(timeSlot.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("clientProfile");
    }
}