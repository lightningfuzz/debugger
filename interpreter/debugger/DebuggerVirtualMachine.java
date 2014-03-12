
package interpreter.debugger;
import interpreter.*;
import interpreter.bytecode.*;
import interpreter.bytecode.debuggerByteCodes.*;
import java.util.*;

/** Michael Santer
 * 908250517
 * 
 * The DebuggerVM executes bytecodes until certain events (breakpoints,
 * stepping, etc) cause it to stop.
 * It contains a stack of functionEnvironmentRecords, a vector that holds 
 * the separate lines of source code, and data inherited from VirtualMachine. 
 * There are then, several methods to assist in accessing this data. 
 * 
 */
public class DebuggerVirtualMachine extends VirtualMachine {
    
    private Vector<ProgramLine> sourceCode;
    private Stack<FunctionEnvironmentRecord> environmentStack;
    private Vector<Integer> validLines; //used for breakpoints
    private boolean isBreakpoint;
    private boolean pause;
    private Trace trace;
    private Step step;

    
    
    public DebuggerVirtualMachine(Program prog, Vector<ProgramLine> source){
        super(prog);
        sourceCode = source;
        environmentStack = new Stack<FunctionEnvironmentRecord>();
        newEnvironmentRecord();
        isBreakpoint = false;
        pause = false;
        trace = new Trace();
        validLines = program.getLineNumbers();//fills vector with line numbers
                                              //that can be used for breakpoints
        step = null; 
    }
    
    @Override
    public void executeProgram(){
        while(!isBreakpoint && isRunning && !pause){
            
            //exectue next bytecode
            ByteCode code = program.getCode(pc);
            code.execute(this);
            
            //check for stepping
            if(step != null){
                step.checkBreak(this);
            }
            pc++;
        }
    }
    
    // methods for Breakpoints
    
    public void changeBreakPoint(int line, boolean state){
        sourceCode.get(line).setIsBreakptSet(state);
    }
   
    /**
     * Checks if given line number exists in the vector "validLines".
     * This tells us if it is okay to set a breakpoint at that line.
     * @param int line
     * @return true if line is in the vector. false otherwise.
     * 
     */
    public boolean checkValidBreakpoint(int line) {
        if(line < 1) return false;
        
        for(int i=0; i<validLines.size(); i++){
            if(line == validLines.get(i))
                return true;
        }
        return false;
    }
    
    public boolean getIsBreakpoint(){
        return isBreakpoint;
    }
    
    public void setIsBreakpoint(boolean state) {
        isBreakpoint = state;
    }
    
    /**
     * Checks if the given line number has a breakpoint
     * @param line
     * @return true if the programLine's breakpoint is true.
     */
    public boolean checkIsBreakpoint(int line) {
        if (line > 0 && sourceCode.get(line).getIsBreakptSet()){
            return true;
        }
        else
            return false;
    }
    
    
    // The following methods are for making changes to the TOP 
    // FunctionEnvironmentRecord
    
    public void popEnvironmentStack(){
        environmentStack.pop();
    }
    
    public void newEnvironmentRecord(){
        FunctionEnvironmentRecord fer = new FunctionEnvironmentRecord();
        fer.beginScope();
        environmentStack.push(fer);
    }
    
    /**
     * Set currentLine of the top environmentRecord.
     * @param line 
     */
    public void setEnvironmentLine(int line){
        FunctionEnvironmentRecord fer = environmentStack.peek();
        fer.setCurrentLine(line);
    }
    
    public Integer getEnvironmentLine(){
        FunctionEnvironmentRecord fer = environmentStack.peek();
        return fer.getCurrentLine();
    }
    
    public void addEnvironmentVariable(String id, int offset){
        environmentStack.peek().addVariable(id, offset);
    }
    
    public void popEnvironmentVariables(int amount) {
        environmentStack.peek().popVariables(amount);
    }
    
    public void setEnvironmentFunction(String name, int start, int end){
        environmentStack.peek().setFunction(name, start, end);
    }
    
    /**
     * 
     * @return the name of the function only
     */
    public String getEnvironmentFunction(){
        return environmentStack.peek().getFuncName();
    }
    
    
    
    // The following are methods for the entire EnvironmentRecord stack
    
    public int getEnvironmentSize() {
        return environmentStack.size();
    }
    
    /**
     * Creates a string representing every record in the stack. 
     * The format for each entry is "functionName(currentlineNumber)"
     * @return String
     */
    public String dumpEnvironmentStack() {
        Vector<FunctionEnvironmentRecord> envStack = environmentStack;
        StringTokenizer st;
        String str = "";
        
        for(int i=envStack.size()-1; i>=0; i--){
            FunctionEnvironmentRecord fer = envStack.get(i);
            if(fer.getCurrentLine() != null && fer.getCurrentLine()>0){
                st = new StringTokenizer(fer.getFuncName());
                str += st.nextToken("<");
                str += ": " + fer.getCurrentLine() + "\n";
            }
        }
        return str;
    }
    
    
    
    // The following are methods to access the Trace object
    
    public boolean isTraceSet() {
        return trace.isSet();
    }

    public void setIsTraceSet(boolean state) {
        trace.setIsSet(state);
        trace.setEnvironmentSize(environmentStack.size());
    }
    
    public String getTraceData(){
        return trace.getData();
    }
    
    public void setTraceData(String data){
        trace.setData(data);
    }

    
    // other methods
    
    
    public boolean isPause() {
        return pause;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }
    
    /**
     * Used by DebuggerLitCode
     * @return the offset of the item at the top of the runstack. 
     */
    public int getCurrRunstackOffset(){
        return runStack.getTopOffset();
    }
    
    public void setStep(Step step){
        this.step = step;
    }
    
    /**
     * 
     * @return the size of sourceCode vector. i.e. the number of lines in 
     * the source code. 
     */
    public int getSourceSize(){
        return sourceCode.size();
    }

    /**
     * 
     * @return String containing source code of the current activated function
     */
    public String functionToString() {
        int start, end, currentLine;
        String str = "";
        
        if(environmentStack.peek().getStartLine() == null){
            start = 1;
            end = sourceCode.size()-1;
            currentLine = 0;
        }
        else{
            start = environmentStack.peek().getStartLine();
            end = environmentStack.peek().getEndLine();
            currentLine = environmentStack.peek().getCurrentLine();
        }
        
        if(start == -1){
            str += "Intrinsic Function: " + 
                    environmentStack.peek().getFuncName();
        }
        else{
            for(int i = start; i<=end; i++){
                //indicate that line is a breakpoint with '*'
                if(sourceCode.get(i).getIsBreakptSet())
                    str += "*";
                str += i + ". " + sourceCode.get(i).toString();
                //indicate that line is the current line with "<-----"
                if(i == currentLine){
                    str += " <-------";
                }
                str += "\n";
            }
        }
        return str;
            
    }
    
    /**
     * 1. Gets a HashMap of variables and their offsets from the environment record.
     * 2. Use runtime stack to replace the offsets with literal values. 
     * 
     * @return HashMap<String,Integer> variables
     */
    public HashMap<String,Integer> getEnvironmentVariables(){
        HashMap<String,Integer> variables = environmentStack.peek().getVariables();
        for(String key: variables.keySet()){
            int offset = variables.get(key);
            int value = runStack.getValue(offset);
            variables.put(key, value);
        }
        
        return variables;
        
    }  
}