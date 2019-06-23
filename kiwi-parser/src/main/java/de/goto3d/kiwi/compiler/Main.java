package de.goto3d.kiwi.compiler;

import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.visitors.SerializeVisitor;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.parser.KiwiParser;
import de.goto3d.kiwi.compiler.parser.visitors.DeclarationVisitor;
import de.goto3d.kiwi.compiler.parser.visitors.InsertMissingTypesVisitor;
import de.goto3d.kiwi.compiler.parser.visitors.TypeConversionVisitor;
import de.goto3d.kiwi.compiler.parser.visitors.ValidationVisitor;

import java.io.*;

/**
 * Created by gru.
 * Created on 22.06.2019 - 19:22.
 */
public class Main {

    public static void main(String[] args) throws IOException {

        KiwiParser parser   = new KiwiParser();
        AstNode rootNode    = parser.parse(new BufferedReader(new InputStreamReader(new FileInputStream(args[0]))));

        // generate symbol table
        DeclarationVisitor declarationVisitor   = new DeclarationVisitor();
        rootNode.accept(declarationVisitor);
        SymbolTable symbolTable = declarationVisitor.getSymbolTable();

        // fill in missing types
        rootNode.accept(new InsertMissingTypesVisitor(symbolTable));
        // add necessary type converters
        rootNode.accept(new TypeConversionVisitor(symbolTable));

        // run validation visitor
        ValidationVisitor validationVisitor = new ValidationVisitor(symbolTable);
        rootNode.accept(validationVisitor);
        if ( !validationVisitor.isValid() ) {
            System.exit(-1);
        }

        // generate LLVM bit code
        CodeGeneratorVisitor codeGeneratorVisitor = new CodeGeneratorVisitor("huhu", symbolTable);
        rootNode.accept(codeGeneratorVisitor);
        String llvmCode = codeGeneratorVisitor.print();

        String outFileName  = args.length > 1 ? args[1] : replaceExtension(args[0]);
        BufferedWriter out = new BufferedWriter(new FileWriter(outFileName));
        out.write(llvmCode);
        out.close();
    }

    private static String replaceExtension(String fileName) {
        int lastIdx = fileName.lastIndexOf('.');
        if ( lastIdx == -1 ) {
            return fileName;
        }
        return fileName.substring(0,lastIdx)+".ll";
    }
}
