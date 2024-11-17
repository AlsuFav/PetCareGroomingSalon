package ru.fav.petcaregroomingsalon.service;

import ru.fav.petcaregroomingsalon.dao.AppointmentDAO;
import ru.fav.petcaregroomingsalon.dao.ServicePriceDAO;
import ru.fav.petcaregroomingsalon.entity.Appointment;
import ru.fav.petcaregroomingsalon.entity.Pet;
import ru.fav.petcaregroomingsalon.entity.ServicePrice;

import java.sql.SQLException;

public class AppointmentService {
    AppointmentDAO appointmentDAO;
    ServicePriceDAO servicePriceDAO;

    public AppointmentService() {
        appointmentDAO = new AppointmentDAO();
        servicePriceDAO = new ServicePriceDAO();
    }

    public void updateAppointmentPricesForPet(Pet pet) throws SQLException {
        for (Appointment appointment : appointmentDAO.findUpcomingByPetId(pet.getId())) {
            int newPrice = servicePriceDAO.findServicePriceForPetAndService(pet.getId(), appointment.getService().getId());
            appointment.setPrice(newPrice);
            appointmentDAO.update(appointment);
        }
    }
}
