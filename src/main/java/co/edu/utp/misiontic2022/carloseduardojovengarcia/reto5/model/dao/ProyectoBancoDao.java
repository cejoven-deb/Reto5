/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.utp.misiontic2022.carloseduardojovengarcia.reto5.model.dao;

import co.edu.utp.misiontic2022.carloseduardojovengarcia.reto5.model.vo.ProyectoBancoVo;
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
public class ProyectoBancoDao {
    
    public List<ProyectoBancoVo> listar(String banco) throws SQLException{
        ArrayList<ProyectoBancoVo> respuesta = new ArrayList<ProyectoBancoVo>();

    Connection conn =JDBCUtilities.getConnection();
    PreparedStatement pstmt = null;
    ResultSet rset = null;
    
        String consulta ="SELECT P.ID_PROYECTO AS ID, P.CONSTRUCTORA, P.CIUDAD, P.CLASIFICACION, T.ESTRATO," 
        + "  L.NOMBRE ||' '||L.PRIMER_APELLIDO ||' '||L.SEGUNDO_APELLIDO AS LIDER"
        + "  FROM PROYECTO P"
        + "  JOIN TIPO T ON (P.ID_TIPO=T.ID_TIPO)"
        + "  JOIN LIDER L ON (P.ID_LIDER = L.ID_LIDER)"
        + "  WHERE P.BANCO_VINCULADO = ?"
        + "  ORDER BY FECHA_INICIO DESC, CIUDAD, CONSTRUCTORA";

        try{
            pstmt = conn.prepareStatement(consulta);
            pstmt.setString(1,banco);
            /*Para hacer el select se hace uso del executeQuery y para el insert se hace uso del executeUpdaet */
            rset=pstmt.executeQuery();
            while (rset.next()){
                ProyectoBancoVo vo = new ProyectoBancoVo();
                vo.setId(rset.getInt("id"));
                vo.setConstructora(rset.getString("constructora"));
                vo.setCiudad(rset.getString("ciudad"));
                vo.setClasificacion(rset.getString("clasificacion"));
                vo.setEstrato(rset.getInt("estrato"));
                vo.setLider(rset.getString("lider"));

                respuesta.add(vo);

            }

        }finally{
            if (rset !=null){
                rset.close();
            }
            if (pstmt !=null){
                pstmt.close();
            }
            if (conn !=null){
                conn.close();
            }
        }
        return respuesta;
    }
    
}
