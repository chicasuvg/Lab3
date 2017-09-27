/*
 * Guardia.java
 * 22/09/2017
 * @author Ana Lucia Hernandez 17138, Andrea Arguello 17801 
 */
package Hospital;


import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Guardia {
    //Atributos
    private Doctor[] doctores;
    private Enfermera[] enfermeras;
    private Turno[][] pizarra;
    
    public Guardia()
    {
        int[] posiciones = {0, 1, 2, 3, 4};
        String[] nombresEnf = {"Juana Lopez", "Lidia Mendez","Miriam Garcia", "Lorna Suarez", "Mariela Rodriguez"};
        String[] nitsEnf = {"563269-K", "7654376-6", "9876456-8", "4567865-8", "6789654-8"};
        String[] dpiEnf = {"5643213450101", "1246547850101", "6547854370101", "569374160101", "3254786540101"};
        boolean[] intensivo = {false, true, false, true, false};
        double[] salarioEnf = {6000.0, 9000.0, 8000.0, 10000.0, 8000.0};
        int[] anios = {2, 2, 14, 12, 2};
        enfermeras = new Enfermera[5];
        String[] nombresDoc = {"Juan Perez", "Luis Gutierrez","Eduardo Gonzalez", "Guadalupe Torres", "Maria Castillo"};
        String[] nitsDoc = {"6799652-3", "964547-3", "8786456-6", "5684873-1", "8765485-7"};
        String[] dpiDoc = {"1234567870101", "9876543260101", "5642871750101", "4562973920101", "569395290101"};
        long[] colegiados = {89098, 98554, 5653, 10098, 67965};
        double[] salarioDoc = {15000.0, 12000.0, 17000.0, 17000.0, 13000.0};
        boolean[] especialista = {true, false, true, true , false};
        doctores = new Doctor[5];
        
        for (int i =0; i<5; i++)
        {
            Enfermera enfermera = new Enfermera(posiciones[i], nombresEnf[i], nitsEnf[i], dpiEnf[i], salarioEnf[i], intensivo[i], anios[i]);
            enfermeras[i] = enfermera;
            Doctor doctor = new Doctor(posiciones[i], nombresDoc[i], nitsDoc[i], dpiDoc[i], colegiados[i], salarioDoc[i], especialista[i]);
            doctores[i] = doctor;
        }
        pizarra = new Turno[31][12];
    }
    
    /**
     * Metodo para crear la pizarra que contendra todos los turnos del anio.
     * 
     */
    public void crearMatriz() //crea el calendario de los turnos de los doctores y enfermeras
    {
        Random genRandom = new Random(); //Crea el objeto de tipo random
        Enfermera nurse = null;
        Doctor doc = null;
        for (int i =0; i< 31; i++)
        {
            for (int j=0; j<12; j++)
            {
                int positionE = genRandom.nextInt(5);
                int positionD = genRandom.nextInt(5);
                for(Enfermera enfermera: enfermeras)
                {
                    if(enfermera.getPosicion() == positionE)
                    {
                        nurse = enfermera;
                    }
                }
                for (Doctor doctor: doctores)
                {
                    if(doctor.getPosicion() == positionD)
                    {
                        doc = doctor;
                    }
                }
                pizarra[i][j] = new Turno(nurse, doc);
                pizarra[i][j].getEnfermera().addTurno();
                if((pizarra[i][j].getEnfermera().getIntensivista()==true) && (pizarra[i][j].getDoctor().getEspecial()==true))
                {
                    pizarra[i][j].getEnfermera().numCompanero(pizarra[i][j].getDoctor().getNombre()); //cuenta las veces que un especialista y una intensivista han trabajado
                }
            }
        }
    }
    

    
    /**
     * Metodo para fijar todos los valores de la matriz en la tabla del GUI
     * @param tabla: tabla del GUI
     * 
     */
    public void setPizarra(JTable tabla)
    {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        for (int i =0; i< 31; i++)
        {
            for (int j=0; j<12; j++)
            {
                String doct = String.valueOf(pizarra[i][j].getDoctor().getNombre());
                String enf = pizarra[i][j].getEnfermera().getNombre();
                String info = "Dr.: "+doct+ "\nEnf.: "+enf;
                tabla.setValueAt(info, i, j+1);
            }
        //Fechas en las que no habrá un turno, debido a que no existen en un calendario real. 
        tabla.setValueAt("",28,2);
        tabla.setValueAt("",29,2);
        tabla.setValueAt("",30,2);
        tabla.setValueAt("",30,4);
        tabla.setValueAt("",30,6);
        tabla.setValueAt("",30,9);
        tabla.setValueAt("",30,11);
        }
        
    }
    /**
     * Metodo para obtener el turno de una fecha específica. 
     * @param dia: dia en el cual se quiere obtener el turno
     * @param mes: mes en el cual se quiere obtener el turno
     * @return objeto turno que se encuentra en una fecha específica
     */
    public Turno getFechaPizarra(int dia, int mes)
    {
        return pizarra[dia][mes];
    }
    
    /**
     * Metodo para hacer el cambio del nombre de la enfermera cuando se deseed
     * @param dia: dia en el cual se quiere hacer el cambio
     * @param mes: mes en el cual se quiere hacer el cambio
     * @param nombre: nombre de la enfermera que se quiere poner en lugar de la anterior
     */
    public void cambioEnfermera(int dia, int mes, String nombre) //para hacer el cambio de enfermera
    {
        for (Enfermera enfermera: enfermeras)
        {
            if (enfermera.getNombre().equals(nombre))
            {
                if(((pizarra[dia][mes].getEnfermera().getIntensivista()==true) && (enfermera.getIntensivista() == true))||((pizarra[dia][mes].getEnfermera().getIntensivista()==false) && (enfermera.getIntensivista() == true)))
                {
                    pizarra[dia][mes].getEnfermera().restarGuardias();
                    pizarra[dia][mes].setReemplazable(true);
                    if (pizarra[dia][mes].getDoctor().getEspecial() ==true)
                    {
                        String doc=pizarra[dia][mes].getDoctor().getNombre();
                        pizarra[dia][mes].getEnfermera().restarCompanero(doc); //a la enfermera anterior le resta una guardia conjunta con este especialista
                        enfermera.numCompanero(doc); //le agrega esta guardia a la nueva enfermera
                    }
                    pizarra[dia][mes].setEnfermera(enfermera);
                    pizarra[dia][mes].getEnfermera().addTurno();
                }
                else
                {
                    pizarra[dia][mes].setReemplazable(false);
                }
            }
        }
    }
    /**
     * Metodo que devuelve la enfermera que ha hecho mas turnos
     * @return String nombre de la enfermera
     */
    public String masGuardias() 
    {
        String nombre = "";
        int mayor =0;
        for (Enfermera enfermera : enfermeras)
        {if(enfermera.getIntensivista()==true){
            if (enfermera.getGuardias() > mayor)
            {
                mayor = enfermera.getGuardias();
                nombre = enfermera.getNombre();
            }
        }}
        return nombre;
    }
    
    
    /**
     * Para obtener la cantidad de turnos que un doctor especialista o una enfermera del intensivo ha tenido con otro doctor
     * especialista/enfermera del intensivo específico.
     * @param enf nombre de enfermera
     * @param doc nombre de doctor
     * @return veces que han trabajado juntos
     */
    public int guardiasConjuntas(String enf, String doc){
        int conjunto=0;
        for(Enfermera enfermera: enfermeras){
            if(enfermera.getNombre().equals(enf)){
                conjunto=enfermera.getGuardiasDoc(doc);
                }
            }
        return conjunto;
    }
    
    
    /**
     * Metodo para obtener el total que se les ha pagado a los trabajadores por turnos extra.
     * @return double total que se ha pagado de bonos extra por turnos en el año
     */
    public double getTotalDevengado()
    {
        double totalDevengado =0.0;
        for (int i =0; i< 31; i++)
        {
            for (int j=0; j<12; j++)
            {
                totalDevengado += pizarra[i][j].totalAbonado();
            }
        }
        return totalDevengado;
    }
    
    /**
     * Método para obtener la enfermera perteneciente al intensivo que realizó la mayor cantidad de turnos.
     * @return extra de la intensivista
     */
    public double intensivistaExtra(){
        double extra = 0.0;
            int mayor =0;
        for (Enfermera enfermera : enfermeras)
        {
            if (enfermera.getGuardias() > mayor)
            {
                mayor = enfermera.getGuardias();
                extra = enfermera.getExtra();
            }
        }
        return extra;
    }
    /**
     * Metodo para mostrar la informacion del doctor y la enfermera de cada turno
     * @param dia: del cual quiere ver la informacion del turno
     * @param mes: mes del cual quiere ver la informacion del turno
     * @return string informacion del turno
     */
    public String infoTurno(int dia, int mes)
    {
        String doct = String.valueOf(pizarra[dia][mes].getDoctor().getNombre());
        String dnit = String.valueOf(pizarra[dia][mes].getDoctor().getNIT());
        String ddpi = String.valueOf(pizarra[dia][mes].getDoctor().getdpi());
        String dcol = String.valueOf(pizarra[dia][mes].getDoctor().getColegiado());
        String dsal = String.valueOf(pizarra[dia][mes].getDoctor().getSalario());
        String desp ="";
        if(pizarra[dia][mes].getDoctor().getEspecial() == true)
        {
            desp = "Si";
        }
        else {desp += "No";}
        String enf = pizarra[dia][mes].getEnfermera().getNombre();
        String enit = pizarra[dia][mes].getEnfermera().getNIT();
        String edpi = pizarra[dia][mes].getEnfermera().getdpi();
        String inten = "";
        if(pizarra[dia][mes].getEnfermera().getIntensivista() == true)
        {
            inten = "Si";
        }
        else {inten += "No";}
        String esal = String.valueOf(pizarra[dia][mes].getEnfermera().getSalario());
        String anios = String.valueOf(pizarra[dia][mes].getEnfermera().getAnios());
        
        String info = "DOCTOR: \n\nNombre: "+doct+ "\nNIT: "+dnit+"\nDPI: "+ddpi+"\nColegiado: "+dcol+"\nSalario: Q"+dsal+"\nEspecialista: "+desp;
        info += "\n\nENFERMERA: \n\nNombre: "+enf+"\nNIT: "+enit+"\nDPI: "+edpi+"\nIntensivista: "+inten+"\nSalario: Q"+esal+"\nAños de experiencia: "+ anios;
        return info;
    }
    
    
}
