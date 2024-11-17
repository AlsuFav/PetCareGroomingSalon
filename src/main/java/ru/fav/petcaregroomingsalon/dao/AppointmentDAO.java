package ru.fav.petcaregroomingsalon.dao;



import ru.fav.petcaregroomingsalon.entity.Appointment;
import ru.fav.petcaregroomingsalon.entity.TimeSlot;
import ru.fav.petcaregroomingsalon.util.DriverManagerDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    private final DriverManagerDataSource dataSource;
    private final ServiceDAO serviceDAO;
    private final TimeSlotDAO timeSlotDAO;

    public AppointmentDAO() {
        this.dataSource = DriverManagerDataSource.getInstance();
        this.serviceDAO = new ServiceDAO();
        this.timeSlotDAO = new TimeSlotDAO();
    }

    // Создание новой записи
    public void create(Appointment appointment) throws SQLException {
        String sql = "INSERT INTO appointment (pet_id, groomer_id, service_id, price, date, notes) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointment.getPet().getId());
            statement.setInt(2, appointment.getGroomer().getId());
            statement.setInt(3, appointment.getService().getId());
            statement.setInt(4, appointment.getPrice());
            statement.setTimestamp(5, appointment.getDate());
            statement.setString(6, appointment.getNotes());
            statement.executeUpdate();
        }
    }

    // Получение записи по идентификатору
    public Appointment findById(int id) throws SQLException {
        Appointment appointment = null;
        String sql = "SELECT * FROM appointment WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                appointment = new Appointment();
                appointment.setId(resultSet.getInt("id"));
                appointment.setPet(new PetDAO().findById(resultSet.getInt("pet_id")));
                appointment.setGroomer(new GroomerDAO().findById(resultSet.getInt("groomer_id")));
                appointment.setService(serviceDAO.findById(resultSet.getInt("service_id")));
                appointment.setPrice(resultSet.getInt("price"));
                appointment.setDate(resultSet.getTimestamp("date"));
                appointment.setNotes(resultSet.getString("notes"));
            }
        }
        return appointment;
    }

    // Получение всех записей
    public List<Appointment> findAll() throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt("id"));
                appointment.setPet(new PetDAO().findById(resultSet.getInt("pet_id")));
                appointment.setGroomer(new GroomerDAO().findById(resultSet.getInt("groomer_id")));
                appointment.setService(serviceDAO.findById(resultSet.getInt("service_id")));
                appointment.setPrice(resultSet.getInt("price"));
                appointment.setDate(resultSet.getTimestamp("date"));
                appointment.setNotes(resultSet.getString("notes"));
                appointments.add(appointment);
            }
        }
        return appointments;
    }


    public List<Appointment> findUpcomingByClientId(int clientId) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT a.* FROM appointment a " +
                "JOIN pet p ON a.pet_id = p.id " +
                "WHERE p.owner_id = ? AND a.date > NOW()";
        try (Connection connection = DriverManagerDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt("id"));
                appointment.setPet(new PetDAO().findById(resultSet.getInt("pet_id")));
                appointment.setGroomer(new GroomerDAO().findById(resultSet.getInt("groomer_id")));
                appointment.setService(serviceDAO.findById(resultSet.getInt("service_id")));
                appointment.setPrice(resultSet.getInt("price"));
                appointment.setDate(resultSet.getTimestamp("date"));
                appointment.setNotes(resultSet.getString("notes"));
                appointments.add(appointment);
            }
        }
        return appointments;
    }

    public List<Appointment> findUpcomingByPetId(int petId) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM appointment WHERE pet_id = ? AND date > NOW()";
        try (Connection connection = DriverManagerDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, petId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setId(resultSet.getInt("id"));
                appointment.setPet(new PetDAO().findById(petId));
                appointment.setGroomer(new GroomerDAO().findById(resultSet.getInt("groomer_id")));
                appointment.setService(serviceDAO.findById(resultSet.getInt("service_id")));
                appointment.setPrice(resultSet.getInt("price"));
                appointment.setDate(resultSet.getTimestamp("date"));
                appointment.setNotes(resultSet.getString("notes"));
                appointments.add(appointment);
            }
        }
        return appointments;
    }


    public void update(Appointment appointment) throws SQLException {
        String sql = "UPDATE appointment SET pet_id = ?, groomer_id = ?, service_id = ?, price = ?, date = ?, notes = ? WHERE id = ?";
        try (Connection connection = DriverManagerDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, appointment.getPet().getId());
            statement.setInt(2, appointment.getGroomer().getId());
            statement.setInt(3, appointment.getService().getId());
            statement.setInt(4, appointment.getPrice());
            statement.setTimestamp(5, appointment.getDate());
            statement.setString(6, appointment.getNotes());
            statement.setInt(7, appointment.getId());
            statement.executeUpdate();
        }
    }


    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM appointment WHERE id = ?";

        Appointment appointment = findById(id);
        if (appointment != null) {
            TimeSlot timeSlot = new TimeSlotDAO().findByStartTimeAndGroomerId(appointment.getDate(), appointment.getGroomer().getId());

            if (timeSlot != null) {
                timeSlotDAO.setEmpty(timeSlot.getId());
            }
        }

        try (Connection connection = DriverManagerDataSource.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }
}