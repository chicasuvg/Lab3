/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital;

/**
 *
 * @author Ana
 */
public class Doctor {
    private int posicion;
    private String nombre;
    private String nit;
    private String dpi;
    private long colegiado;
    private double salario;
    private double extra;
    private boolean especialista;
    private double guardias;
    private int guardiaJuana;
    private int guardiaLidia;
    private int guardiaMiriam;
    private int guardiaLorna;
    private int guardiaMariela;
    private int conIntensivista; //variable que guarda las veces que este doctor ha realizado un turno con una enfermera del intensivo
    
    public Doctor(int posicion, String nombre, String nit, String dpi, long colegiado, double salario, boolean especialista)
    {
        this.posicion = posicion;
        this.nombre = nombre;
        this.nit = nit;
        this.dpi = dpi;
        this.colegiado = colegiado;
        this.salario = salario;
        this.especialista = especialista;
    }
    public double getExtra()
    {
        return extra;
    }
    public String getNombre()
    {
        return nombre;
    }
    public int getPosicion()
    {
        return posicion;
    }
    public boolean getEspecial()
    {
        return especialista;
    }
    public void addConIntensivo()
    {
        conIntensivista +=1;
    }
    public void addTurno()
    {
        guardias +=1;
        if (guardias > 2)
        {
            this.extra = guardias * 900.0;
        }
    }
    public void numCompanero(Enfermera enfermera)
    {
        if(enfermera.getNombre().equals("Juana Lopez"))
        {
            guardiaJuana += 1;
        }
        if(enfermera.getNombre().equals("Lidia Mendez"))
        {
            guardiaLidia += 1;
        }
        if(enfermera.getNombre().equals("Miriam Garcia"))
        {
            guardiaMiriam += 1;
        }
        if(enfermera.getNombre().equals("Lorna Suarez"))
        {
            guardiaLorna += 1;
        }
        if(enfermera.getNombre().equals("Mariela Rodriguez"))
        {
            guardiaMariela += 1;
        }
    }
    
}
