
package co.edu.utp.misiontic2022.carloseduardojovengarcia.reto5.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author carlos
 */
public class JDBCUtilities {
    
      private static final String UBICACION_BD ="ProyectosConstruccion.db";
    public static Connection getConnection() throws SQLException{
    String url = "jdbc:sqlite:" + UBICACION_BD;
    return DriverManager.getConnection(url);    
      

    }
    
}
