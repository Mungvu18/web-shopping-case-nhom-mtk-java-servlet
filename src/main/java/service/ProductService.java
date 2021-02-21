package service;

import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService{
    @Override
    public void Create(Product product) {

    }

    @Override
    public Product findById(int id) {
        return null;
    }

    @Override
    public List findAll() {
        List<Product> productList = new ArrayList<>();

        try {
            Connection connection = MyConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int id_category = resultSet.getInt("id_category");
                int id_account = resultSet.getInt("id_account");
                Product product = new Product(id,name,image,description,price,id_category,id_account);

                productList.add(product);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productList;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void update(Product product) {

    }
}
