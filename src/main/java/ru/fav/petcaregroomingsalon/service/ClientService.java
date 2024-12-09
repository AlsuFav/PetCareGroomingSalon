package ru.fav.petcaregroomingsalon.service;

import lombok.AllArgsConstructor;
import ru.fav.petcaregroomingsalon.dao.ClientDAO;
import ru.fav.petcaregroomingsalon.entity.Client;
import ru.fav.petcaregroomingsalon.exception.EmailAlreadyExistsException;

import java.security.MessageDigest;
import java.sql.SQLException;

@AllArgsConstructor
public class ClientService {
    private ClientDAO clientDAO;

    public Client login(String email, String password) throws SQLException {
        String hashedPassword = hashPassword(password);
        return clientDAO.findByEmail(email)
                .filter(client -> client.getPassword().equals(hashedPassword))
                .orElse(null);
    }

    public void register(Client client) throws SQLException {

        if (clientDAO.findByEmail(client.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Клиент с почтой " + client.getEmail() + " уже существует.");
        }

        String hashedPassword = hashPassword(client.getPassword());
        client.setPassword(hashedPassword);
        clientDAO.create(client);
    }

    public Client findById(int id) throws SQLException {
        return clientDAO.findById(id).orElse(null);
    }

    public Client findByEmail(String email) throws SQLException {
        return clientDAO.findByEmail(email).orElse(null);
    }

    public void delete(Client client) throws SQLException {
        int clientId = client.getId();
        clientDAO.delete(clientId);
    }

    public void update(Client client) throws SQLException {
        if (clientDAO.findByEmail(client.getEmail()).isPresent()
                && clientDAO.findByEmail(client.getEmail()).get().getId() != client.getId()) {
            throw new EmailAlreadyExistsException("Клиент с почтой " + client.getEmail() + " уже существует.");
        }

        clientDAO.update(client);
    }

    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception e) {
            throw new RuntimeException("MD5 hashing failed", e);
        }
    }
}
