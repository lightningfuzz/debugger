/* Michael Santer
 * 908250517
 */
package interpreter.debugger;
import interpreter.CodeTable;

/**
 * 
 * DebuggerCodeTable calls the init method of it's super class to initialize
 * the same bytecodes used by the interpreter, and then initializes the set
 * of bytecodes only used in debug mode. 
 * 
 */
public class DebuggerCodeTable extends CodeTable{
    
    private DebuggerCodeTable(){}
    
    
    public static void init(){
        
        //initialize the usual ByteCodes
        CodeTable.init();
        
        // add new debugger ByteCodes
        codes.put("LINE", "LineCode");
        codes.put("FUNCTION", "FunctionCode");
        codes.put("FORMAL", "FormalCode");
        
        //add overwritten debugger ByteCodes
        codes.put("POP", "DebuggerPopCode");
        codes.put("LIT", "DebuggerLitCode");
        codes.put("RETURN", "DebuggerReturnCode");
        codes.put("CALL", "DebuggerCallCode");
    }
}
