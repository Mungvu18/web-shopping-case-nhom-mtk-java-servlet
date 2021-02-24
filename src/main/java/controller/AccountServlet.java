package controller;

import model.Account;
import model.Product;
import service.AccountService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AccountServlet", urlPatterns = "/account")
public class AccountServlet extends HttpServlet {
    public static Account account = null;


    ProductService productService = new ProductService();

    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "login":
                showLoginForm(request, response);
                break;
            case "newAdminAccount":
            case "newUserAccount":
                ShowFormCreateNewAccount(request, response);
                break;
            case "showAccountList":
                showAccountList(request, response);
                break;
            case "update":
                showUpdateForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "logout":
                logoutAccount(request, response);
                break;
            case "showManagerPage":
                showManagerPage(request,response,account);



        }
    }

    private void logoutAccount(HttpServletRequest request, HttpServletResponse response) {
        account = null;
        ProductServlet.account=account;

        try {

            response.sendRedirect("/account?action=login");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showLoginForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginForm.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Account account_delete = accountService.findById(id);
        request.setAttribute("account_delete", account_delete);
        request.setAttribute("account", account);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("deleteForm.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Account account_update = accountService.findById(id);
        request.setAttribute("account", account);
        request.setAttribute("account_update", account_update);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("updateForm.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showAccountList(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        List<Account> accountList = accountService.findAll();
        request.setAttribute("account", account);

        request.setAttribute("accountList", accountList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("accountList.jsp");
        requestDispatcher.forward(request, response);
    }

    private void ShowFormCreateNewAccount(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("createAccountForm.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        switch (action) {
            case "newAdminAccount":
            case "newUserAccount":
                createNewAccount(request, response);
                break;
            case "delete":
                deleteAccount(request, response);
                break;
            case "update":
                updateAccount(request, response);
                break;
            case "login":
                checkLogin(request, response);
                break;

        }
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int adminRole = 1;
        int userRole = 2;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        List<Account> accountList = accountService.findAll();
        for (Account a : accountList) {
            if (username.equals(a.getUsername()) && password.equals(a.getPassword()) && a.getRole() == adminRole) {

                showManagerPage(request, response, a);
                return;
            } else if (username.equals(a.getUsername()) && password.equals(a.getPassword()) && a.getRole() == userRole) {
                ProductServlet.account = account = a;
                request.setAttribute("account", account);
                List<Product> productList = productService.findAll();
                request.setAttribute("productList", productList);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("shop.jsp");
                requestDispatcher.forward(request, response);
                return;
            }

        }
        request.setAttribute("loginFails", true);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("loginForm.jsp");
        requestDispatcher.forward(request, response);

    }

    private void showManagerPage(HttpServletRequest request, HttpServletResponse response, Account a) throws ServletException, IOException {
        ProductServlet.account = account = a;
        request.setAttribute("account", account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("manager.jsp");
        requestDispatcher.forward(request, response);
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("role"));

        Account account = new Account(id, username, password, role);


        accountService.update(account);
        try {
            response.sendRedirect("/account?action=showAccountList");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        accountService.delete(id);
        try {
            response.sendRedirect("/account?action=showAccountList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int id = 0;
        String action = request.getParameter("action");
        if (action.equals("newAdminAccount")) {
            int role = 1;
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Account account = new Account(id, username, password, role);

            accountService.Create(account);

            response.sendRedirect("/account?action=showAccountList");

        }
        if (action.equals("newUserAccount")) {
            int role = 2;
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Account account = new Account(id, username, password, role);

            accountService.Create(account);

            response.sendRedirect("/product?action=show");
        }

    }

}


