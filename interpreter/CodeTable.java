/* Michael Santer
 * 908250517
 * 
 * 
 * CodeTable stores the names of each BytCode class.
 * This class only contains static variables and methods.
 * It's not intended to be instantiated. 
 * 
 */
package interpreter;
import java.util.*;

public class CodeTable {
    protected static HashMap<String, String> codes;
    
    // This class is not intended to have any instances constructed
    protected CodeTable(){}
    
    /**
     * 
     * @param String
     * @return a String containing a ByteCode class name
     */
    public static String get(String code){
        return codes.get(code);
    }
    
    /**
     * Initialize the HashMap to contain all ByteCode class names
     */
    public static void init(){
        codes = new HashMap<String, String>();
        codes.put("HALT", "HaltCode");
        codes.put("POP", "PopCode");
        codes.put("FALSEBRANCH", "FalseBranchCode");
        codes.put("GOTO", "GotoCode");
        codes.put("STORE", "StoreCode");
        codes.put("LOAD", "LoadCode");
        codes.put("LIT", "LitCode");
        codes.put("ARGS", "ArgsCode");
        codes.put("CALL", "CallCode");
        codes.put("RETURN", "ReturnCode");
        codes.put("BOP","BopCode");
        codes.put("READ", "ReadCode");
        codes.put("WRITE", "WriteCode");
        codes.put("LABEL", "LabelCode");
        codes.put("DUMP", "DumpCode");
    }
    
}
