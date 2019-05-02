import evaluators.ASTManager;
import evaluators.ast.AST;
import evaluators.ast.ASTException;
import evaluators.scopeevaluation.TypeCheckException;
import evaluators.scopeevaluation.table.TableException;
import generated.whileGrammarLexer;
import generated.whileGrammarParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.antlr.v4.runtime.CharStreams.fromFileName;

public class Main {
    final private static String directory = System.getProperty("user.dir") + "\\iofiles\\",
    indirectory = directory + "inputs\\",
    outdirectory = directory + "outputs\\";
    final static int SAMPLEA = 1, SAMPLEB = 2, SAMPLEC = 3, SAMPLED = 4, CUSTOM = 5;
    public static void main(String[] args) throws IOException {
        //Runs all sample code
        for(int i = 1; i <= 4; i++){run(i);}
        //Runs testing
        //run(CUSTOM);


    }

    /**
     * run method takes an input. Based on the input it evaluates a specific .txt file.
     *
     * Lexes, tokenises and parses the .txt input into an ANTLR parsetree.
     *
     * AST is built from parsetree. AST is static type checked. AST is converted to python code.
     *
     * Python code is output to [thisdirectory]/iofiles/outputs/[ouputfilename].py
     *
     * If .py file is empty then an error was encountered. Error will be printed in [outputfilename].txt
     */
    private static void run(int i) throws IOException{
        String inputFileName, outputFileName;
        switch(i){
            case SAMPLEA :
                inputFileName = "sampleA.txt"; outputFileName = "fibonnaci";
                break;
            case SAMPLEB :
                inputFileName = "sampleB.txt"; outputFileName = "factorial";
                break;
            case SAMPLEC :
                inputFileName = "sampleC.txt"; outputFileName = "odd_even";
                break;
            case SAMPLED :
                inputFileName = "sampleD.txt"; outputFileName = "custom_demo";
                break;
            default:
                inputFileName = "testInput.txt"; outputFileName = "aaatest";
        }

        CharStream cs = fromFileName(indirectory + inputFileName);
        System.out.println("Input stream:\n"+cs+"\n\n");
        whileGrammarLexer lexer = new whileGrammarLexer(cs);
        System.out.println("Creating common token stream...");
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        System.out.println("Generating grammar parser...");
        whileGrammarParser dp = new whileGrammarParser(tokens);
        System.out.println("Parser generated.");

        File pythonOutput = new File(outdirectory + outputFileName + ".py"),
                textOutput = new File(outdirectory + outputFileName + ".txt");

        if(pythonOutput.createNewFile())
            System.out.println("Creating new file structure for python output...");
        if(textOutput.createNewFile())
            System.out.println("Creating new file structure for text output...");

        BufferedWriter pyWriter = new BufferedWriter(new FileWriter(pythonOutput)),
                textWriter = new BufferedWriter(new FileWriter(textOutput));


        AST ast;
        String pycode = "";
        try{
            ast = ASTManager.buildAST(dp.program());

            ASTManager manager = new ASTManager(ast);
            System.out.println("AST as string: " + manager.getTree() + "\n\n");

            try {
                manager.checkType();
                pycode = manager.generatePythonCode();
                pyWriter.write(pycode);
                pyWriter.flush();
                textWriter.write(pycode);
                textWriter.flush();
            }
            catch (TypeCheckException | TableException exc){
                String excstr = "Type error: " + exc.getMessage();
                System.out.println((char)27 + "[31m" + excstr);
                textWriter.write(excstr);
                textWriter.flush();
                pyWriter.write("");
                pyWriter.flush();
            }
            //Prints generated python code
            System.out.println(pycode);
        }
        catch (ASTException astexc){
            String excstr = "Error encountered when trying to built AST: " + astexc.getMessage();
            System.out.println((char)27 + "[31m" + excstr);
            textWriter.write(excstr);
            textWriter.flush();
        }

    }
}
