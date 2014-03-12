
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.*;

/** Michael Santer
 *  908250517
 * 
 * ListBreakpointsCommand prints the line numbers of all lines that have
 * breakpoints set.
 * 
 */
public class ListBreakpointsCommand extends Command{
    private Vector<Integer> lines;
    
    public ListBreakpointsCommand(){}
    
    /**
     * Parses the strings saved in "args" vector to integers and saves to
     * "lines" vector
     * @param args 
     */
    public void init(Vector<String> args) {
    }
    
    public void execute(DebuggerVirtualMachine vm){
        System.out.print("Current Breakpoints:");
        for(int i=1; i<vm.getSourceSize(); i++){
            if(vm.checkIsBreakpoint(i)){
                System.out.print(" " + i);
            }
        }
        System.out.println();
    }

    
}
