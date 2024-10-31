package ru.fav.petcaregroomingsalon.dao;

import ru.fav.petcaregroomingsalon.entity.BreedTypeEnum;
import ru.fav.petcaregroomingsalon.entity.Pet;
import ru.fav.petcaregroomingsalon.entity.ServicePrice;
import ru.fav.petcaregroomingsalon.util.DriverManagerDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ServicePriceDAO {
    private DriverManagerDataSource dataSource;
    private final PetDAO petDAO;
    private final ServiceDAO serviceDAO;

    public ServicePriceDAO() {
        this.dataSource = DriverManagerDataSource.getInstance();
        petDAO = new PetDAO();
        serviceDAO = new ServiceDAO();
    }

    public void create(ServicePrice servicePrice) throws SQLException {
        String sql = "INSERT INTO service_price (service_id, species, breed_type, price) VALUES (?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, servicePrice.getService().getId());
            statement.setString(2, servicePrice.getSpecies());
            statement.setString(3, servicePrice.getBreedType().name());
            statement.setInt(4, servicePrice.getPrice());
            statement.executeUpdate();
        }
    }

    public ServicePrice findById(int id) throws SQLException {
        String sql = "SELECT * FROM service_price WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new ServicePrice(
                        resultSet.getInt("id"),
                        new ServiceDAO().findById(resultSet.getInt("service_id")),
                        resultSet.getString("species"),
                        BreedTypeEnum.valueOf(resultSet.getString("breed_type")),
                        resultSet.getInt("price")
                );
            }
        }
        return null;
    }

    public List<ServicePrice> findAll() throws SQLException {
        List<ServicePrice> servicePrices = new ArrayList<>();
        String sql = "SELECT * FROM service_price";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                servicePrices.add(new ServicePrice(
                        resultSet.getInt("id"),
                        new ServiceDAO().findById(resultSet.getInt("service_id")),
                        resultSet.getString("species"),
                        BreedTypeEnum.valueOf(resultSet.getString("breed_type")),
                        resultSet.getInt("price")
                ));
            }
        }
        return servicePrices;
    }

    public void update(ServicePrice servicePrice) throws SQLException {
        String sql = "UPDATE service_price SET service_id = ?, species = ?, breed_type = ?, price = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, servicePrice.getService().getId());
            statement.setString(2, servicePrice.getSpecies());
            statement.setString(3, servicePrice.getBreedType().name());
            statement.setInt(4, servicePrice.getPrice());
            statement.setInt(5, servicePrice.getId());
            statement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM service_price WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    public int findServicePriceForPetAndService(int petId, int serviceId) throws SQLException {
        Pet pet = petDAO.findById(petId);
        String species = pet.getSpecies();
        String breedType;

        if ("собака".equalsIgnoreCase(species) && pet.getBreed() != null) {
            breedType = pet.getBreed().getBreedType().name();
        } else if ("кошка".equalsIgnoreCase(species)) {
            breedType = null;
        } else {
            throw new IllegalArgumentException("Unsupported pet species: " + species);
        }

        String sql = """
            SELECT *
            FROM service_price
            WHERE service_id = ?
            AND species = ?
            AND (breed_type = ?::breed_type_enum OR breed_type IS NULL)
        """;

        try (Connection connection = DriverManagerDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, serviceId);
            statement.setString(2, species);
            statement.setString(3, breedType);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("price");
            }
        }
        return -1;
    }
}