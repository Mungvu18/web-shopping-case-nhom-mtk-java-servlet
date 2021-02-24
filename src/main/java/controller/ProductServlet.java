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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/product")
public class ProductServlet extends HttpServlet {
    ProductService productService = new ProductService();
    public static   Account account = null;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) {
            action = "product";
        }

        switch (action) {
            case "show":
                showAllProduct(request, response);
                break;
            case "search":
                finByName(request, response);
                break;
            case "sortByPriceLowToHigh":
                sortByPriceLowToHigh(request, response);
                break;
            case "sortByPriceHighToLow":
                sortByPriceHighToLow(request,response);
                break;
            case "findByBrand":
                findByBrand(request,response);
                break;

        }
    }

    private void findByBrand(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("account", account);
        String brand = request.getParameter("brand");
        List<Product> productList = productService.findByBrand(brand);

        request.setAttribute("productList",productList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("shop.jsp");
        try {
            requestDispatcher.forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sortByPriceHighToLow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = productService.findAll();
        request.setAttribute("account", account);
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() > o2.getPrice()) {
                    return -1;
                } else if (o1.getPrice() < o2.getPrice()) {
                    return 1;

                } else {return 0;}
            }});
        request.setAttribute("productList",productList);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/shop.jsp");
        requestDispatcher.forward(request,response);


    }

    private void sortByPriceLowToHigh(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("account", account);
        List<Product> productList = productService.findAll();
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                if (o1.getPrice() > o2.getPrice()) {
                    return 1;
                } else if (o1.getPrice() < o2.getPrice()) {
                    return -1;

                } else {return 0;}
            }});

        request.setAttribute("productList",productList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/shop.jsp");
        requestDispatcher.forward(request,response);

        }

        private void finByName (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            request.setAttribute("account", account);
            String name = request.getParameter("name");
            List<Product> AllProduct = productService.findAll();
            List<Product> productList = new ArrayList<>();

            for (Product product : AllProduct) {

                if (product.getName().contains(name)) {
                    productList.add(product);
                }

            }
            request.setAttribute("productList", productList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("shop.jsp");
            requestDispatcher.forward(request, response);

        }


        private void showAllProduct (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            request.setAttribute("account", account);
            List<Product> productList = productService.findAll();
            request.setAttribute("productList", productList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/shop.jsp");
            requestDispatcher.forward(request, response);

        }

        @Override
        protected void doPost (HttpServletRequest request, HttpServletResponse response) throws
        ServletException, IOException {
            String action = request.getParameter("action");
            if (action == null) {
                action = "";
            }

            switch (action) {

            }
        }


    }

