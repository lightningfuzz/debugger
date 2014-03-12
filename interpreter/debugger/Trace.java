
package interpreter.debugger;

/** Michael Santer
 * 908250517
 * 
 * Trace contains information necessary for the debugger to perform the tracing
 * function.
 * It contains a boolean to indicate whether tracing is turned on, an integer 
 * that indicates the current environment size, and a string containing the data
 * that is needed to be displayed while tracing. 
 * 
 */

public class Trace {
    
    private boolean isSet;
    private int environmentSize;
    private String traceData;
    
    public Trace(){
        traceData = null;
    }
    
    public void setData(String data){
        traceData = data;
    }

    public void setIsSet(boolean isSet) {
        this.isSet = isSet;
    }
    
    public boolean isSet(){
        return isSet;
    }

    public void setEnvironmentSize(int environmentSize) {
        this.environmentSize = environmentSize;
    }
    
    public String getData(){
        return traceData;
    }
    
}
