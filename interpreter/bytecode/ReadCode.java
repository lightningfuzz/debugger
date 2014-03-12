/* Michael Santer
 * 908250517
 * 
 * ReadCode prompts the user for input with a '$'.
 * It then reads input and pushes it to the top of the stack. 
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class ReadCode extends ByteCode {
    
    public ReadCode(){}
    
    public void init(Vector<String> args){
        //ReadCode has no arguments
    }
    
    public void execute(VirtualMachine vm){
        Scanner in = new Scanner(System.in);
        int value;
        System.out.print("$ ");
        value = in.nextInt();
        vm.pushRunStack(value);
    }
    
    public String toString(){
        return ("READ");
    }
}
