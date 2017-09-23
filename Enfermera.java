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
public class Enfermera {
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
    private int guardiaLuis;
    private int guardiaEduardo;
    private int guardiaLupe;
    private int guardiaMaria;
    private int conEspecialista;//variable que guarda las veces que esta enfermera ha realizado un turno con un doctor especialista
    
    public Enfermera(int posicion, String nombre, String nit, String dpi, double salario, boolean intensivista, int anios)
    {
        this.posicion = posicion;
        this.nombre= nombre;
        this.nit = nit;
        this.dpi = dpi;
        this.salario = salario;
        this.intensivista = intensivista;
        this.anios = anios;
    }
    public double getExtra()
    {
        return extra;
    }
    public String getNombre()
    {
        return nombre;
    }
    public boolean getIntensivista()
    {
        return intensivista;
    }
    public int getPosicion()
    {
        return posicion;
    }
    public int getGuardias()
    {
        return guardias;
    }
    public void addConEspecial()
    {
        conEspecialista +=1;
    }
    public void numCompanero(Doctor doc)
    {
        if(doc.getNombre().equals("Juan Perez"))
        {
            guardiaJuan += 1;
        }
        if(doc.getNombre().equals("Luis Gutierrez"))
        {
            guardiaLuis += 1;
        }
        if(doc.getNombre().equals("Eduardo Gonzalez"))
        {
            guardiaEduardo += 1;
        }
        if(doc.getNombre().equals("Guadalupe Torres"))
        {
            guardiaLupe += 1;
        }
        if(doc.getNombre().equals("Maria Castillo"))
        {
            guardiaMaria += 1;
        }
        
    }
    public void addTurno()
    {
        guardias +=1;
        if (guardias > 4)
        {
            this.extra = guardias * 500.0;
        }
    }
    public void restarGuardias()
    {
        guardias -=1;
    }
    
    
}
