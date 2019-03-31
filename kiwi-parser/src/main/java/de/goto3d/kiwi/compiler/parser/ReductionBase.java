package de.goto3d.kiwi.compiler.parser;

import com.creativewidgetworks.goldparser.engine.Position;
import com.creativewidgetworks.goldparser.engine.Reduction;
import com.creativewidgetworks.goldparser.parser.GOLDParser;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.SourcePosition;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 16.01.13
 * Time: 11:06
 */
public abstract class ReductionBase extends Reduction {

    protected AstNode astNode;

    public AstNode getAstNode() {
        return astNode;
    }

    protected SourcePosition convertPosition(final GOLDParser parser) {
        final Position position = parser.getCurrentPosition();
        return new SourcePosition(position.getLine(),position.getColumn());
    }
}
