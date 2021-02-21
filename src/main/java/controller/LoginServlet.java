package controller;

import model.Account;
import service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    LoginService loginService = new LoginService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "":
                login(request, response);
                break;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        Account account = loginService.getAccount(username, password);
        System.out.println(account);
        if (account == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            if (account.getRole() == 1) {
                request.getRequestDispatcher("admin.jsp").forward(request, response);
            }else if(account.getRole() == 2){
                request.getRequestDispatcher("managerProduct.jsp").forward(request,response);
            }
            else {
                request.getRequestDispatcher("/home").forward(request, response);
            }
        }
    }
}
