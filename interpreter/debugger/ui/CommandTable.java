
package interpreter.debugger.ui;

import java.util.HashMap;

/** Michael Santer
 *  908250517
 * 
 * Contains a Hashmap of commands supported by the Debugger UI.
 * The key is what the user should enter, the value is the Class name. 
 * 
 */
public class CommandTable {
    
    private static HashMap<String,String> commands;
    
    private CommandTable() {}
    
    public static void init(){
        commands = new HashMap<String,String>();
        commands.put("help", "HelpCommand");
        commands.put("?", "HelpCommand");
        commands.put("set", "SetBreakpointCommand");
        commands.put("clear", "ClearBreakpointCommand");
        commands.put("list", "ListBreakpointsCommand");
        commands.put("function", "DisplayFunctionCommand");
        commands.put("func", "DisplayFunctionCommand");
        commands.put("continue", "ContinueCommand");
        commands.put("cont", "ContinueCommand");
        commands.put("quit", "QuitCommand");
        commands.put("q", "QuitCommand");
        commands.put("variables", "DisplayVariablesCommand");
        commands.put("var", "DisplayVariablesCommand");
        commands.put("stepout", "StepOutCommand");
        commands.put("out", "StepOutCommand");
        commands.put("stepinto", "StepIntoCommand");
        commands.put("in", "StepIntoCommand");
        commands.put("stepover", "StepOverCommand");
        commands.put("over", "StepOverCommand");
        commands.put("stack", "PrintCallStackCommand");
        commands.put("trace", "SetTraceCommand");
    }
    
    public static String get(String command){
        return commands.get(command);
    }
}
