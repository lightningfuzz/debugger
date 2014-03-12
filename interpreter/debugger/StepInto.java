
package interpreter.debugger;

/** Michael Santer
 * 908250517
 * 
 * StepInto triggers a break in execution when a new line in the source code
 * is reached, or when a new function is activated.
 * 
 */
public class StepInto extends Step{
    
    private int line;
    
    public void setLine(int line){
        this.line = line;
    }
    public void checkBreak(DebuggerVirtualMachine vm){
        if(isSet && vm.getEnvironmentFunction() != null){
            
            if(vm.getEnvironmentLine() != line){
                vm.setIsBreakpoint(true);
                isSet = false;
            }
            if(vm.getEnvironmentSize() == environmentSize+1){
                vm.setIsBreakpoint(true);
                isSet = false;
            }
        }
    }  
}
