package controller;


import model.Account;
import model.Product;
import service.AccountService;
import service.ProductService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/home")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    AccountService accountService = new AccountService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
        }

        switch (action) {
            case "show":
                showAllProduct(request, response);
                break;
            case "home":
                showHome(request, response);
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
            case "search":
                finByName(request,response);
                break;

        }
    }

    private void finByName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        List<Product> AllProduct = productService.findAll();
        List<Product> productList = new ArrayList<>();

        for( Product product : AllProduct){

            if (product.getName().contains(name)){
                productList.add(product);
            }

        }
        request.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("shop.jsp");
        requestDispatcher.forward(request,response);

    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        Account account = accountService.findById(id);
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
        Account account = accountService.findById(id);
        request.setAttribute("account", account);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("updateForm.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showAccountList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Account> accountList = accountService.findAll();
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


    private void showHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");
        requestDispatcher.forward(request, response);
    }

    private void showAllProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        request.setAttribute("productList", productList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/shop.jsp");
        requestDispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "home";
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

        }
    }

    private void updateAccount(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        int role = Integer.parseInt(request.getParameter("role"));

        Account account = new Account(id, username, password, role);


        accountService.update(account);
        try {
            response.sendRedirect("/home?action=showAccountList");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void deleteAccount(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        accountService.delete(id);
        try {
            response.sendRedirect("/home?action=showAccountList");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNewAccount(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int id = 0;
        String action = request.getParameter("action");
        if (action.equals("newAdminAccount")) {
            int role = 1;
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Account account = new Account(id, username, password, role);

            accountService.Create(account);

            response.sendRedirect("/home?action=showAccountList");

        } if (action.equals("newUserAccount")){
            int role = 2;
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            Account account = new Account(id, username, password, role);

            accountService.Create(account);

            response.sendRedirect("/home?action=showAccountList");

        }

    }

}

