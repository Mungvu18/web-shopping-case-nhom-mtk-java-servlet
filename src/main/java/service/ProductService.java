package service;

import javafx.scene.effect.SepiaTone;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService {
    @Override
    public void Create(Product product) {

    }

    @Override
    public Product findById(int id) {
        Product product = null;
        try {
            Connection connection = MyConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product where id=?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int id_category = resultSet.getInt("id_category");
                int id_account = resultSet.getInt("id_account");
                product = new Product(id, name, image, description, price, id_category, id_account);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return product;
    }

    @Override
    public List findAll() {
        List<Product> productList = new ArrayList<>();

        try {
            Connection connection = MyConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int id_category = resultSet.getInt("id_category");
                int id_account = resultSet.getInt("id_account");
                Product product = new Product(id, name, image, description, price, id_category, id_account);

                productList.add(product);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return productList;
    }

    @Override
    public void delete(int id) {

        try {
            Connection connection = MyConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from product where id = ?,");
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @Override
    public void update(Product product) {
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("update product set name=?,image=?,description=?,price = ?, id_category = ?, id_account = ? where id=?");
            ps.setString(1,product.getName());
            ps.setString(2,product.getImage());
            ps.setString(3,product.getDescription());
            ps.setDouble(4,product.getPrice());
            ps.setInt(5,product.getId_category());
            ps.setInt(6,product.getId_account());
            ps.setInt(7,product.getId());
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public List<Product> findByBrand(String brand) {
        List<Product> productList = new ArrayList<>();
        Connection connection = MyConnection.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product join category c on c.id = product.id_category where c.name = ?;");
            preparedStatement.setString(1, brand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String image = resultSet.getString("image");
                String description = resultSet.getString("description");
                double price = resultSet.getDouble("price");
                int id_category = resultSet.getInt("id_category");
                int id_account = resultSet.getInt("id_account");
                Product product = new Product(id, name, image, description, price, id_category, id_account);

                productList.add(product);

            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return productList;

    }
}
