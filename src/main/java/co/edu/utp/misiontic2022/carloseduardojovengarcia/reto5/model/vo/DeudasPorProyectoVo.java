
package co.edu.utp.misiontic2022.carloseduardojovengarcia.reto5.model.vo;

/**
 *
 * @author carlos
 */
public class DeudasPorProyectoVo {
    
     private Integer id;
    private Double valor;


    /**
     * @return Integer return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return Double return the valor
     */
    public Double getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("%3d %,15.1f", id, valor);
    }


    
}
