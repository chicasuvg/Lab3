/*
 * 
 */
package Hospital;

/**
 *
 * @author Ana
 */
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Guardia {
    private Doctor[] doctores;
    private Enfermera[] enfermeras;
    private Turno[][] pizarra;
    
    public Guardia()
    {
        int[] posiciones = {1, 2, 3, 4, 5};
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
                int positionE = genRandom.nextInt(6);
                int positionD = genRandom.nextInt(6);
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
            }
        }
        for(int k =0; k<31; k++)
        {
            for(int h=0; h<12; h++)
            {
                if ((pizarra[k][h].getEnfermera().getIntensivista() == true) && (pizarra[k][h].getDoctor().getEspecial() == true))
                {
                    pizarra[k][h].getEnfermera().addConEspecial();
                    pizarra[k][h].getDoctor().addConIntensivo();
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
                String doct = pizarra[i][j].getDoctor().getNombre();
                String enf = pizarra[i][j].getEnfermera().getNombre();
                String info = "Dr.: "+doct+ "\nEnf.: "+enf;
                tabla.setValueAt(info, i, j+1);
            }
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
                if (enfermera.getIntensivista() == true)
                {
                    pizarra[dia][mes].getEnfermera().restarGuardias();
                    pizarra[dia][mes].setReemplazable(true);
                    pizarra[dia][mes].setEnfermera(enfermera);
                    pizarra[dia][mes].getEnfermera().addTurno();
                    if (pizarra[dia][mes].getDoctor().getEspecial() ==true)
                    {
                        pizarra[dia][mes].getEnfermera().restarConEspecial();
                        pizarra[dia][mes].getDoctor().restarConIntensivo();
                    }
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
        {
            if (enfermera.getGuardias() > mayor)
            {
                mayor = enfermera.getGuardias();
                nombre = enfermera.getNombre();
            }
        }
        return nombre;
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
    
}
