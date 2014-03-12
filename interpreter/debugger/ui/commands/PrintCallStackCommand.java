
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.Vector;

/** Michael Santer
 *  908250517
 * 
 * PrintCallStackCommand prints the stack of function calls. 
 * This String is formatted by a method in the debuggerVM
 * 
 */
public class PrintCallStackCommand extends Command{
    
    public PrintCallStackCommand(){}
    
    @Override
    public void init(Vector<String> args) {}
    
    public void execute(DebuggerVirtualMachine vm){
        System.out.print(vm.dumpEnvironmentStack());
    }

    
}
