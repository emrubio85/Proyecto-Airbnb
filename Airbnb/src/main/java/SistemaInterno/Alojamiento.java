/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SistemaInterno;

import Usuarios.Anfitrion;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

/**
 *
 * @author vv
 */
public class Alojamiento {
    
    private static int numAlojamiento =1011;
    private int alojaminetoID;
    private Anfitrion anfitrion;
    private double precio;
    private int habitaciones;
    private String ubicacion;
    private double calificacion;
    private double tarifaAirbnb;
    private ArrayList <String> fechasDisponibles;
    private ArrayList<Double> calificaciones= new ArrayList<>();
    private ArrayList<String> servicios = new ArrayList<>();
    private ArrayList<String> reglamento = new ArrayList<>();
    private ArrayList<Resenha> resenhas = new ArrayList<>();

    //Contructor que se usa cuando por consola se crea un alojamiento
    public Alojamiento(Anfitrion anfitrion, double precio, int habitaciones, String ubicacion,double tarifaAirbnb) {
        this.alojaminetoID = numAlojamiento;
        this.anfitrion = anfitrion;
        this.precio = precio;
        this.habitaciones = habitaciones;
        this.ubicacion = ubicacion;
        this.calificacion = 0;
        this.tarifaAirbnb = tarifaAirbnb;
        numAlojamiento++;
        this.fechasDisponibles=fechas();
    }
    
    //Constructor usado al recibir datos de la BD
    public Alojamiento(int alojaminetoID,Anfitrion anfitrion, double precio, int habitaciones, String ubicacion,double tarifaAirbnb) {
        this.alojaminetoID = alojaminetoID;
        this.anfitrion = anfitrion;
        this.precio = precio;
        this.habitaciones = habitaciones;
        this.ubicacion = ubicacion;
        this.calificacion = 0;
        this.tarifaAirbnb=tarifaAirbnb;
        numAlojamiento++;
        this.fechasDisponibles=fechas();
    }

    public int getAlojaminetoID() {
        return alojaminetoID;
    }
    
    public Anfitrion getAnfitrion() {
        return anfitrion;
    }

    public double getPrecio() {
        return precio;
    }

    public int getHabitaciones() {
        return habitaciones;
    }

    public double getTarifaAirbnb() {
        return tarifaAirbnb;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public double getCalificacion() {
        return calificacion;
    }
    
    
    
    public ArrayList <String> getFechasDisponibles() {
        return fechasDisponibles;
    }
    
    public void setServicios(ArrayList<String> servicios) {
        this.servicios = servicios;
    }
    public void setReglamento(ArrayList<String> reglamento) {
        this.reglamento = reglamento;
    }
    public ArrayList<Resenha> getResenhas() {
        return resenhas;
    }

    @Override
    public String toString() {
        return  "alojaminetoID=" + alojaminetoID + ", anfitrion=" + anfitrion.getAnfitrion() + ", precio=" + precio + ", habitaciones=" + habitaciones + ", ubicacion=" + ubicacion + ", calificacion=" + calificacion + ", calificaciones=" + calificaciones + ", servicios=" + servicios + ", reglamento=" + reglamento;
    }
    
    public ArrayList<String> fechas(){
        ArrayList<String> fechasD = new ArrayList<>();
        
        for (Integer mes=7;mes<=9;mes++){
            String txtMes = mes.toString();
            
            if(mes<10){
                txtMes = "0"+txtMes;
            }
            for (Integer dia=1;dia<=30;dia++){
                String txtDia = dia.toString();
                
                if(dia<10){
                    txtDia = "0"+txtDia;
                }
                String fecha = "2023-"+txtMes+"-"+txtDia;
                fechasD.add(fecha);
            }
        }
        return fechasD;
    }    
    
 
    public void enlistarAlojamiento(){
        System.out.printf("Lugar en: %s\n    Precio: %s x noche\n    Calificacion: %s estrellas\n    AlojamientoID: %d\n",this.ubicacion,this.precio,this.calificacion,this.alojaminetoID);
    }
    
    public void detallarAlojamientoSeleccionado(){
        System.out.printf("\n %s\n- Anfitrion: %s\n- Costo: %s x noche\n- Habitaciones: %d\n- Calificacion: %s estrellas\n- AlojamientoID: %d\n",this.ubicacion.toUpperCase(),this.anfitrion.getAnfitrion(),this.precio,this.habitaciones,this.calificacion,this.alojaminetoID);
        
        if (!servicios.isEmpty()){
            System.out.println("\n*SERVICIOS DEL ALOJAMINETO*");
            for(String s:servicios){
                System.out.println("-"+s);
            }
        }
        if(!reglamento.isEmpty()){
            System.out.println("\n*REGLAS DEL ALOJAMINETO*");
            for(String s:reglamento){
                System.out.println("-"+s);
            }
        }
    }
    
    
    
//---------------------------------------------------------------------------------------------------
    //Metodo que cambia la calificacion del alojamineto
    public void cambiarCalificaion(double nuevaCalificacion){  
        this.calificaciones.add(nuevaCalificacion);
        double calificacion=0;
        
        for(Double d:this.calificaciones){
            calificacion+=d;
        }     
        double calificaionFinal = (calificacion)/(calificaciones.size());
        this.calificacion=calificaionFinal;
    }
    
    public void addUnaResenha(Resenha resenha){
        resenhas.add(resenha);
    }
  
//-------------------------------------------------------------------------------------------
    //Metodos de la opcion publicar alojamientos (ANFITRION)
    public void addUnServicio(){
        Scanner sc = new Scanner(System.in);
        int opcion=0;
        try{
            System.out.println("Servicio que posee su alojamiento: ");
            String servicio = sc.nextLine();
            if (!servicio.isEmpty() && !servicio.isBlank()){
                this.servicios.add(servicio);
            }
            System.out.println("Desea agregar otro servicio?\n1.si\n2.no");
            opcion =Sistema.getOpcion(2);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            if(opcion==1){
                this.addUnServicio();
            }
        }
    }
    
    public void addUnReglamento(){
        Scanner sc = new Scanner(System.in);
        int opcion=0;

        try{
            System.out.println("Regla a agregar: ");
            String regla = sc.nextLine();
            if (!regla.isEmpty() && !regla.isBlank()){
                this.reglamento.add(regla);
            }
            System.out.println("Desea agregar otra regla?\n1.si\n2.no");
            opcion =Sistema.getOpcion(2);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            if(opcion==1){
                this.addUnReglamento();
            }
        }
    }

    
}
