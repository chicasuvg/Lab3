/*
 * Enfermera.java
 * 22/09/2017
 * @author Ana Lucia Hernandez 17138, Andrea Arguello 17801
 */
package Hospital;

public class Enfermera {
    //Atributos
    private int posicion;
    private String nombre;
    private String nit;
    private String dpi;
    private boolean intensivista;
    private double salario;
    private int anios;
    private double extra;
    private int guardias;
    private int guardiaJuan;
    private int guardiaEduardo;
    private int guardiaGuadalupe;
    
    /**
     * 
     * @param posicion
     * @param nombre
     * @param nit
     * @param dpi
     * @param salario
     * @param intensivista
     * @param anios 
     */
    public Enfermera(int posicion, String nombre, String nit, String dpi, double salario, boolean intensivista, int anios)
    {
        this.posicion = posicion;
        this.nombre= nombre;
        this.nit = nit;
        this.dpi = dpi;
        this.salario = salario;
        this.intensivista = intensivista;
        this.anios = anios;
        guardias = 0;
    }
    
    /**
     * 
     * @return extra
     */
    public double getExtra()
    {
        return extra;
    }
    public String getNIT()
    {
        return nit;
    }
    public String getdpi()
    {
        return dpi;
    }
    public double getSalario()
    {
        return salario;
    }
    public int getAnios()
    {
        return anios;
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
     * @return si es intensivista o no
     */
    public boolean getIntensivista()
    {
        return intensivista;
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
     * @return cantidad de guardias
     */
    public int getGuardias()
    {
        return guardias;
    }
    
    /**
     * 
     * @param doc nombre del doctor
     */
    public void numCompanero(String doc)//suma las veces que ha trabajado con un companero
    {
        if(doc.equals("Juan Perez"))
        {
            guardiaJuan += 1;
        }
        
        if(doc.equals("Eduardo Gonzalez"))
        {
            guardiaEduardo += 1;
        }
        if(doc.equals("Guadalupe Torres"))
        {
            guardiaGuadalupe += 1;
        }    
    }
    
    /**
     * 
     * @param doc 
     */
    public void restarCompanero(String doc)//resta las veces que ha trabajado con un companero
    {
        if(doc.equals("Juan Perez"))
        {
            guardiaJuan -= 1;
        }
        
        if(doc.equals("Eduardo Gonzalez"))
        {
            guardiaEduardo -= 1;
        }
        if(doc.equals("Guadalupe Torres"))
        {
            guardiaGuadalupe -= 1;
        }    
    }
    
    /**
     * 
     * @param doc
     * @return cantidad de guardias hechas con el companero
     */
    public int getGuardiasDoc(String doc){
        int guard=0;
        if(doc.equals("Juan Perez"))
        {
            guard=guardiaJuan;
        }
        
        if(doc.equals("Eduardo Gonzalez"))
        {
            guard=guardiaEduardo;
        }
        if(doc.equals("Guadalupe Torres"))
        {
            guard=guardiaGuadalupe;
        }
        return guard;
    }
    
    
    public void addTurno()
    {
        guardias +=1;
        if (guardias > 48)
        {
            this.extra = guardias * 500.0;
        }
    }
    
    public void restarGuardias()
    {
        guardias -=1;
    }
    
    
}
