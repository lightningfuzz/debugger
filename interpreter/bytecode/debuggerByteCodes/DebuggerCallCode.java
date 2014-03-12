
package interpreter.bytecode.debuggerByteCodes;
import interpreter.bytecode.*;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.*;

/** Michael Santer
 *  908250517
 * 
 * DebuggerCallCode
 * 1) Performs all tasks of its parent class
 * 2) creates a new FunctionEnvironmentRecord in the DVM.
 * 
 */
public class DebuggerCallCode extends CallCode {
    
    
    public DebuggerCallCode(){}
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.newEnvironmentRecord();
    }
    
    }
