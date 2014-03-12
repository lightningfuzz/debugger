
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.*;

/** Michael Santer
 *  908250517
 * 
 * SetBreakpointCommand
 * 1) is initialized with a vector of line numbers that are parsed to ints
 * 2) checks if each line number is allowed to have a breakpoint and sets the 
 *    breakpoint flag to true
 * 3) prints a confirmation of which lines were able or not able to have
 *    breakpoints set.
 * 
 */
public class SetBreakpointCommand extends Command{
    private Vector<Integer> lines;
    
    public SetBreakpointCommand(){}
    
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
       
        //set breakpoints
        for(int i=0; i<lines.size();i++){
            int line = lines.get(i);
            if(vm.checkValidBreakpoint(line)){
                vm.changeBreakPoint(line, true);
                validLines.add(line);
            }
            else
                invalidLines.add(line);
        }
        
        //print confirmation
        if(validLines.size() > 0){
            System.out.print("Breakpoint set:");
            for(int line : validLines){
                System.out.print(" " + line);
            }
            System.out.println();
        }
        if(invalidLines.size() > 0){
            System.out.print("Invalid breakpoint:");
            for(int line : invalidLines){
                System.out.print(" " + line);
            }
            System.out.println();
        }
        
    }

    
}
