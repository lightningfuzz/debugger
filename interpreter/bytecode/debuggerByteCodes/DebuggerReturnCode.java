
package interpreter.bytecode.debuggerByteCodes;
import interpreter.bytecode.*;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.*;


/** Michael Santer
 *  908250517
 * 
 * DebuggerReturnCode
 * 1) performs all actions of its super class
 * 2) If tracing is enabled, saves the function name and its return value 
 *    to a string inside the trace object, and turns on the "pause" flag in 
 *    the DVM so that control returns to the UI to print the trace data. 
 * 
 */
public class DebuggerReturnCode extends ReturnCode {
    
    private String funcName;
    
    public DebuggerReturnCode(){}
    
    public void execute(VirtualMachine vm){
        super.execute(vm);
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        
        if(dvm.isTraceSet())
            trace(dvm);
        
        dvm.popEnvironmentStack();
    }
    
    private void trace(DebuggerVirtualMachine dvm){
        String data = "";

        //create proper indenting for stack level
        for(int i=1; i<dvm.getEnvironmentSize(); i++)
            data += " ";
        
        //save function name and returnValue to trace
        StringTokenizer st = new StringTokenizer(dvm.getEnvironmentFunction());
        funcName = st.nextToken("<");
        data += "exit " + funcName + ": " + returnValue; 
        dvm.setTraceData(data);

        // Pause is set to true so that the UI resumes control and
        // can print the trace. 
        dvm.setPause(true);
    }
}
