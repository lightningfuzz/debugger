
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.StepOut;
import java.util.Vector;

/** Michael Santer
 *  908250517
 * 
 * StepOutCommand
 * 1) create a stepOut object
 * 2) set isSet to true, and set the current environmentRecord size
 * 3) set the DebugVM's step to this new stepOut object
 * 4) execute the DVM.
 *
 */
public class StepOutCommand extends Command{
    
    public StepOutCommand(){}
    
    @Override
    public void init(Vector<String> args) {}
    
    public void execute(DebuggerVirtualMachine vm){
        StepOut stepOut = new StepOut();
        stepOut.setIsSet(true);
        stepOut.setEnvironmentSize(vm.getEnvironmentSize());
        vm.setStep(stepOut);
        vm.executeProgram();
    }

}
