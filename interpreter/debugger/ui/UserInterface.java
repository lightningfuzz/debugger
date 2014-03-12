
package interpreter.debugger.ui;
import interpreter.debugger.DebuggerVirtualMachine;
import interpreter.debugger.ui.commands.Command;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Vector;


/** Michael Santer
 *  908250517
 * 
 * The UserInterface controls interacting with the user(reading commands, 
 * and printing to the screen), executing the DebuggerVM, and executing user 
 * commands.
 * 
 * 
 */

public class UserInterface {
    
    public UserInterface() {
        CommandTable.init();
    }

    public void run(DebuggerVirtualMachine vm) {
        
        //initialize
        System.out.println("****Starting Debugger****\n");
        System.out.println(vm.functionToString());
        System.out.println("Type '?' for help.");
        Scanner in = new Scanner(System.in);
        StringTokenizer st;
     
        while(vm.getIsRunning()){
            String commandClass;
            Command command;
            
            // if VM is stopped but no user command is needed.
            // ex: trace printing
            if(vm.isPause()){
                if(vm.getTraceData() != null){
                    System.out.println(vm.getTraceData());
                    vm.setTraceData(null);
                }
                vm.setPause(false);
                vm.executeProgram();
            }
            
            // if VM is stopped and user command is needed.
            // ex: breakpoint, step, or waiting for another command 
            else{
                
                //if execution stopped due to a breakpoint,
                //print the source code, and set IsBreakpoint to false
                if(vm.getIsBreakpoint()){
                    System.out.println(vm.functionToString());
                    vm.setIsBreakpoint(false);
                    vm.setStep(null);
                }

                //prompt user to type
                System.out.print("\n> ");

                //get user input. first token is the command
                st = new StringTokenizer(in.nextLine());
                commandClass = st.nextToken().toLowerCase();
                commandClass = CommandTable.get(commandClass);
                command = getCommandObject(commandClass);

                //if the command is a valid command, continue to get
                //args, then execute the command. 
                if(command != null){
                    Vector<String> args = getArgs(st);
                    command.init(args);
                    System.out.println();
                    command.execute(vm);
                }
            }
        } 
    }

    /**
     * Creates an instance of a Command class based on a string containing the
     * name of the class. 
     * @param commandClass
     * @return Command object
     */
    private Command getCommandObject(String commandClass) {
        Command command = null;
        try {
            command = (Command)(Class.forName("interpreter.debugger.ui."
                        + "commands." + commandClass).newInstance());
        } catch (Exception ex) {
            System.out.println("****Invalid Command****");
            //System.out.println(ex);
        }
        return command;
    }
    
    /**
     * Parses a single string of user input into a vector of individual 
     * String arguments. 
     * @param st
     * @return 
     */
    private Vector<String> getArgs(StringTokenizer st){
        Vector<String> args = new Vector<String>();
        while(st.hasMoreTokens()){
            args.add(st.nextToken());
        }
        return args;
    }
}
