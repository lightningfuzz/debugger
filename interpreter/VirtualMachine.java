/* Michael Santer
 * 908250517
 * 
 * The VituralMachine executes the program.
 * Maintains the prgram counter (PC)
 * Requests the next bytcode from Program object
 * Excecutes the code
 * 
 */
package interpreter;
import interpreter.Program;
import interpreter.RunTimeStack;
import interpreter.bytecode.*;
import java.util.*;

public class VirtualMachine {
    protected RunTimeStack runStack;
    protected int pc;
    private Stack<Integer> returnAddrs;
    protected boolean isRunning;
    protected Program program;
    protected boolean dump;
    private int numOfArgs; // used by ArgsCode and CallCode for dumping purposes
    
    public VirtualMachine(Program prog){
        program = prog;
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        dump = false;
    }
    
    public void executeProgram(){
           
        while(isRunning){
            ByteCode code = program.getCode(pc);
            code.execute(this);
            //check for dump
            if(dump && (code.getClass() != DumpCode.class)){
                System.out.println(code.toString());
                runStack.dump();
            }
            pc++;
        }
    }

    public void setIsRunning(boolean b) {
        isRunning = b;
    }
    
    public boolean getIsRunning(){
        return isRunning;
    }
    
    public void setPc(int address) {
        pc = address;
    }
    
    public int getPc(){
        return pc;
    }
    
    public void setDump(boolean b) {
        dump = b;
    }
    
    public ByteCode getProgramByteCode(int i){
        return program.getCode(i);
    }
    /**
     * Saves current pc value into returnAddrs stack
     * @param address 
     */
    public void saveReturnAddrs(){
        returnAddrs.push(pc);
    }
    
    //The follwing are methods to help access the runStack
    public int popReturnAddrs() {
        return returnAddrs.pop();
    }
    
    public void newRunStackFrameAt(int offset) {
        runStack.newFrameAt(offset);
    }
    
    public void popFrameRunStack() {
        runStack.popFrame();
    }
    
    public int popRunStack() {
        return runStack.pop();
    } 

    public void pushRunStack(int value) {
        runStack.push(value);
    }

    public void loadRunStack(int offset) {
        runStack.load(offset);
    }

    public void storeRunStack(int offset) {
        runStack.store(offset);
    }

    public int peekRunStack() {
        return runStack.peek();
    }
    
    // the following are methods regarding arguments used to call a function.
    // these are mainly used by CallCode and ArgsCode for dumping purposes
    
    public int getNumOfArgs() {
        return numOfArgs;
    }

    public void setNumOfArgs(int numOfArgs) {
        this.numOfArgs = numOfArgs;
    }

    /**
     * Gets the arguments form the runtime stack used when calling a function.
     * The vector returned is used by the CallCode's toString method
     * @param numOfArgs
     * @return Vector args
     */
    public Vector<Integer> getArgsRunStack(int number) {
        return runStack.getArgs(number);
        
    }
    
}
