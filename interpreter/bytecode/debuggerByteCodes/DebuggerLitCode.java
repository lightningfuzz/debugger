
package interpreter.bytecode.debuggerByteCodes;
import interpreter.bytecode.*;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.*;

/** Michael Santer
 *  908250517
 * 
 * DebuggerLitCode
 * 1) performs all actions of it's superclass
 * 2) if an ID is present, add the variables id and offset to the current
 *    environmentRecord. 
 * 
 */
public class DebuggerLitCode extends LitCode {
    
    public DebuggerLitCode(){}
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        if(id != null){
            DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
            dvm.addEnvironmentVariable(id,dvm.getCurrRunstackOffset());
        }
    }
    
}
