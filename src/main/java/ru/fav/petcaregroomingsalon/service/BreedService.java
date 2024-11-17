package ru.fav.petcaregroomingsalon.service;

import ru.fav.petcaregroomingsalon.dao.BreedDAO;

import java.sql.SQLException;

public class BreedService {
    BreedDAO breedDAO;

    public BreedService() {
        breedDAO = new BreedDAO();
    }

    public boolean ifEqualBreedSize(int breedId1, int breedId2) throws SQLException {
        return breedDAO.findById(breedId1).getBreedType().equals(breedDAO.findById(breedId2).getBreedType());
    }
}
