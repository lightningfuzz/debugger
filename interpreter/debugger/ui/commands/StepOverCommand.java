
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.StepOver;
import java.util.Vector;

/** Michael Santer
 *  908250517
 * 
 * StepOverCommand
 * 1) create a stepOver object
 * 2) set isSet to true, set the current line number, and set the current
 *    environmentRecord size
 * 3) set the DebugVM's step to this new stepOver object
 * 4) execute the DVM.
 *
 */
public class StepOverCommand extends Command{
    
    public StepOverCommand(){}
    
    @Override
    public void init(Vector<String> args) {}
    
    public void execute(DebuggerVirtualMachine vm){
        StepOver stepOver = new StepOver();
        stepOver.setIsSet(true);
        stepOver.setEnvironmentSize(vm.getEnvironmentSize());
        if(vm.getEnvironmentLine() == null)
            stepOver.setLine(0);
        else
            stepOver.setLine(vm.getEnvironmentLine());
        vm.setStep(stepOver);
        vm.executeProgram();
    }

}
