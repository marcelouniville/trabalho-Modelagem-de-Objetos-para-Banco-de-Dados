package dao;

import java.sql.Connection;
import java.sql.SQLException;

class BaseDAO {

    public Connection con() throws SQLException {
        return ConexaoDB.getInstance().getConnection();
    }

}
