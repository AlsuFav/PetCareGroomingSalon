package ru.fav.petcaregroomingsalon.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.fav.petcaregroomingsalon.dao.ClientDAO;
import ru.fav.petcaregroomingsalon.entity.Client;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private final ClientDAO clientDao = new ClientDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");

        // Проверка на пустые поля и ошибки
        if (email == null || email.isEmpty()) {
            request.setAttribute("error", "Email не может быть пустым.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }
        if (password == null || password.isEmpty()) {
            request.setAttribute("error", "Пароль не может быть пустым.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }
        if (!password.equals(confirmPassword)) {
            request.setAttribute("error", "Пароли не совпадают.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }
        if (firstName == null || firstName.isEmpty()) {
            request.setAttribute("error", "Имя не может быть пустым.");
            request.getRequestDispatcher("registration.jsp").forward(request, response);
            return;
        }

        try {
            if (clientDao.findByEmail(email) != null) {
                request.setAttribute("error", "Пользователь с таким email уже существует.");
                request.getRequestDispatcher("registration.jsp").forward(request, response);
                return;
            }

            Client client = new Client(firstName, lastName, email, phone, password); // Пароль желательно хэшировать
            clientDao.create(client);

            client = clientDao.findByEmail(email);

            HttpSession session = request.getSession();
            session.setAttribute("client", client);
            response.sendRedirect("clientProfile");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("registration.jsp");
    }
}