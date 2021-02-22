/******************************************************************
@author Carol Arevalo 
@since 02/02/21

class interfaz calculadora
******************************************************************/

/** 
* interface - determinates methodes and parameters
* 
*/

public interface Icalculadora <E>{
        /** 
    * suma method - to add two numbers 
    * @return int
    * @param x,y
    */
    public int suma(int x, int y);

    /** 
    * resta method - to subtract two numbers 
    * @return int
    * @param x,y
    */
    public int resta(int x, int y);

    /** 
    * multiplicaion method - to multiplicate two numbers 
    * @return int
    * @param x,y
    */
    public int multiplicacion(int x, int y);

    /** 
    * division method - to divide two numbers 
    * @return int
    * @param x,y
    */
    public int division(int x, int y);

    /** 
    * operar method - push or operate a number froma stack 
    * @return int
    * @param x
    */
    public int operar(AbstractStack x);//operar recibe un stack en formato postfix, y lo opera para devolver un entero que es igual al valor total de la operación
    
    /** 
    * decode method - reads operation from file.txt and save them in a stack 
    * @return String
    * @param a
    */
    public String decode(String a);//función que se encarga de leer el archivo y decodificar cada línea para realizar las operaciones de cada una
}