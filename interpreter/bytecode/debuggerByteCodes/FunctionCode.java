
package interpreter.bytecode.debuggerByteCodes;
import interpreter.bytecode.*;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.*;

/** Michael Santer
 *  908250517
 * 
 * FunctionCode 
 * 1) sets the function information for the current 
 *    environmentRecord (startLine, endLine, and function name)
 * 2) If tracing is enabled, saves the function data to a string inside
 *    the trace object, and turns on the "pause" flag in the DVM so that 
 *    control returns to the UI to print the trace data. 
 * 
 */
public class FunctionCode extends ByteCode {
    String funcName;
    int startLine;
    int endLine;
    
    public FunctionCode(){}
    
    public void init(Vector<String> args){
        funcName = args.get(0);
        startLine = Integer.parseInt(args.get(1));
        endLine = Integer.parseInt(args.get(2));
    }
    
    public void execute(VirtualMachine vm){
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.setEnvironmentFunction(funcName, startLine, endLine);
        if(dvm.isTraceSet())
            trace(dvm);
        
    }
    
    public String toString(){
        return "FUNCTION " + funcName + " " + startLine + " " + endLine;
    }
    
    private void trace(DebuggerVirtualMachine dvm){
        String data = "";

        //create proper indenting for stack level
        for(int i=1; i<dvm.getEnvironmentSize(); i++)
            data += " ";

        //save function name
        StringTokenizer st = new StringTokenizer(funcName);
        data += st.nextToken("<") + "(";

        //save passed arguements
        int numOfArgs = dvm.getNumOfArgs();
        Vector args = dvm.getArgsRunStack(numOfArgs);
        if(numOfArgs > 0){
            data += args.get(0);
            for(int i=1; i<args.size(); i++){
                data += "," + args.get(i);
            } 
        }
        data += ")";
        dvm.setTraceData(data);

        // Pause is set to true so that the UI resumes control and
        // can print the trace. 
        dvm.setPause(true);
    }
}
