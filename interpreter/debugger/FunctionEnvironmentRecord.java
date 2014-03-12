package interpreter.debugger;

import java.util.*;

/** Michael Santer
 * 908250517
 * 
 *  The FunctionEnvironmentRecord contains information for an activated function.
 *  This data includes, a table of variables, function name, start line,
 *  end line, and current line.
 * 
 */
public class FunctionEnvironmentRecord {
    
    private SymbolTable symTab;
    private Integer startLine, endLine, currentLine;
    private String functionName;
    
    public FunctionEnvironmentRecord(){
        startLine = null; endLine = null;
        currentLine = null; functionName = null;
        symTab = null;
    }
    
    public void beginScope(){
        symTab = new SymbolTable();
        symTab.beginScope();
    }
    
    public void setFunction(String name, int start, int end){
        functionName = name;
        startLine = start;
        endLine = end;
    }
    
    public void setCurrentLine(int lineNumber){
        currentLine = lineNumber;
    }
    
    public void addVariable(String id, int value){
        symTab.put(id, value);
    }
    
    /**
     * Removes last "amount" (number) of variables added to the symbol table
     * @param amount 
     */
    public void popVariables(int amount){
        symTab.pop(amount);
    }
    
    public Integer getStartLine(){
        return startLine;
    }
    
    public int getEndLine(){
        return endLine;
    }
    
    public Integer getCurrentLine(){
        return currentLine;
    }
    
    public String getFuncName() {
        return functionName;
    }
    /**
     * Creates a HashMap of variables and their values from the symbolTable
     * @return HashMap variables
     */
    public HashMap<String, Integer> getVariables(){
        HashMap<String, Integer> variables = new HashMap<String, Integer>();
        Set<String> ids = symTab.keys();
        for(String id : ids){
            variables.put(id, (Integer)symTab.get(id));
        }
        return variables;
    }
    
    /**
     * Used to print the FunctionEnvironmentRecord.
     * Format <variables,startLine,endLine,FuncName,currentLine>
     * @return 
     */
    public String toString(){
        String str = "(";
        
        if(symTab == null)
            str += "-,";
        else
            str += symTab + ",";
        
        if(functionName == null)
            str += "-,";
        else
            str += functionName + ",";
        
        if(startLine == null)
            str += "-,";
        else
            str += startLine + ",";
        
        if(endLine == null)
            str += "-,";
        else
            str += endLine + ",";
        
        if(currentLine == null)
            str += "-)";
        else
            str += currentLine + ")";
        
        return str;
    }
    
    
    /*
     * TEST CASE
     */
    public static void main(String args[]){
        FunctionEnvironmentRecord fer = new FunctionEnvironmentRecord();
        System.out.println("Initialize \t\t" + fer);
        fer.beginScope();
        System.out.println("BS \t\t\t" + fer);
        fer.setFunction("g", 1, 20);
        System.out.println("Function g 1 20 \t" + fer);
        fer.setCurrentLine(5);
        System.out.println("Line 5 \t\t\t" + fer);
        fer.addVariable("a", 4);
        System.out.println("Enter a 4 \t\t" + fer);
        fer.addVariable("b", 2);
        System.out.println("Enter b 2 \t\t" + fer);
        fer.addVariable("c", 7);
        System.out.println("Enter c 7 \t\t" + fer);
        fer.addVariable("a", 1);
        System.out.println("Enter a 1 \t\t" + fer);
        fer.popVariables(2);
        System.out.println("Pop 2 \t\t\t" + fer);
        fer.popVariables(1);
        System.out.println("Pop 1 \t\t\t" + fer);
        fer.popVariables(1);
        System.out.println("Pop 1 \t\t\t" + fer);
    }

}

