package data.utils;

public interface ProductsQueries {
    public final static String CLEAR_TABLE_QUERY = "DELETE FROM GOODS";
    public final static String CREATE_TABLE_QUERY = "CREATE TABLE GOODS (id INTEGER not null AUTO_INCREMENT, prodid VARCHAR(255), title VARCHAR(255), cost DOUBLE, PRIMARY KEY ( id ))";
    public final static String INSERT_PRODUCT_QUERY = "INSERT INTO GOODS VALUES (0,?,?,?)";
    public final static String DELETE_PRODUCT_QUERY = "DELETE FROM GOODS WHERE title = (?)";
    public final static String GET_PRICE_QUERY = "SELECT cost FROM GOODS WHERE title = (?)";
    public final static String GET_PRODUCTS_QUERY = "SELECT * FROM GOODS";
    public final static String GET_PRODUCTS_BY_PRICE_QUERY = "SELECT * FROM GOODS WHERE cost >= (?) AND cost <= (?)";
    public final static String CHANGE_PRICE_QUERY = "UPDATE GOODS SET cost = (?) WHERE title = (?)";
}
