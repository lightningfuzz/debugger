
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.Vector;

/** Michael Santer
 *  908250517
 * 
 * QuitCommand sets the dubuggerVM's isRunning to false.
 * This breaks us out of the while loop in the UserInterface "run" method.
 * 
 */
public class QuitCommand extends Command{
    
    public QuitCommand(){}
    
    public void init(Vector<String> args) {}
    
    public void execute(DebuggerVirtualMachine vm){
        vm.setIsRunning(false);
        System.out.println("\n****Quiting Execution****\n");
    }

    
}
