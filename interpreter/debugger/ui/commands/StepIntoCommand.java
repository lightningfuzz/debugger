
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.StepInto;
import java.util.Vector;

/** Michael Santer
 *  908250517
 * 
 * StepIntoCommand
 * 1) create a stepInto object
 * 2) set isSet to true, and set the current environmentRecord size, and set
 *    the current line number
 * 3) set the DebugVM's step to this new stepInto object
 * 4) execute the DVM.
 *
 */

public class StepIntoCommand extends Command{
    
    public StepIntoCommand(){}
    
    @Override
    public void init(Vector<String> args) {}
    
    public void execute(DebuggerVirtualMachine vm){
        StepInto stepInto = new StepInto();
        stepInto.setIsSet(true);
        stepInto.setEnvironmentSize(vm.getEnvironmentSize());
        if(vm.getEnvironmentLine() == null)
            stepInto.setLine(0);
        else
            stepInto.setLine(vm.getEnvironmentLine());
        vm.setStep(stepInto);
        vm.executeProgram();
    }

}
