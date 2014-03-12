
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.Vector;

/** Michael Santer
 *  908250517
 * 
 * DisplayFunctionCommand prints the source code for the current function.
 * 
 */
public class DisplayFunctionCommand extends Command{
    
    public DisplayFunctionCommand(){}
    
    public void init(Vector<String> args) {}
    
    public void execute(DebuggerVirtualMachine vm){
        System.out.print(vm.functionToString());
    } 
}
