package ru.fav.petcaregroomingsalon.servlet.pet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.fav.petcaregroomingsalon.dao.BreedDAO;
import ru.fav.petcaregroomingsalon.dao.PetDAO;
import ru.fav.petcaregroomingsalon.entity.Breed;
import ru.fav.petcaregroomingsalon.entity.Client;
import ru.fav.petcaregroomingsalon.entity.Pet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

@WebServlet("/addPet")
public class AddPetServlet extends HttpServlet {
    private final PetDAO petDao = new PetDAO();
    private final BreedDAO breedDao = new BreedDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false);

        Client client = (Client) session.getAttribute("client");
        String name = request.getParameter("name");
        Date birthDate = Date.valueOf(request.getParameter("birthDate"));
        String species = request.getParameter("species");

        Breed breed = null;
        if ("собака".equals(species)) {
            int breedId = Integer.parseInt(request.getParameter("breedId"));
            try {
                breed = breedDao.findById(breedId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        Pet pet = new Pet(name, species, breed, birthDate, client);
        try {
            petDao.create(pet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect("clientProfile");
    }
}
