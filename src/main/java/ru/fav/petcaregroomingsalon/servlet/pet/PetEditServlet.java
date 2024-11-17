package ru.fav.petcaregroomingsalon.servlet.pet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.fav.petcaregroomingsalon.dao.AppointmentDAO;
import ru.fav.petcaregroomingsalon.dao.BreedDAO;
import ru.fav.petcaregroomingsalon.dao.PetDAO;
import ru.fav.petcaregroomingsalon.dao.ServicePriceDAO;
import ru.fav.petcaregroomingsalon.entity.Pet;
import ru.fav.petcaregroomingsalon.service.AppointmentService;
import ru.fav.petcaregroomingsalon.service.BreedService;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Date;
import java.util.Objects;

@WebServlet("/editPet")
public class PetEditServlet extends HttpServlet {
    private final PetDAO petDao = new PetDAO();
    private final BreedDAO breedDao = new BreedDAO();
    private final BreedService breedService = new BreedService();
    private final AppointmentService appointmentService = new AppointmentService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int petId = Integer.parseInt(request.getParameter("petId"));

        try {
            Pet pet = petDao.findById(petId);
            request.setAttribute("pet", pet);

            if ("собака".equalsIgnoreCase(pet.getSpecies())) {
                request.setAttribute("breeds", breedDao.findAll());
                System.out.println(breedDao.findAll());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("pet/editPet.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int petId = Integer.parseInt(request.getParameter("petId"));
        String name = request.getParameter("name");
        Date birthDate = Date.valueOf(request.getParameter("birthDate"));

        try {
            Pet pet = petDao.findById(petId);
            pet.setName(name);
            pet.setBirthDate(birthDate);

            if (!Objects.equals(pet.getSpecies(), "кошка")) {
                int oldBreedId = pet.getBreed().getId();
                int newBreedId = Integer.parseInt(request.getParameter("breedId"));

                pet.setBreed(breedDao.findById(newBreedId));

                if (!breedService.ifEqualBreedSize(newBreedId, oldBreedId)) {
                    appointmentService.updateAppointmentPricesForPet(pet);
                }
            }

            petDao.update(pet);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("petProfile?petId=" + petId);
    }
}