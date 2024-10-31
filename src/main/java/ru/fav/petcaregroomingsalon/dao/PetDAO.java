package ru.fav.petcaregroomingsalon.dao;

import ru.fav.petcaregroomingsalon.entity.Pet;
import ru.fav.petcaregroomingsalon.util.DriverManagerDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {
    private final DriverManagerDataSource dataSource;
    private final ClientDAO clientDAO;
    private final BreedDAO breedDAO;

    public PetDAO() {
        this.dataSource = DriverManagerDataSource.getInstance();
        this.clientDAO = new ClientDAO();
        this.breedDAO = new BreedDAO();
    }

    public void create(Pet pet) throws SQLException {
        String sql = "INSERT INTO pet (name, species, breed_id, birth_date, owner_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getSpecies());
            statement.setInt(3, pet.getBreed().getId());
            statement.setDate(4, Date.valueOf(pet.getBirthDate().toLocalDate()));
            statement.setInt(5, pet.getOwner().getId());
            statement.executeUpdate();
        }
    }

    public Pet findById(int id) throws SQLException {
        String sql = "SELECT * FROM pet WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Pet(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("species"),
                        breedDAO.findById(resultSet.getInt("breed_id")),
                        resultSet.getDate("birth_date"),
                        clientDAO.findById(resultSet.getInt("owner_id"))
                );
            }
        }
        return null;
    }

    public List<Pet> findAllByOwnerId(int ownerId) throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet WHERE owner_id = ?";
        try (Connection connection = DriverManagerDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, ownerId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Pet pet = new Pet();
                pet.setId(resultSet.getInt("id"));
                pet.setName(resultSet.getString("name"));
                pet.setSpecies(resultSet.getString("species"));
                pet.setBreed(breedDAO.findById(resultSet.getInt("breed_id")));
                pet.setBirthDate(resultSet.getDate("birth_date"));
                pet.setOwner(clientDAO.findById(ownerId));
                pets.add(pet);
            }
        }
        return pets;
    }

    public List<Pet> findAll() throws SQLException {
        List<Pet> pets = new ArrayList<>();
        String sql = "SELECT * FROM pet";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                pets.add(new Pet(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("species"),
                        breedDAO.findById(resultSet.getInt("breed_id")),
                        resultSet.getDate("birth_date"),
                        clientDAO.findById(resultSet.getInt("owner_id"))
                ));
            }
        }
        return pets;
    }

    public void update(Pet pet) throws SQLException {
        String sql = "UPDATE pet SET name = ?, species = ?, breed_id = ?, birth_date = ?, owner_id = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, pet.getName());
            statement.setString(2, pet.getSpecies());
            statement.setInt(3, pet.getBreed().getId());
            statement.setDate(4, pet.getBirthDate());
            statement.setInt(5, pet.getOwner().getId());
            statement.setInt(6, pet.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM pet WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}