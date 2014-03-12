
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.*;

/** Michael Santer
 *  908250517
 * 
 * SetTraceCommand
 * 1) receives 1 argument that should contain the string "on" or "off"
 * 2) sets the DVM's trace appropriately
 * 3) prints confirmation 
 * 4) if no proper argument is given, prints the current trace status. 
 * 
 */
public class SetTraceCommand extends Command{
    
    private String state;
    
    public SetTraceCommand(){}
    
    public void init(Vector<String> args) {
        if(args.size() != 0)
            state = args.get(0).toLowerCase();
        else
            state = "";
    }
    
    public void execute(DebuggerVirtualMachine vm){
        //set trace
        if(state.equals("on"))
            vm.setIsTraceSet(true);
        else if(state.equals("off"))
            vm.setIsTraceSet(false);
        
        //print confirmation
        System.out.print("Trace is ");
        if(vm.isTraceSet())
            System.out.println("on.");
        else
            System.out.println("off.");
    }  
}
