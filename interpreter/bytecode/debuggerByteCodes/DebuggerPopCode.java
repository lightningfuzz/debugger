
package interpreter.bytecode.debuggerByteCodes;
import interpreter.bytecode.*;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.*;

/** Michael Santer
 *  908250517
 * 
 * DebuggerPopCode
 * 1) perform all actions of super class
 * 2) removes the given number of variables from the current environmentRecord.
 * 
 */
public class DebuggerPopCode extends PopCode {
    
    
    public DebuggerPopCode(){}
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.popEnvironmentVariables(numberOfPops);
    }
    
}
