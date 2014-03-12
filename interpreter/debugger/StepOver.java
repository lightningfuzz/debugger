
package interpreter.debugger;

/** Michael Santer
 * 908250517
 * 
 * StepOver triggers a break in execution when a new line in the source code
 * is reached while still in the same function(environmentStack size hasnt changed)
 * or when the current activation record is popped. 
 * 
 */
public class StepOver extends Step{
    
    private int line;
    
    public void setLine(int line){
        this.line = line;
    }
    public void checkBreak(DebuggerVirtualMachine vm){
        if(isSet && vm.getEnvironmentFunction() != null){
            
            if(vm.getEnvironmentSize() == environmentSize-1){
                vm.setIsBreakpoint(true);
                isSet = false;
            }
            else if(vm.getEnvironmentSize() == environmentSize &&
                    vm.getEnvironmentLine() != line){
                vm.setIsBreakpoint(true);
                isSet = false;
            }
            
        }
    }  
}
