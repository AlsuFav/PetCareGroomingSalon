package ru.fav.petcaregroomingsalon.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ru.fav.petcaregroomingsalon.dao.*;
import ru.fav.petcaregroomingsalon.service.*;
import ru.fav.petcaregroomingsalon.config.CustomDataSource;

import javax.sql.DataSource;


@WebListener
public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        DataSource dataSource = CustomDataSource.getInstance();

        ServiceDAO serviceDAO = new ServiceDAO(dataSource);
        GroomerDAO groomerDAO = new GroomerDAO(dataSource);
        BreedDAO breedDAO = new BreedDAO(dataSource);
        ClientDAO clientDAO = new ClientDAO(dataSource);
        TimeSlotDAO timeSlotDAO = new TimeSlotDAO(dataSource, groomerDAO);
        PetDAO petDAO = new PetDAO(dataSource, clientDAO, breedDAO);
        AppointmentDAO appointmentDAO = new AppointmentDAO(dataSource, serviceDAO, timeSlotDAO, petDAO, groomerDAO);
        ServicePriceDAO servicePriceDAO = new ServicePriceDAO(dataSource, petDAO, serviceDAO);

        AppointmentService appointmentService = new AppointmentService(appointmentDAO, servicePriceDAO);
        ServicePriceService servicePriceService = new ServicePriceService(servicePriceDAO);
        TimeSlotService timeSlotService = new TimeSlotService(timeSlotDAO);
        PetService petService = new PetService(petDAO);
        ServiceService serviceService = new ServiceService(serviceDAO);
        ClientService clientService = new ClientService(clientDAO);
        BreedService breedService = new BreedService(breedDAO);

        ServletContext servletContext = sce.getServletContext();

        servletContext.setAttribute("appointmentService", appointmentService);
        servletContext.setAttribute("servicePriceService", servicePriceService);
        servletContext.setAttribute("timeSlotService", timeSlotService);
        servletContext.setAttribute("petService", petService);
        servletContext.setAttribute("serviceService", serviceService);
        servletContext.setAttribute("clientService", clientService);
        servletContext.setAttribute("breedService", breedService);
    }

}
