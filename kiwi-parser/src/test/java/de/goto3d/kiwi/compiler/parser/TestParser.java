package de.goto3d.kiwi.compiler.parser;

import de.goto3d.kiwi.compiler.SymbolTable;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.visitors.SerializeVisitor;
import de.goto3d.kiwi.compiler.codegenerator.CodeGeneratorVisitor;
import de.goto3d.kiwi.compiler.parser.visitors.DeclarationVisitor;
import de.goto3d.kiwi.compiler.parser.visitors.InsertMissingTypesVisitor;
import de.goto3d.kiwi.compiler.parser.visitors.TypeConversionVisitor;
import de.goto3d.kiwi.compiler.parser.visitors.ValidationVisitor;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 17.01.13
 * Time: 09:04
 */
public class TestParser {

    public static void main(String[] args) {

        KiwiParser parser = new KiwiParser();
        AstNode rootNode = parser.parse(
                new BufferedReader(new InputStreamReader(TestParser.class.getResourceAsStream("/test2.kiwi")))
        );

        // generate symbol table
        DeclarationVisitor declarationVisitor = new DeclarationVisitor();
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

        Visitor visitor = new SerializeVisitor();
        Object result = rootNode.accept(visitor);
        System.out.println(result.toString());

        CodeGeneratorVisitor codeGeneratorVisitor = new CodeGeneratorVisitor("huhu", symbolTable);
        rootNode.accept(codeGeneratorVisitor);
        codeGeneratorVisitor.dump();
    }
}
