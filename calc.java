import java.util.Scanner;
/******************************************************************
@author Carol Arevalo, Jose Miguel Gonzalez
@since 02/02/21

class calc calculadora - main
******************************************************************/

/** 
* main- works as a controler
* 
*/

public class calc {

    public static void main (String [ ] args) {

        //patron singleton
        calculadora calcu = calculadora.getInstance();
        Scanner scan = new Scanner(System.in);

        //calculadora c= new calculadora(); no se puede insanciar por singleton

         //ciclo

        int opcion = -1;

            while (opcion != 3){

            //-------------------------------------------------------------------------------------------------------------------
            System.out.println("\n\nBienvenido al programa de listas UwU");
            System.out.println("1. Stack");
            System.out.println("2. Listas");
            System.out.println("3. Salir\n\n");
            
            opcion = scan.nextInt();
            
            if (opcion == 1){
                

                int op= -1;
                while (op != 4){

                    //------------------------------------------------------------------------------------------------------------------------
                    System.out.println("\n\nEscoja un tipo: ");
                    System.out.println("1. Vector");
                    System.out.println("2. Arraylist");
                    System.out.println("3. List");
                    System.out.println("4. Salir\n\n");
                    
                    op = scan.nextInt();
                    
                    if (op == 1){
                        calcu.tipo(1);
                        calcu.decode("datos.txt");
                       
                    }else if(op == 2){
                        calcu.tipo(2);
                        calcu.decode("datos.txt");

                    }else if(op ==3){
                        calcu.tipo(3);
                        calcu.decode("datos.txt");

                    }else if(op == 4){
                        System.out.println("Ha salido de la opcion:)");

                    }
                }
               

            } else if (opcion == 2){

                int op= -1;
                while (op != 3){

                    //-----------------------------------------------------------------------------------------------------------------------
                    System.out.println("\n\nEscoja un tipo: ");
                    System.out.println("1. Simples");
                    System.out.println("2. Doblemente Encadenadas");
                    System.out.println("3. Salir\n\n");
                    
                    op = scan.nextInt();
                    
                    if (op == 1){
                        calcu.tipo(1);
                        calcu.decodeLista("datos.txt");
        
                    }else if(op == 2){
                        calcu.tipo(2);
                        calcu.decodeLista("datos.txt");

                    }else if(op == 3){
                        System.out.println("Ha salido de la opcion:)");

                    }else{
                        System.out.println("La opción es incorrecta");
                    }
                }
                
              
            } else if (opcion == 3){
                System.out.println("Ha cerrado el programa :)");

            }else{
                System.out.println("La opción es incorrecta");
            }

        }



        
    } 
} 