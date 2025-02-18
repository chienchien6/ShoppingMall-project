package com.chien.springbootmall.RowMapper;

import com.chien.springbootmall.constant.ProductCategory;
import com.chien.springbootmall.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product>{

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        Product product = new Product();

        product.setProductId(rs.getInt("product_id"));
        product.setProductName(rs.getString("product_name"));

        //String轉Enum的方法
//        String categoryStr = rs.getString("category");
//        ProductCategory categoryEnum = ProductCategory.valueOf(categoryStr);
//        product.setCategory(categoryEnum);
        product.setCategory(ProductCategory.valueOf(rs.getString("category")));


        product.setImageUrl(rs.getString("image_url"));
        product.setPrice(rs.getInt("price"));
        product.setStock(rs.getInt("stock"));
        product.setDescription(rs.getString("description"));
        product.setCreateDate(rs.getDate("created_date"));
        product.setLastModifiedDate(rs.getDate("last_modified_date"));

        return product;
    }
}
