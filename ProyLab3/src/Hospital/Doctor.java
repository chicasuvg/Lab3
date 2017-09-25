/* 
 * Doctor.java
 * 22/09/2017
 * @author Ana Lucia Hernandez 17138, Andrea Arguello 17801
 */
package Hospital;


public class Doctor {
    //Atributos
    private int posicion;
    private String nombre;
    private String nit;
    private String dpi;
    private long colegiado;
    private double salario;
    private double extra;
    private boolean especialista;
    private double guardias;
    
    /**
     * 
     * @param posicion
     * @param nombre
     * @param nit
     * @param dpi
     * @param colegiado
     * @param salario
     * @param especialista 
     */
    public Doctor(int posicion, String nombre, String nit, String dpi, long colegiado, double salario, boolean especialista)
    {
        this.posicion = posicion;
        this.nombre = nombre;
        this.nit = nit;
        this.dpi = dpi;
        this.colegiado = colegiado;
        this.salario = salario;
        this.especialista = especialista;
        guardias =0;
    }
    public String getNIT()
    {
        return nit;
    }
    public String getdpi()
    {
        return dpi;
    }
    public long getColegiado()
    {
        return colegiado;
    }
    public double getSalario()
    {
        return salario;
    }
    
    /**
     * 
     * @return extra
     */
    public double getExtra()
    {
        return extra;
    }
    
    /**
     * 
     * @return nombre
     */
    public String getNombre()
    {
        return nombre;
    }
    
    /**
     * 
     * @return posicion
     */
    public int getPosicion()
    {
        return posicion;
    }
    
    /**
     * 
     * @return es especialista o no
     */
    public boolean getEspecial()
    {
        return especialista;
    }
    
    
    public void addTurno()
    {
        guardias +=1;
        if (guardias > 24)
        {
            this.extra = guardias * 900.0;
        }
    }
    
}
