package de.goto3d.kiwi.compiler.parser;

import com.creativewidgetworks.goldparser.engine.ParserException;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import de.goto3d.kiwi.compiler.ast.AstNode;

import java.io.Reader;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 17.01.13
 * Time: 08:57
 */
public class KiwiParser {

    public AstNode parse(Reader reader) {

        // Use the compiled grammar file inside the jar
        GOLDParser parser = new GOLDParser(
                getClass().getResourceAsStream("/Kiwi.egt"), // compiled grammar table
                getClass().getPackage().getName(),           // rule handler package
                true                                         // trim reductions
        );

        // Parse the source statements to see if it is syntactically correct
        boolean parsedWithoutError = parser.parseSourceStatements(reader);

        // Either execute the code or print any error message
        if (parsedWithoutError) {
            return ((ReductionBase)parser.getCurrentReduction()).astNode;
        }

        throw new ParserException(parser.getErrorMessage());
    }
}
