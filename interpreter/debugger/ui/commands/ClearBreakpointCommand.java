
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.Vector;

/** Michael Santer
 *  908250517
 * 
 * ClearBreakpointCommand
 * 1) is initialized with a vector of line numbers that are parsed to ints
 * 2) set's the breakpoint flag for each line number to false
 * 3) prints a confirmation.
 * 
 */

public class ClearBreakpointCommand extends Command{
    private Vector<Integer> lines;
    
    public ClearBreakpointCommand(){}
    
    /**
     * Parses the strings saved in "args" vector to integers and saves to
     * "lines" vector
     * @param args 
     */
    public void init(Vector<String> args) {  
        lines = new Vector<Integer>();
        for(int i=0; i<args.size(); i++){
            lines.add(Integer.parseInt(args.get(i)));
        }
    }
    
    public void execute(DebuggerVirtualMachine vm){
        Vector<Integer> validLines = new Vector<Integer>();
        Vector<Integer> invalidLines = new Vector<Integer>();
       
        //clear breakpoints
        for(int line : lines){
            if(vm.checkValidBreakpoint(line)){
                vm.changeBreakPoint(line, false);
                validLines.add(line);
            }
            else
                invalidLines.add(line);
        }
        
        //print confirmation
        if(validLines.size() > 0){
            System.out.print("Breakpoint cleared:");
            for(int line : validLines){
                System.out.print(" " + line);
            }
            System.out.println();
        }
        if(invalidLines.size() > 0){
            System.out.print("Invalid line:");
            for(int line : invalidLines){
                System.out.print(" " + line);
            }
            System.out.println();
        }
    }

    
}
