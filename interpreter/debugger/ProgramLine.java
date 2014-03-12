
package interpreter.debugger;

/** Michael Santer
 * 908250517
 * 
 * ProgramLine contains a string (intended to be a line of source code)
 * and a boolean stating whether or not that line has a breakpoint set. 
 * 
 */
public class ProgramLine{
    private String sourceLine;
    private boolean isBreakptSet;
    
    public ProgramLine(String line){
        sourceLine = line;
        isBreakptSet = false;
    }
    
    public void setIsBreakptSet(boolean state){
        isBreakptSet = state;
    }
    
    public boolean getIsBreakptSet(){
        return isBreakptSet;
    }
    
    public String toString(){
        return sourceLine;
    }
    
}
