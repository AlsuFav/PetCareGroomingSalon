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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final ClientDAO clientDao = new ClientDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // Проверка на пустые поля
        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            response.sendRedirect("login.jsp?error=empty");
            return;
        }

        // Попытка найти клиента по email и паролю
        Client client = clientDao.findByEmailAndPassword(email, password);

        if (client != null) {
            HttpSession session = request.getSession();
            session.setAttribute("client", client); // Установка атрибута "client" в сессии
            response.sendRedirect("clientProfile");
        } else {
            response.sendRedirect("login.jsp?error=invalid");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("login.jsp");
    }
}