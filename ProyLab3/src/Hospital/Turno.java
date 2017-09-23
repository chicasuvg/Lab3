/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hospital;

/**
 *
 * @author Ana
 * 
 */
public class Turno {
    private Enfermera enfermera;
    private Doctor medico;
    private boolean reemplazable;
    
    public Turno()
    {
        reemplazable = true;
    }
    public void setEnfermera(Enfermera enfermera)
    {
        this.enfermera = enfermera;
    }
    public void setDoctor(Doctor doctor)
    {
        this.medico = doctor;
    }
    public void setReemplazable (boolean disp)
    {
        this.reemplazable = disp;
    }
    public Enfermera getEnfermera()
    {
        return enfermera;
    }
    public Doctor getDoctor()
    {
        return medico;
    }
    public boolean getReemplazable()
    {
        return reemplazable;
    }
    public double totalAbonado()
    {
        double abono = (enfermera.getExtra())+ (medico.getExtra());
        return abono;
    }
}
