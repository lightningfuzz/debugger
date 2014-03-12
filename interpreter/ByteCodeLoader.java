/* Michael Santer
 * 908250517
 * 
 * Reads bytecodes from a file and uses the CodeTable to create instances of 
 * the ByteCodes to be saved into a Program object. 
 * 
 */
package interpreter;
import java.io.*;
import java.util.*;
import interpreter.bytecode.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ByteCodeLoader {
    
    private BufferedReader source;
    
    public ByteCodeLoader(String programFile) throws IOException{
        //System.out.println("Source file: "+sourceFile);
    	//System.out.println("user.dir: " + System.getProperty("user.dir"));
        source = new BufferedReader(new FileReader(programFile));
    }
    
    public Program loadCodes() throws IOException{
        Program program = new Program();

        //while there is more code to read
        while(source.ready()){
            //read first line of file
            StringTokenizer st = new StringTokenizer(source.readLine());
        
            //get ByteCode class name from first token
            String codeClass = CodeTable.get(st.nextToken());
            
            try{
                // create new ByteCode
                ByteCode bytecode = null;
                try{
                    bytecode = (ByteCode)(Class.forName("interpreter.bytecode." + codeClass).newInstance());
                }
                catch(Exception e){
                    bytecode = (ByteCode)(Class.forName("interpreter.bytecode."
                            + "debuggerByteCodes." + codeClass).newInstance());
                }
                Vector<String> args = new Vector<String>();
                try{
                    // record any arguments into a vector
                    while(true){
                        args.add(st.nextToken());
                    }
                }
                catch(NoSuchElementException e){}
                
                bytecode.init(args);
                program.addCode(bytecode);
            }
            catch(Exception e){System.out.println("******" + e);}
            
        }
        
        program.resolveAddress();
        return program;
    }
}
