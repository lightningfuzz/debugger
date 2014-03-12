
package interpreter.debugger;

/** Michael Santer
 * 908250517
 * 
 * StepOut triggers a break in execution when the current activation record
 * is popped from the stack.
 * 
 */
public class StepOut extends Step{
    
    public void checkBreak(DebuggerVirtualMachine vm){
        if(isSet && environmentSize > 1){
            if(vm.getEnvironmentSize() == environmentSize-1){
                vm.setIsBreakpoint(true);
                isSet = false;
            }
        }
    }  
}
