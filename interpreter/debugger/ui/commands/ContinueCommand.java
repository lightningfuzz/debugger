
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.Vector;

/** Michael Santer
 *  908250517
 * 
 * ContinueCommand continues running program by calling the debugger VM's 
 * execute method.
 * 
 */
public class ContinueCommand extends Command{
    
    public ContinueCommand(){}
    
    @Override
    public void init(Vector<String> args) {}
    
    public void execute(DebuggerVirtualMachine vm){
        vm.executeProgram();
    }

    
}
