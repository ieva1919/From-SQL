package sample;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryUtils {

    public static boolean isTableExist(Connection connection, String tablename) {
        if(connection != null && tablename != null) {
            Statement statemant  = null;
            try {
                statemant = connection.createStatement();
                statemant.executeQuery ("SELECT * FROM " + tablename);
            } catch (SQLException e) {
                return false;
            }
            return true;
        }
        return false;
    }
}
