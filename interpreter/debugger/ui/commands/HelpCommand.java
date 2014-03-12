
package interpreter.debugger.ui.commands;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.Vector;

/** Michael Santer
 *  908250517
 * 
 * HelpCommand prints a list of commands that the user can enter, along with
 * a brief description.
 * 
 */
public class HelpCommand extends Command{
    
    public HelpCommand(){}
    
    public void init(Vector<String> args) {}
    
    public void execute(DebuggerVirtualMachine vm){
        System.out.println("\t\t Available Commands\n");
        System.out.println("'set' \t\t\t -Set breakpoints (ex: set 8 9 3).");
        System.out.println("'clear' \t\t -Clear breakpoints (ex: clear 8 9).");
        System.out.println("'list' \t\t\t -List current breakpoints.");
        System.out.println("'trace'<on><off> \t -Turn tracing on or off (ex: trace on).");
        System.out.println("'function' or 'func' \t -Display current function.");
        System.out.println("'stack' \t\t -Display call stack.");
        System.out.println("'variables' or 'var' \t -Display local variables and values.");
        System.out.println("'continue' or 'cont' \t -Continue execution of program.");
        System.out.println("'stepOut' or 'out' \t -Step out of the current function.");
        System.out.println("'stepInto' or 'in' \t -Step into function on current line.");
        System.out.println("'stepOver' or 'over' \t -Step over the next line.");
        System.out.println("'quit' or 'q' \t\t -Quit execution.");
        System.out.println("'help' or '?' \t\t -Display list of commands.");
    }
}
