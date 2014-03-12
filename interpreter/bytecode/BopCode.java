/* Michael Santer
 * 908250517
 * 
 * BopCode takes one argument to perform a binary operation.
 * Pops top two levels of stack and performs indicated operation.
 * Operators (possible arguments) are + - / * == != <= > >= < | &
 * | and & are logical operators, not bit operators.
 * Lower level is the first operand, top level is the second operand. 
 * 
 */
package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.*;

public class BopCode extends ByteCode {
    
    private String op;
    private int operand1, operand2;
    
    public BopCode(){}
    
    public void init(Vector<String> args){
        op = args.get(0);
    }
    
    public void execute(VirtualMachine vm){
        operand2 = vm.popRunStack();
        operand1 = vm.popRunStack();
        
        if(op.equals("+"))
            vm.pushRunStack(operand1+operand2);
        
        if(op.equals("-"))
            vm.pushRunStack(operand1-operand2);
        
        if(op.equals("/"))
            vm.pushRunStack(operand1/operand2);
        
        if(op.equals("*"))
            vm.pushRunStack(operand1*operand2);
        
        if(op.equals("==")){
            if(operand1==operand2)
                vm.pushRunStack(1);
            else
                vm.pushRunStack(0);
        }
        
        if(op.equals("!=")){
            if(operand1!=operand2)
                vm.pushRunStack(1);
            else
                vm.pushRunStack(0);
        }
        
        if(op.equals("<=")){
            if(operand1<=operand2)
                vm.pushRunStack(1);
            else
                vm.pushRunStack(0);
        }
        
        if(op.equals("<")){
            if(operand1<operand2)
                vm.pushRunStack(1);
            else
                vm.pushRunStack(0);
        }
        
        if(op.equals(">=")){
            if(operand1>=operand2)
                vm.pushRunStack(1);
            else
                vm.pushRunStack(0);
        }
        
        if(op.equals(">")){
            if(operand1>operand2)
                vm.pushRunStack(1);
            else
                vm.pushRunStack(0);
        }
        
        if(op.equals("|")){
            if(operand1==1 || operand2==1)
                vm.pushRunStack(1);
            else
                vm.pushRunStack(0);
        }
        
        if(op.equals("&")){
            if(operand1==1 && operand2==1)
                vm.pushRunStack(1);
            else
                vm.pushRunStack(0);
        }
        
        
    }
    
    public String toString(){
        return ("BOP " + op + " \t" + operand1 + op + operand2);
    }
}
