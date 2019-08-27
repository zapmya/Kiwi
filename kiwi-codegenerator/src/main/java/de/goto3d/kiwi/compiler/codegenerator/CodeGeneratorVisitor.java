package de.goto3d.kiwi.compiler.codegenerator;

import de.goto3d.kiwi.compiler.SymbolTable;
import de.goto3d.kiwi.compiler.ast.AstNode;
import de.goto3d.kiwi.compiler.ast.BlockNode;
import de.goto3d.kiwi.compiler.ast.Visitor;
import de.goto3d.kiwi.compiler.ast.classes.ClassNode;
import de.goto3d.kiwi.compiler.ast.compilation.CompilationItemsNode;
import de.goto3d.kiwi.compiler.ast.constructors.ArrayConstructorNode;
import de.goto3d.kiwi.compiler.ast.constructors.NativeArrayConstructorNode;
import de.goto3d.kiwi.compiler.ast.expressions.*;
import de.goto3d.kiwi.compiler.ast.functions.ExternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.functions.FunctionListNode;
import de.goto3d.kiwi.compiler.ast.functions.InternalFunctionNode;
import de.goto3d.kiwi.compiler.ast.constructors.VectorConstructorNode;
import de.goto3d.kiwi.compiler.ast.statements.*;
import de.goto3d.kiwi.compiler.ast.types.StructNode;
import de.goto3d.kiwi.compiler.ast.types.TypeConversionNode;
import de.goto3d.kiwi.compiler.codegenerator.constructors.ArrayConstructorGenerator;
import de.goto3d.kiwi.compiler.codegenerator.constructors.NativeArrayConstructorGenerator;
import de.goto3d.kiwi.compiler.codegenerator.expressions.*;
import de.goto3d.kiwi.compiler.codegenerator.functions.*;
import de.goto3d.kiwi.compiler.codegenerator.constructors.VectorConstructorGenerator;
import de.goto3d.kiwi.compiler.codegenerator.statements.*;
import de.goto3d.kiwi.compiler.codegenerator.types.TypeConversionGenerator;
import de.goto3d.kiwi.compiler.codegenerator.types.TypeGenerator;
import de.goto3d.kiwi.compiler.llvmbindings.*;

import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: gru
 * Date: 06.02.13
 * Time: 06:45
 */
public class CodeGeneratorVisitor implements Visitor<LLVMValue> {

    private final Stack<LoopEntry> loopEntryStack = new Stack<>();

    private LLVMModule module;

    private final LLVMBuilder builder;

    private final LLVMContext context;

    private final ArithmeticExpressionGenerator arithmeticExpressionGenerator = new ArithmeticExpressionGenerator(this);

    private final FunctionListGenerator functionListGenerator = new FunctionListGenerator(this);

    private final PrototypeGenerator prototypeGenerator = new PrototypeGenerator(this);

    private final ExternalFunctionGenerator externalFunctionGenerator = new ExternalFunctionGenerator(
            this, this.prototypeGenerator
    );

    private final InternalFunctionGenerator internalFunctionGenerator = new InternalFunctionGenerator(
            this, this.prototypeGenerator
    );

    private final TypeGenerator typeGenerator = new TypeGenerator();

    private final AssignmentGenerator assignmentGenerator = new AssignmentGenerator(this);

    private final DeclarationAndAssignmentGenerator declarationAndAssignmentGenerator =
            new DeclarationAndAssignmentGenerator(this, this.typeGenerator);

    private final RelationalExpressionGenerator relationalExpressionGenerator = new RelationalExpressionGenerator(this);

    private final SimpleStatementGenerator simpleStatementGenerator = new SimpleStatementGenerator(this);

    private final BlockGenerator blockGenerator = new BlockGenerator(this);

    private final PrecedenceGenerator precedenceGenerator = new PrecedenceGenerator(this);

    private NumberGenerator numberGenerator = new NumberGenerator(this);

    private NegateGenerator negateGenerator = new NegateGenerator(this);

    private IdentifierGenerator identifierGenerator = new IdentifierGenerator(this);

    private CallGenerator callGenerator = new CallGenerator(this, prototypeGenerator);

    private WhileStatementGenerator whileStatementGenerator = new WhileStatementGenerator(this);

    private DoWhileStatementGenerator doWhileStatementGenerator = new DoWhileStatementGenerator(this);

    private IfStatementGenerator ifStatementGenerator = new IfStatementGenerator(this);

    private IfElseStatementGenerator ifElseStatementGenerator = new IfElseStatementGenerator(this);

    private ReturnStatementGenerator returnGenerator = new ReturnStatementGenerator(this);

    private BreakStatementGenerator breakGenerator = new BreakStatementGenerator(this);

    private TypeConversionGenerator typeConversionGenerator = new TypeConversionGenerator(this);

    private VectorConstructorGenerator vectorConstructorGenerator =
            new VectorConstructorGenerator(this, this.typeGenerator);

    private ArrayConstructorGenerator arrayConstructorGenerator = new ArrayConstructorGenerator(
            this, this.prototypeGenerator, this.typeGenerator
    );

    private NativeArrayConstructorGenerator nativeArrayConstructorGenerator = new NativeArrayConstructorGenerator(
            this, this.typeGenerator
    );

    private IndexAccessGenerator indexAccessGenerator = new IndexAccessGenerator(this);

    private IndexedAssignmentGenerator indexedAssignmentGenerator = new IndexedAssignmentGenerator(this);

    private VariableStore currentVariableStore;
    private LLVMFunction currentFunction;

    private final SymbolTable symbolTable;


    public CodeGeneratorVisitor(String moduleName, SymbolTable symbolTable) {

        this.symbolTable= symbolTable;

        this.context    = LLVMContext.getGlobalContext();

        this.builder    = new LLVMBuilder(this.context);
    }

    public ExternalFunctionNode getFunctionNode(String functionName) {
        return this.symbolTable.findFirstFunctionNode(functionName);
    }

    public LLVMModule getModule() {
        return module;
    }

    public LLVMBuilder getBuilder() {
        return builder;
    }

    public LLVMContext getContext() {
        return context;
    }

    public void dump() {
        // TODO: RUNTIME_MODULE auslagern in eigene Klasse(n)
        PrototypeGenerator.RUNTIME_MODULE.dump();
        this.module.dump();
    }

    public String print() {
        // TODO: RUNTIME_MODULE auslagern in eigene Klasse(n)
        return PrototypeGenerator.RUNTIME_MODULE.print() + this.module.print();
    }

    public VariableStore getCurrentVariableStore() {
        return this.currentVariableStore;
    }

    @Override
    public LLVMValue visit(NumberNode numberNode) {
        return this.numberGenerator.generateCode(numberNode);
    }

    @Override
    public LLVMValue visit(NegateNode negateNode) {
        return this.negateGenerator.generateCode(negateNode);
    }

    @Override
    public LLVMValue visit(IdentifierNode identifierNode) {
        return this.identifierGenerator.generateCode(identifierNode);
    }

    @Override
    public LLVMValue visit(AssignmentNode assignmentNode) {
        return this.assignmentGenerator.generateCode(assignmentNode);
    }

    @Override
    public LLVMValue visit(IndexedAssignmentNode assignmentNode) {
        return this.indexedAssignmentGenerator.generateCode(assignmentNode);
    }

    @Override
    public LLVMValue visit(DeclarationAndAssignmentNode declarationAndAssignmentNode) {
        return this.declarationAndAssignmentGenerator.generateCode(declarationAndAssignmentNode);
    }

    @Override
    public LLVMValue visit(StatementListNode statementListNode) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LLVMValue visit(CallNode callNode) {
        return this.callGenerator.generateCode(callNode);
    }

    @Override
    public LLVMValue visit(OperationNode operationNode) {
        return this.arithmeticExpressionGenerator.generateCode(operationNode);
    }

    @Override
    public LLVMValue visit(SimpleStatementNode simpleStatement) {
        return this.simpleStatementGenerator.generateCode(simpleStatement);
    }

    @Override
    public LLVMValue visit(IfStatementNode ifStatementNode) {
        return this.ifStatementGenerator.generateCode(ifStatementNode);
    }

    @Override
    public LLVMValue visit(IfElseStatementNode ifElseStatementNode) {
        return this.ifElseStatementGenerator.generateCode(ifElseStatementNode);
    }

    @Override
    public LLVMValue visit(WhileStatementNode whileStatementNode) {
        return this.whileStatementGenerator.generateCode(whileStatementNode);
    }

    @Override
    public LLVMValue visit(DoWhileStatementNode doWhileStatementNode) {
        return this.doWhileStatementGenerator.generateCode(doWhileStatementNode);
    }

    @Override
    public LLVMValue visit(InternalFunctionNode internalFunctionNode) {

        VariableStore oldStore      = this.currentVariableStore;
        this.currentVariableStore   = new VariableStore(oldStore);
        LLVMValue value             = this.internalFunctionGenerator.generateCode(internalFunctionNode);
        this.currentVariableStore   = oldStore;
        return value;
    }

    @Override
    public LLVMValue visit(BlockNode blockNode) {
        VariableStore oldStore      = this.currentVariableStore;
        this.currentVariableStore   = new VariableStore(oldStore);
        LLVMValue value             = this.blockGenerator.generateCode(blockNode);
        this.currentVariableStore   = oldStore;
        return value;
    }

    @Override
    public LLVMValue visit(PrecedenceNode precedenceNode) {
        return this.precedenceGenerator.generateCode(precedenceNode);
    }

    @Override
    public LLVMValue visit(ReturnStatementNode returnStatementNode) {
        return this.returnGenerator.generateCode(returnStatementNode);
    }

    @Override
    public LLVMValue visit(BreakStatementNode breakStatementNode) {
        return this.breakGenerator.generateCode(breakStatementNode);
    }

    @Override
    public LLVMValue visit(ExpressionListNode expressionListNode) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LLVMValue visit(ArgumentListNode argumentListNode) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LLVMValue visit(FunctionListNode functionListNode) {
        // first step: generate all prototypes (this way we are getting rid of forward declarations)
        for (ExternalFunctionNode functionNode : functionListNode.getItems()) {
            this.prototypeGenerator.generateCode(functionNode);
        }
        // second: generate code in functions
        return this.functionListGenerator.generateCode(functionListNode);
    }

    @Override
    public LLVMValue visit(RelationExpressionNode relationExpressionNode) {
        return this.relationalExpressionGenerator.generateCode(relationExpressionNode);
    }

    @Override
    @SuppressWarnings("unchecked")
    public LLVMValue visit(ExternalFunctionNode externalFunctionNode) {
        return this.externalFunctionGenerator.generateCode(externalFunctionNode);
    }

    @Override
    public LLVMValue visit(DeclarationNode declarationNode) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public LLVMValue visit(TypeConversionNode typeConversionNode) {
        return this.typeConversionGenerator.generateCode(typeConversionNode);
    }

    @Override
    public LLVMValue visit(VoidExpressionNode voidExpressionNode) {
        return null;
    }

    @Override
    public LLVMValue visit(VectorConstructorNode vectorConstructorNode) {
        return this.vectorConstructorGenerator.generateCode(vectorConstructorNode);
    }

    @Override
    public LLVMValue visit(ClassNode classNode) {
        this.module = new LLVMModule(classNode.getIdentifier(), this.context);
        return classNode.getFunctionListNode().accept(this);
    }

    @Override
    public LLVMValue visit(StructNode structNode) {
        return structNode.getDeclarationListNode().accept(this);
    }

    @Override
    public LLVMValue visit(IndexAccessNode indexAccessNode) {
        return this.indexAccessGenerator.generateCode(indexAccessNode);
    }

    @Override
    public LLVMValue visit(ArrayConstructorNode arrayConstructorNode) {
        return this.arrayConstructorGenerator.generateCode(arrayConstructorNode);
    }

    @Override
    public LLVMValue visit(NativeArrayConstructorNode arrayConstructorNode) {
        return this.nativeArrayConstructorGenerator.generateCode(arrayConstructorNode);
    }

    @Override
    public LLVMValue visit(DimNode dimNode) {
        return null;
    }

    @Override
    public LLVMValue visit(DimExpressionsNode dimExpressionsNode) {
        return null;
    }

    @Override
    public LLVMValue visit(CompilationItemsNode compilationItemsNode) {
        for (AstNode astNode : compilationItemsNode.getItems()) {
            astNode.accept(this);
        }
        return null;
    }

    public void setCurrentFunction(LLVMFunction currentFunction) {
        this.currentFunction = currentFunction;
    }

    public LLVMFunction getCurrentFunction() {
        return currentFunction;
    }

    public void pushLoopEntry(LoopEntry loopEntry) {
        this.loopEntryStack.push(loopEntry);
    }

    public LoopEntry popLoopEntry() {
        return this.loopEntryStack.pop();
    }

    public LoopEntry peekLoopEntry() {
        return this.loopEntryStack.peek();
    }
}
