package ru.fav.petcaregroomingsalon.servlet.appointment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.fav.petcaregroomingsalon.dao.PetDAO;
import ru.fav.petcaregroomingsalon.dao.ServiceDAO;
import ru.fav.petcaregroomingsalon.entity.Client;
import ru.fav.petcaregroomingsalon.entity.Pet;
import ru.fav.petcaregroomingsalon.entity.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/selectAppointmentOptions")
public class AppointmentOptionsSelectionServlet extends HttpServlet {
    private final PetDAO petDao = new PetDAO();
    private final ServiceDAO serviceDao = new ServiceDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("client") != null) {
        Client client = (Client) session.getAttribute("client");

        List<Pet> pets;
        List<Service> services;
        try {
            pets = petDao.findAllByOwnerId(client.getId());
            services = serviceDao.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("pets", pets);
        request.setAttribute("services", services);
        request.getRequestDispatcher("appointment/selectAppointmentOptions.jsp").forward(request, response);

        } else {
            response.sendRedirect("login.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            Pet pet = petDao.findById(Integer.parseInt(request.getParameter("petId")));
            Service service = serviceDao.findById(Integer.parseInt(request.getParameter("serviceId")));

            request.getSession().setAttribute("selectedPet", pet);
            request.getSession().setAttribute("selectedService", service);

            response.sendRedirect("selectTimeSlot");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}