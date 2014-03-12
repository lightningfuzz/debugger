
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.HashMap;
import java.util.Vector;

/** Michael Santer
 *  908250517
 * 
 * DisplayVariablesCommand prints all of the current local variables.
 * Format "id:value"
 * 
 */
public class DisplayVariablesCommand extends Command{
    
    public DisplayVariablesCommand(){}
    
    public void init(Vector<String> args) {}
    
    public void execute(DebuggerVirtualMachine vm){
        HashMap<String,Integer> variables = vm.getEnvironmentVariables();
        
        if(variables.isEmpty())
            System.out.print("No local variables to display");
        
        for(String key : variables.keySet()){
            System.out.print(key + ":" + variables.get(key) + "\t");
        }
        System.out.println();
    }

    
}
