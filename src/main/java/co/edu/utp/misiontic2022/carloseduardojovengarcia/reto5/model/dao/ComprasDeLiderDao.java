
package co.edu.utp.misiontic2022.carloseduardojovengarcia.reto5.model.dao;

import co.edu.utp.misiontic2022.carloseduardojovengarcia.reto5.model.vo.ComprasDeLiderVo;
import co.edu.utp.misiontic2022.carloseduardojovengarcia.reto5.util.JDBCUtilities;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author carlos
 */
public class ComprasDeLiderDao {
    
       public List<ComprasDeLiderVo> listar() throws SQLException {
            ArrayList<ComprasDeLiderVo> respuesta = new ArrayList<ComprasDeLiderVo>();
            Connection conn =JDBCUtilities.getConnection();

            Statement stmt = null;
            ResultSet rset = null;
            String consulta = "SELECT L.NOMBRE ||' '||L.PRIMER_APELLIDO ||' '||L.SEGUNDO_APELLIDO AS LIDER,"
                   + "SUM(C.CANTIDAD * MC.PRECIO_UNIDAD) AS VALOR"
                   + " FROM LIDER L"
                   + " JOIN PROYECTO P ON (P.ID_LIDER = L.ID_LIDER)"
                   + " JOIN COMPRA C ON (P.ID_PROYECTO = C.ID_PROYECTO)"
                   + " JOIN MATERIALCONSTRUCCION MC ON (C.ID_MATERIALCONSTRUCCION = MC.ID_MATERIALCONSTRUCCION)"
                   + " GROUP BY L.NOMBRE ||' '||L.PRIMER_APELLIDO ||' '||L.SEGUNDO_APELLIDO"
                   + " ORDER BY VALOR DESC"
                   + " LIMIT 10;";
            try{
                stmt = conn.createStatement();
                rset = stmt.executeQuery(consulta);
                while(rset.next()){
                    ComprasDeLiderVo vo = new ComprasDeLiderVo();
                    vo.setLider(rset.getString("lider"));
                    vo.setValor(rset.getDouble("valor"));
                    respuesta.add(vo);

                }
            }finally{
                if (rset != null){
                    rset.close();
                }
                if (stmt != null){
                    stmt.close();
                }
                if (conn != null){
                    stmt.close();
                }
            }
        return respuesta;

    }
    
}
