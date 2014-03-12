/* Michael Santer
 * 908250517
 * 
 * RunTimeStack maintains the stack of active freams.
 * When we call a function we'll push a new frame on the stack.
 * When we return from a function we'll pop the top frame.
 * 
 */
package interpreter;
import java.util.*;

public class RunTimeStack {
    private Stack<Integer> framePointers;
    private Vector<Integer> runStack;
    
    public RunTimeStack(){
        framePointers = new Stack<Integer>();
        framePointers.push(0);
        runStack = new Vector<Integer>();
    }
    
    public void dump(){
        //print one frame at a time, starting with bottom
        for(int i=1; i<framePointers.size(); i++){
            System.out.print("[");
            
            //print values from bottom to top of current frame
            int j;
            for(j=framePointers.get(i-1); j<framePointers.get(i)-1; j++){
                System.out.print(runStack.get(j) + ",");
            }
            System.out.print(runStack.get(j) + "]");
        }
        
        //print top frame
        System.out.print("[");
        int i;
        for(i=framePointers.peek(); i<runStack.size()-1; i++){
            System.out.print(runStack.get(i) + ",");
        }
        try{
            System.out.println(runStack.get(i) + "]");
        }
        catch(Exception e){
            System.out.println("]");
        }
    }
    
    public int peek(){
        return runStack.lastElement();
    }
    
    public int pop(){
        return runStack.remove(runStack.size()-1);
    }
    
    public int push(int i){
        runStack.add(i);
        return i;
    }
    
    public void newFrameAt(int offset){
        framePointers.push(runStack.size()-offset);
    }
    
    public void popFrame(){
        //remove runStack elements of current frame, while saving top element)
        int bottom = framePointers.peek();
        int top = runStack.size()-1;
        for (int i = bottom; i < top; i++){
            runStack.remove(bottom);
        }
        //remove top framePointer
        framePointers.pop();
    }
    
    /**
     * Pop the top of the stack; store the value in the offset
     * from the start of the frame
     * @param offset from start of frame
     * @return value stored
     * 
     */
    public int store(int offset){
        int value = pop();
        runStack.set(framePointers.peek()+offset, value);
        return value;
    }
    
    /**
     * Push the value in the slot which is offset from the start of the frame
     * onto the top of the stack.
     * @param offset
     * @return value loaded
     */
    public int load(int offset){
        int value = runStack.get(framePointers.peek() + offset);
        push(value);
        return value;
    }

    /**
     * Gets the top "numOfArgs" values from the runStack.
     * Starting with the first values that were pushed to the stack.
     * Used by CallCode's toString method for printing. 
     * @param numOfArgs
     * @return Vector args
     */
    public Vector<Integer> getArgs(int numOfArgs) {
        Vector<Integer> args = new Vector<Integer>();
        for(int i=numOfArgs; i>0; i--){
            args.add(runStack.get(runStack.size()-i));
        }
        
        return args;
        
    }
    
    /**
     * 
     * @return the index for the top of the current frame.
     */
    public int getTopOffset(){
        return (runStack.size()-1) - framePointers.peek();
    }
    
    public int getValue(int offset){
        return runStack.get(framePointers.peek()+offset);
    }
}
