/* Michael Santer
 * 908250517
 * 
 * Program holds bytecodes loaded from byteCodeLoader.
 * Contains a method to resolve symbolic addresses. 
 * 
 */
package interpreter;
import java.util.*;
import interpreter.bytecode.*;
import interpreter.bytecode.debuggerByteCodes.DebuggerCallCode;
import interpreter.bytecode.debuggerByteCodes.LineCode;

public class Program {
    
    private ArrayList<ByteCode> byteCodes;
    
    public Program(){
        byteCodes = new ArrayList<ByteCode>();
    }
    
    public void addCode(ByteCode code){
        byteCodes.add(code);
    }
    
    public ByteCode getCode(int i){
        return byteCodes.get(i);
    }
    
    /**
     * Resolves symbolic addresses (ex: f<<2>>) into numerical addresses (ex: 1) 
     * for easy branching. 
     */
    public void resolveAddress(){
        ArrayList<LabelCode> labels = new ArrayList<LabelCode>();
        
        //find all labels, save to array, and set address.
        for(int i=0; i < byteCodes.size(); i++){
            if((byteCodes.get(i)).getClass() == LabelCode.class){
                labels.add((LabelCode)byteCodes.get(i));
                (labels.get(labels.size()-1)).setAddress(i);
            }
        }
        
        //find instances of branches
        for(int i=0; i < byteCodes.size(); i++){
            ByteCode bc = byteCodes.get(i);
            
            //find FALSEBRANCH
            if(bc.getClass() == FalseBranchCode.class){
                FalseBranchCode fbc = (FalseBranchCode)bc;
                //resolve address by comparing to labels array
                for(int j=0; j<labels.size(); j++){
                    if((fbc.getLabel()).equals(labels.get(j).getLabel())){
                        fbc.setAddress(labels.get(j).getAddress());
                        break;
                    }
                }
            }
            
            //find GOTO and resolve address
            if(bc.getClass() == GotoCode.class){
                GotoCode gtc = (GotoCode)bc;
                //resolve address by comparing to labels array
                for(int j=0; j<labels.size(); j++){
                    if((gtc.getLabel()).equals(labels.get(j).getLabel())){
                        gtc.setAddress(labels.get(j).getAddress());
                        break;
                    }
                }
            }
            
            //find CALL and resolve address
            if(bc.getClass() == CallCode.class || 
                    bc.getClass() == DebuggerCallCode.class){
                CallCode cc = (CallCode)bc;
                //resolve address by comparing to labels array
                for(int j=0; j<labels.size(); j++){
                    if((cc.getLabel()).equals(labels.get(j).getLabel())){
                        cc.setAddress(labels.get(j).getAddress());
                        break;
                    }
                }
            }
        }
    }
    
    public Vector getLineNumbers(){
        Vector<Integer> lines = new Vector<Integer>();
        for(int i=0; i<byteCodes.size(); i++){
            ByteCode bc = byteCodes.get(i);
            if(bc.getClass() == LineCode.class){
                LineCode lc = (LineCode)bc;
                lines.add(lc.getLineNumber());
            }
        }
        return lines;
    }
    
    
}
