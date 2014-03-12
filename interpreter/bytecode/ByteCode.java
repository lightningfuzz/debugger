/* Michael Santer
 * 908250517
 * 
 * ByteCode is an abstract class. It is the parent to all other "Code" classes
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public abstract class ByteCode {
    
    public ByteCode(){}
    
    public abstract void init(Vector<String> args);
    
    public abstract void execute(VirtualMachine vm);
    
}
