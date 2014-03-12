
package interpreter.bytecode.debuggerByteCodes;
import interpreter.bytecode.*;
import interpreter.VirtualMachine;
import interpreter.debugger.DebuggerVirtualMachine;
import java.util.*;


/** Michael Santer
 *  908250517
 * 
 * LineCode updates the current line number of the current environmentRecord.
 * 
 * 
 */
public class LineCode extends ByteCode {
    private int lineNumber;
    
    public LineCode(){}
    
    public void init(Vector<String> args){
        lineNumber = Integer.parseInt(args.get(0));
    }
    
    public void execute(VirtualMachine vm){
        DebuggerVirtualMachine dvm = (DebuggerVirtualMachine)vm;
        dvm.setEnvironmentLine(lineNumber);
        
        if(dvm.checkIsBreakpoint(lineNumber)){
            dvm.setIsBreakpoint(true);
        }
    }
    
    public String toString(){
        return "LINE " + lineNumber;
    }

    public int getLineNumber() {
        return lineNumber;
    }
}
