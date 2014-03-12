
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.Vector;
/** Michael Santer
 *  908250517
 * 
 * Command is intended to be a parent to all other command classes.
 *
 */
public abstract class Command {
    
    public Command(){}
    
    public abstract void init(Vector<String> args);
    
    public abstract void execute(DebuggerVirtualMachine vm);
}
