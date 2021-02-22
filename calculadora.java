/******************************************************************
@author Carol Arevalo 
@since 02/02/21

class calculadora1
******************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

/**
*Implemenrs interface calculadora1
*uso of stack in a calculator
*/
public class calculadora <E> implements Icalculadora <E>{

//SINGLETON///////////////////////////////
static calculadora c = new calculadora();

private calculadora(){
}

public static calculadora getInstance(){
    return c;
}

///////////////////////////////////////
public int tipo= -1;

public void tipo(int t){
    tipo = t; 
}

int resultado_final=-1;

/** 
* suma method - to add two numbers 
* @return int
* @param x,y
*/
public int suma(int x, int y){
    int resultado= x+y;
    return resultado;
}

/** 
* resta method - to subtract two numbers 
* @return int
* @param x,y
*/
public int resta(int x, int y){
    int resultado= x-y;
    return resultado;

}

/** 
* multiplicaion method - to multiplicate two numbers 
* @return int
* @param x,y
*/
public int multiplicacion(int x, int y){
    int resultado= x*y;
    return resultado;

}

/** 
* division method - to divide two numbers 
* @return int
* @param x,y
*/
public int division(int x, int y){

    //try catch para division dentro de cero
    try{
        int resultado= x/y;
        return resultado;

    }catch(ArithmeticException e){
        System.out.println("Error: division dentro de 0");
    }
    
    resultado_final =0;
    return resultado_final; 
}

//******************************************************************************* */

/** 
* operar method - push or operate a number froma stack 
* @return int
* @param x
*/
public int operar(AbstractStack stack){ 

    int resultado=-1;
    ArrayList <String> temporal= new ArrayList<String>();
    boolean bien= stack.empty();

    while(bien==false){
        String temp= stack.pop().toString();
        bien= stack.empty();

        int a= -1;
        int b= -1;

        //--------------------------------------------------
        if(temp.equals("+")){
        a= Integer.parseInt(temporal.get(temporal.size()-1)+"");
        temporal.remove(temporal.size()-1);
        b= Integer.parseInt(temporal.get(temporal.size()-1)+"");
        temporal.remove(temporal.size()-1);
        resultado= suma(a, b);
        temporal.add(resultado+"");
        System.out.println("Se suma");
        
        //-------------------------------------------------------
        }else if(temp.equals("-")){
            a= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            b= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            resultado= resta(a, b);
            temporal.add(resultado+"");
            System.out.println("Se resta");
        
        //-------------------------------------------------------
        }else if(temp.equals("*")){
            a= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            b= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            resultado= multiplicacion(a, b);
            temporal.add(resultado+"");
            System.out.println("Se multpilica");

        //-------------------------------------------------------
        }else if(temp.equals("/")){
            a= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            b= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            resultado= division(a, b);
            temporal.add(resultado+"");
            System.out.println("Se divide");

        //-------------------------------------------------------
        }else{
            boolean t= true; 
            //Si el numero es valido hace push
            if(t==true){
                temporal.add(temp+"");
                System.out.println("Se hace push");
            }
            
        }
    }

    //mientras no este vacio el stack 
    int tamano= temporal.size();

    if(temporal.isEmpty()==false){
        //tiene que quedar un dato de lo contrario hizo falta operadores
        if(tamano!=1){
            System.out.println("Operacion incompleta: operadores faltantes");
        }
        resultado_final= Integer.parseInt(temporal.remove(temporal.size()-1)+"");
    }
    
    return resultado_final;
}

//******************************************************************************* */

/** 
* decode method - reads operation from file.txt and save them in a stack 
* @return String
* @param a
*/

public String decode(String a){
    ArrayList <String> operaciones= new ArrayList <String>();

    String respuesta =""; 

    File file = new File(a);
    Scanner scan = null;
    
    //verifica que lea el archivo
    try{
        scan= new Scanner(file);

    }catch(FileNotFoundException e){
        System.out.println("\nNo se ha encontrado el archivo solicitado");
    }

    //va metiendo las operaciones a un stack
    while(scan.hasNextLine()){
        operaciones.add(scan.nextLine());
    }

    //va a leer cada operacion 
    while(operaciones.isEmpty()==false){
        System.out.println("\nOperacion: " + operaciones.get(operaciones.size()-1));

        String temp=(String) operaciones.get(operaciones.size()-1);
        operaciones.remove(operaciones.size()-1);

        AbstractStack <String> operacion = trabajar(tipo);

        //va a separar los carcateres de la operacion y los envia a un stack 
        for(int i=temp.length()-1; i>=0; i--){
            
            String caracter= Character.toString(temp.charAt(i));
            if(!caracter.equals(" ")){
                operacion.push(caracter);
            }

        }

        //el stack es enviado a operar
        operacion = inout(operacion);
        int resultado= operar(operacion);
        System.out.println("La respuesta es: " + resultado);

    }
    return respuesta; 
}

//pasar de infix a postfix-----------------------------------------------------------------------------.
public AbstractStack inout(AbstractStack<String> stack){

    AbstractStack stackRespuesta = trabajar(tipo);
    ArrayList <String> secundaria = new ArrayList <String>();

    while(stack.empty() == false){ 

        String temporal = (String)stack.pop();

        if(Character.isDigit(temporal.charAt(0))==true){
            stackRespuesta.push(temporal);
        }
        if(temporal.equals("(")){
            secundaria.add(temporal);

        }
        if(temporal.equals("*") || temporal.equals("/")){
            secundaria.add(temporal);
        }
        if(temporal.equals("+") || temporal.equals("-")){
            secundaria.add(temporal);            
        }
        if(temporal.equals(")")){

            int tama = secundaria.size();
            for(int i = 0; i < tama-1; i++){
                //if(secundaria.size() >= 1){

                    String temp = secundaria.get(i++);
                    String temp2 = secundaria.get(i);

                    if(secundaria.get(i).equals("+")){
                      
                        if(temp.equals("*") || temp.equals("/")){
                            
                            int numerito = i+1;
                            secundaria.add(i, temp);
                            secundaria.add(numerito, temp2);
                            
                        } else{
                            
                        }
                    } else if(secundaria.get(i).equals("-")){
                        if(temp.equals("*") || temp.equals("/")){
                            
                            int numerito = i+1;
                            secundaria.add(i, temp);
                            secundaria.add(numerito, temp2);
                            
                        }
                    } 
                    if(secundaria.get(i).equals("(")){
                        secundaria.remove(i);
                        String deMientras = secundaria.get(i++);
                        secundaria.add(i, deMientras);
                        //tama--;
                        //i--;
                    } 
                //}
                stackRespuesta.push(secundaria.get(i));
            }
            secundaria.clear();
        }
    }

    for(int i=0; i<secundaria.size(); i++){
        if(secundaria.size() > 1){
            String temp = secundaria.get(i++);
            if(secundaria.get(i).equals("+") || secundaria.get(i).equals("-")){
                if(temp.equals("*") || temp.equals("/")){
                    String temp2 = secundaria.get(i);
                    secundaria.add(i, temp);
                    secundaria.add(i++, temp2);   
                }
            }
        }
        stackRespuesta.push(secundaria.get(i));
    }

    secundaria.clear();

    AbstractStack stackRespuesta2 = trabajar(tipo);

    while(stackRespuesta.empty()==false){
        stackRespuesta2.push(stackRespuesta.pop());
    }
   
    return stackRespuesta2;
}
//-------------------------------------------------------------------------------------------

public AbstractStack trabajar(int tipo){
    AbstractStack stack= null;
    if(tipo==1){
        stack = new stackVector<>();
        
    }else if(tipo==2){
        stack = new StackArraylist<>();

    }else if(tipo==3){
        stack = new StackList<>();
    }
    return stack;
    
}

///////////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////////
//-------------------------------------------LISTAS-----------------------------------------------------//

public int operarLista(AbstractList lista){ 

    int resultado=-1;
    ArrayList <String> temporal= new ArrayList<String>();
    boolean bien= lista.isEmpty();

    while(bien==false){
        String temp = lista.removeAtEnd().toString();
        bien= lista.isEmpty();

        int a= -1;
        int b= -1;

        //--------------------------------------------------
        if(temp.equals("+")){
        a= Integer.parseInt(temporal.get(temporal.size()-1)+"");
        temporal.remove(temporal.size()-1);
        b= Integer.parseInt(temporal.get(temporal.size()-1)+"");
        temporal.remove(temporal.size()-1);
        resultado= suma(a, b);
        temporal.add(resultado+"");
        System.out.println("Se suma");
        
        //-------------------------------------------------------
        }else if(temp.equals("-")){
            a= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            b= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            resultado= resta(a, b);
            temporal.add(resultado+"");
            System.out.println("Se resta");
        
        //-------------------------------------------------------
        }else if(temp.equals("*")){
            a= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            b= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            resultado= multiplicacion(a, b);
            temporal.add(resultado+"");
            System.out.println("Se multpilica");

        //-------------------------------------------------------
        }else if(temp.equals("/")){
            a= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            b= Integer.parseInt(temporal.get(temporal.size()-1)+"");
            temporal.remove(temporal.size()-1);
            resultado= division(a, b);
            temporal.add(resultado+"");
            System.out.println("Se divide");

        //-------------------------------------------------------
        }else{
            boolean t= true; 
            //Si el numero es valido hace push
            if(t==true){
                temporal.add(temp+"");
                System.out.println("Se hace push");
            }
            
        }
    }

    //mientras no este vacio el stack 
    int tamano= temporal.size();

    if(temporal.isEmpty()==false){
        //tiene que quedar un dato de lo contrario hizo falta operadores
        if(tamano!=1){
            System.out.println("Operacion incompleta: operadores faltantes");
        }
        resultado_final= Integer.parseInt(temporal.remove(temporal.size()-1)+"");
    }
    
    return resultado_final;

}

public String decodeLista(String a){
    ArrayList <String> operaciones= new ArrayList <String>();

    String respuesta =""; 

    File file = new File(a);
    Scanner scan = null;
    
    //verifica que lea el archivo
    try{
        scan= new Scanner(file);

    }catch(FileNotFoundException e){
        System.out.println("\nNo se ha encontrado el archivo solicitado");
    }

    //va metiendo las operaciones a un stack
    while(scan.hasNextLine()){
        operaciones.add(scan.nextLine());
    }

    //va a leer cada operacion 
    while(operaciones.isEmpty()==false){
        System.out.println("\nOperacion: " + operaciones.get(operaciones.size()-1));

        String temp=(String) operaciones.get(operaciones.size()-1);
        operaciones.remove(operaciones.size()-1);

        AbstractList<String> operacion = trabajarLista(tipo);

        //va a separar los carcateres de la operacion y los envia a un stack 
        for(int i=temp.length()-1; i>=0; i--){
            
            String caracter= Character.toString(temp.charAt(i));
            if(!caracter.equals(" ")){
                operacion.insertAtEnd(caracter);
            }

        }
        //el stack es enviado a operar
        operacion = inoutLista(operacion);
        int resultado= operarLista(operacion);
        System.out.println("La respuesta es: " + resultado);

    }
    return respuesta; 
}


public AbstractList inoutLista(AbstractList<String> stack){

    AbstractList listaRespuesta = trabajarLista(tipo);
    ArrayList <String> secundaria = new ArrayList <String>();

    while(stack.isEmpty() == false){ 

        String temporal = (String)stack.removeAtEnd();

        if(Character.isDigit(temporal.charAt(0))==true){
            listaRespuesta.insertAtEnd(temporal);
        }
        if(temporal.equals("(")){
            secundaria.add(temporal);

        }
        if(temporal.equals("*") || temporal.equals("/")){
            secundaria.add(temporal);
        }
        if(temporal.equals("+") || temporal.equals("-")){
            secundaria.add(temporal);            
        }
        if(temporal.equals(")")){

            int tama = secundaria.size();
            for(int i = 0; i < tama-1; i++){
                String temp = secundaria.get(i++);
                if(secundaria.get(i).equals("+") || secundaria.get(i).equals("-")){
                    if(temp.equals("*") || temp.equals("/")){

                        String temp2 = secundaria.get(i);
                        secundaria.add(i, temp);
                        secundaria.add(i++, temp2);
                        
                    }
                    
                }else if(temp.equals("(") || temp.equals(")")){
                    secundaria.remove(i);
                }
                listaRespuesta.insertAtEnd(secundaria.get(i));
            }
            secundaria.clear();
        }
    }

    for(int i=0; i<secundaria.size(); i++){
        if(secundaria.size()>1){
            String temp = secundaria.get(i++);
            if(secundaria.get(i).equals("+") || secundaria.get(i).equals("-")){
                if(temp.equals("*") || temp.equals("/")){
                    String temp2 = secundaria.get(i);
                    secundaria.add(i, temp);
                    secundaria.add(i++, temp2);   
                }
            }
        }

        listaRespuesta.insertAtEnd(secundaria.get(i));
    }

    secundaria.clear();

    AbstractList listaRespuesta2 = trabajarLista(tipo);

    while(listaRespuesta.isEmpty()==false){
        listaRespuesta2.insertAtEnd(listaRespuesta.removeAtEnd());
    }

    return listaRespuesta2;
}


public AbstractList trabajarLista(int tipo){
    AbstractList list= null;
    if(tipo==1){
        list = new ListLinkedList<>();
        
    }else if(tipo==2){
        list = new ListDoubleLinkedList<>();

    }
    return list;
    
}
}
