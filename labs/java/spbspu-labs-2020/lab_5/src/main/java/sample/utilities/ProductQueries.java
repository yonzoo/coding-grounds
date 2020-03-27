package sample.utilities;

public class ProductQueries {
    private ProductQueries() {
        //restrict instantiation
    }

    public static final String CLEAR_TABLE_QUERY = "DELETE FROM GOODS";
    public static final String CREATE_TABLE_QUERY = "CREATE TABLE GOODS (id INTEGER not null AUTO_INCREMENT, prodid VARCHAR(255), title VARCHAR(255) UNIQUE, cost DOUBLE, PRIMARY KEY ( id ))";
    public static final String INSERT_PRODUCT_QUERY = "INSERT INTO GOODS VALUES (0,?,?,?)";
    public static final String DELETE_PRODUCT_QUERY = "DELETE FROM GOODS WHERE title = (?)";
    public static final String GET_PRICE_QUERY = "SELECT * FROM GOODS WHERE title = (?)";
    public static final String GET_PRODUCTS_QUERY = "SELECT * FROM GOODS";
    public static final String GET_PRODUCTS_BY_PRICE_QUERY = "SELECT * FROM GOODS WHERE cost >= (?) AND cost <= (?)";
    public static final String CHANGE_PRICE_QUERY = "UPDATE GOODS SET cost = (?) WHERE title = (?)";
}
