
package co.edu.utp.misiontic2022.carloseduardojovengarcia.reto5.model.dao;

import co.edu.utp.misiontic2022.carloseduardojovengarcia.reto5.model.vo.DeudasPorProyectoVo;
import co.edu.utp.misiontic2022.carloseduardojovengarcia.reto5.util.JDBCUtilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class DeudasPorProyectoDao {
    
    public List<DeudasPorProyectoVo> listar(Double limite) 
     throws SQLException{

        ArrayList<DeudasPorProyectoVo> respuesta = new ArrayList<DeudasPorProyectoVo>();
        Connection conn = JDBCUtilities.getConnection();
        PreparedStatement stmt = null;
        ResultSet rset = null;
        String consulta = "SELECT P.ID_PROYECTO AS ID, SUM(C.CANTIDAD * MC.PRECIO_UNIDAD) AS VALOR"
                + " FROM PROYECTO P"
                + " JOIN COMPRA C ON (P.ID_PROYECTO = C.ID_PROYECTO)"
                + " JOIN MATERIALCONSTRUCCION MC ON (C.ID_MATERIALCONSTRUCCION = MC.ID_MATERIALCONSTRUCCION)"
                + " WHERE C.PAGADO = 'No' "
                + " GROUP BY P.ID_PROYECTO"
                + " HAVING SUM(C.CANTIDAD * MC.PRECIO_UNIDAD) > ?"
                + " ORDER BY VALOR DESC";

        try {
            stmt = conn.prepareStatement(consulta);
            stmt.setDouble(1, limite);
            rset = stmt.executeQuery();
            while (rset.next()) {
                DeudasPorProyectoVo vo = new DeudasPorProyectoVo();
                vo.setId(rset.getInt("ID"));
                vo.setValor(rset.getDouble("VALOR"));
                respuesta.add(vo);
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

        return respuesta;

    }

    
}
