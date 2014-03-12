
package interpreter.debugger;

/** Michael Santer
 * 908250517
 * 
 * Step is an abstract class that contains data and methods that all step 
 * objects should posses. 
 * 
 */
public abstract class Step {
    
    protected boolean isSet;
    protected int environmentSize;
    
    public Step(){}
    
    /**
     * 
     * Based on data from the debug VM, this method will check to
     * see if stepping is turned on, and if a break in execution needs to occur.
     * The criteria for whether a break is needed, will vary for each subclass.
     */
    public abstract void checkBreak(DebuggerVirtualMachine vm);

    public void setIsSet(boolean isSet) {
        this.isSet = isSet;
    }

    public void setEnvironmentSize(int environmentSize) {
        this.environmentSize = environmentSize;
    }
    
    
}
