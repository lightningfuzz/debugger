/* Michael Santer
 * 908250517
 * 
 * 
 * 
 */

package interpreter;

import interpreter.debugger.*;
import interpreter.debugger.ui.UserInterface;
import java.io.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * <pre>
 * 
 *  
 *   
 *     Interpreter class runs the interpreter:
 *     1. Perform all initializations
 *     2. Load the bytecodes from file
 *     3. Run the virtual machine
 *     
 *   
 *  
 * </pre>
 */
public class Interpreter {

	ByteCodeLoader bcl;
        Vector<ProgramLine> sourceCode;

	public Interpreter(String codeFile) {
		try {
			CodeTable.init();
			bcl = new ByteCodeLoader(codeFile);
		} catch (IOException e) {
			System.out.println("**** " + e);
		}
	}
        
        //constructor used for debug mode (-d)
        public Interpreter(String byteCodeFile, String sourceCodeFile) {
		try {
			DebuggerCodeTable.init();
			bcl = new ByteCodeLoader(byteCodeFile);
                        loadSourceCode(sourceCodeFile);
		} catch (IOException e) {
			System.out.println("**** " + e);
		}
	}

	void run() {
            try {
                Program program = bcl.loadCodes();
                VirtualMachine vm = new VirtualMachine(program);
                vm.executeProgram();
            } catch (IOException ex) {
                System.out.println("******" + ex);
            }
	}
        
        void runDebugger(){
            try {
                Program program = bcl.loadCodes();
                DebuggerVirtualMachine dvm = 
                        new DebuggerVirtualMachine(program, sourceCode);
                UserInterface ui = new UserInterface();
                ui.run(dvm);
                
            } catch (IOException ex) {
                System.out.println("******" + ex);
            }
        }

	public static void main(String args[]) {
		if (args.length == 0) {
			System.out.println("***Incorrect usage, try: java interpreter.Interpreter <file>");
			System.exit(1);
		}
                else if (args.length == 1){
                    (new Interpreter(args[0])).run();
                }
                
                // if -d is found, run in debug mode
                else if (args[0].equals("-d")){
                    String byteCodeFile = args[1] + ".x.cod";
                    String sourceCodeFile = args[1] + ".x";
                    (new Interpreter(byteCodeFile, sourceCodeFile)).runDebugger();
                } 
                
                else
                    System.out.println("***Incorrect usage!***");
	}
        
        /**
         * Load source code into a vector for use by the debugger vm.
         * @param source 
         */
        private void loadSourceCode(String source) throws IOException{
            sourceCode = new Vector<ProgramLine>();
            BufferedReader reader = new BufferedReader(new FileReader(source));
            
            //There is no zero line in sorce code. Initialize with blank line.
            //This way, the first line of code is at the Vector index 1. 
            sourceCode.add(new ProgramLine(null));
            
            /* Save each line of code to the coresponding index of the vector.
             * isBreakpoint is automatically set to false in ProgramLine constructor
             */
            while(reader.ready()){
                sourceCode.add(new ProgramLine(reader.readLine()));
            }
        }
}
