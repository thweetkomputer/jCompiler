import antlr.ErrorHandler;
import antlr.ZccBaseVisitor;
import antlr.ZccParser;

import java.time.temporal.ValueRange;
import java.util.*;

/**
 * @author jerryzhao
 * @date 2021/11/4
 */
public class Visitor extends ZccBaseVisitor<Void> {
    boolean debug = false;
    int index = 1;
    int labelIndex = 1;
    int globalIndex = 1;
    int constExpRes;
    boolean calConstExp = false;
    int level = 0;
    boolean global = true;
    boolean left = false;
    boolean main = false;
    boolean isReturn = true;
    boolean hasReturn = false;
    boolean param = false;
    List<Variable> parameterList;

    StringBuilder preprocessor = new StringBuilder();
    StringBuilder parameterPreprocessor = null;

    List<Map<String, Variable>> varMapStack = new ArrayList<>();
    Map<Object, Integer> blockMap = new HashMap<>(128);
    Set<Object> blockSet = new HashSet<>(128);
    Map<String, Function> functionMap = new HashMap<String, Function>(128) {
        {
            put("putint", new Function("putint", "void", new ArrayList<Variable>() {
                {
                    add(Variable.newParam("", new int[0]));
                }
            }));
            put("getint", new Function("getint", "i32", new ArrayList<>()));
            put("putch", new Function("putch", "void", new ArrayList<Variable>() {
                {
                    add(Variable.newParam("", new int[0]));
                }
            }));
            put("getch", new Function("getch", "i32", new ArrayList<>()));
            put("putarray", new Function("putarray", "void", new ArrayList<Variable>() {
                {
                    add(Variable.newParam("", new int[0]));
                    add(Variable.newParam("", new int[1]));
                }
            }));
            put("getarray", new Function("getarray", "i32", new ArrayList<Variable>() {
                {
                    add(Variable.newParam("", new int[1]));
                }
            }));
        }
    };
    Deque<List<Variable>> paramsStack = new LinkedList<>();
    Deque<Loop> loopStack = new LinkedList<>();

    @Override
    public Void visitCompUnit(ZccParser.CompUnitContext ctx) {
        varMapStack.add(new HashMap<>(16));
        for (int i = 0; i < ctx.children.size(); i++) {
            visit(ctx.children.get(i));
        }
        return null;
    }

    @Override
    public Void visitFuncDef(ZccParser.FuncDefContext ctx) {
        parameterPreprocessor = new StringBuilder();
        parameterList = new ArrayList<>();
        boolean oldGlobal = global;
        global = false;

        String name = ctx.ident().IDENT().toString();
        if ("main".equals(name)) {
            global = false;
            main = true;
            index = 10000;
        } else {
            index = 0;
        }
        String funcType = ctx.funcType().INT() != null ? "i32" : "void";
        isReturn = ctx.funcType().INT() != null;
        printf("define dso_local %s @%s(", funcType, name);
        level++;
        varMapStack.add(new HashMap<>());
        if (ctx.funcFParams() != null) {
            visit(ctx.funcFParams());
        }
        if (functionMap.get(name) != null) {
            ErrorHandler.err("function already defined");
        }
        functionMap.put(name, new Function(ctx.ident().IDENT().toString(), funcType, parameterList));
        for (int i = 0; i < parameterList.size(); i++) {
            Variable param = parameterList.get(i);
            if (i != 0) {
                print(", ");
            }
            int[] dimension = param.dimension;
            for (int j = 1; j < dimension.length; j++) {
                printf("[%d x ", dimension[j]);
            }
            printf("i32");
            for (int j = 1; j < dimension.length; j++) {
                printf("]");
            }
            if (dimension.length != 0) {
                print("*");
            }
            printf(" %%x%d", index);
            if (param.dimension.length == 0) {
                index++;
                parameterPreprocessor.append(String.format("\t%%x%d = alloca i32\n\tstore i32 %%x%d, i32* %%x%d\n", index, index - 1, index));
                addNewVar(param.name, index++, level, true);
            } else {
                addNewVarArray(param.name, index++, level, param.dimension, true);
            }
        }
        printf(") {\n");
        visit(ctx.block());
        hasReturn = false;
        try {
            if (ctx.block().blockItem(ctx.block().blockItem().size() - 1).stmt().RETURN() != null) {
                hasReturn = true;
            }
        } catch (Exception ignored) {
        }
        if (!hasReturn) {
            if (ctx.funcType().INT() != null) {
                printf("\tret i32 0\n");
            } else {
                printf("\tret void\n");
            }
        } else {
            if (ctx.funcType().VOID() != null) {
                printf("\tret void\n");
            }
        }
        printf("}\n\n");

        global = oldGlobal;
        level--;
        varMapStack.remove(varMapStack.size() - 1);
        return null;
    }

    @Override
    public Void visitFuncFParams(ZccParser.FuncFParamsContext ctx) {
        for (ZccParser.FuncFParamContext context : ctx.funcFParam()) {
            visit(context);
        }
        return null;
    }

    @Override
    public Void visitFuncFParam(ZccParser.FuncFParamContext ctx) {
        String name = ctx.ident().IDENT().toString();
        if (ctx.LBRACK().size() == 0) {
            parameterList.add(Variable.newParam(name, new int[0]));
            return null;
        }
        int[] di = new int[ctx.LBRACK().size()];
        for (int i = 1; i < di.length; i++) {
            boolean oldCalConstExp = calConstExp;
            calConstExp = true;
            visit(ctx.exp(i - 1));
            calConstExp = oldCalConstExp;
            di[i] = constExpRes;
        }
        parameterList.add(Variable.newParam(name, di));
        return null;
    }

    @Override
    public Void visitBlock(ZccParser.BlockContext ctx) {
        if (main) {
            print(preprocessor.toString());
        }
        if (parameterPreprocessor != null) {
            print(parameterPreprocessor.toString());
            parameterPreprocessor = null;
        }
        level++;
        varMapStack.add(new HashMap<>());
        for (ZccParser.BlockItemContext blockItemContext : ctx.blockItem()) {
            visit(blockItemContext);
        }
        level--;
        varMapStack.remove(varMapStack.size() - 1);
        return null;
    }

    @Override
    public Void visitBlockItem(ZccParser.BlockItemContext ctx) {
        visit(ctx.decl() == null ? ctx.stmt() : ctx.decl());
        return null;
    }

    @Override
    public Void visitDecl(ZccParser.DeclContext ctx) {
        visit(ctx.varDecl() == null ? ctx.constDecl() : ctx.varDecl());
        return null;
    }


    @Override
    public Void visitStmt(ZccParser.StmtContext ctx) {
        if (blockMap.containsKey(ctx)) {
            printf("\nl%d:\n", blockMap.get(ctx));
        }
        if (ctx.children.size() == 1) {
            if (blockMap.containsKey(ctx)) {
                blockSet.add(ctx.block());
            }
            if (ctx.block() != null) {
                visit(ctx.block());
            }
            return null;
        }

        if (ctx.RETURN() != null) {
            if (ctx.children.size() == 3) {
                boolean oldCalConstExp = calConstExp;
                calConstExp = false;
                visit(ctx.exp());
                calConstExp = oldCalConstExp;
                printf("\tret i32 %%x%d\n", index - 1);
            } else {
                printf("\tret void\n");
            }
            return null;
        }

        if (ctx.children.size() == 2) {
            if (ctx.CONTINUE() != null) {
                assert loopStack.peek() != null;
                printf("\tbr label %%l%d\n", loopStack.peek().cond);
                return null;
            }
            if (ctx.BREAK() != null) {
                assert loopStack.peek() != null;
                printf("\tbr label %%l%d\n", loopStack.peek().next);
                return null;
            }
            visit(ctx.exp());
            return null;
        }


        if (ctx.children.size() == 4) {
            String name = ctx.lVal().ident().IDENT().toString();
            Variable variable = findInMap(name);
            if (variable == null) {
                ErrorHandler.err("cannot find variable %s", name);
            }
            assert variable != null;
            if (variable.type == Variable.Type.CONST) {
                ErrorHandler.err("cannot modify constant value");
            }
            visit(ctx.exp());
            if (variable.dimension.length == 0) {
                if (variable.level != 0) {
                    printf("\tstore i32 %%x%d, i32* %%x%d\n", index - 1, variable.index);
                } else {
                    printf("\tstore i32 %%x%d, i32* @g%d\n", index - 1, variable.index);
                }
            } else {
                if (ctx.lVal().exp().size() != variable.dimension.length) {
                    ErrorHandler.err("cannot modify pointer");
                }
                int ei = index - 1;
                left = true;
                visit(ctx.lVal());
                left = false;
                printf("\tstore i32 %%x%d, i32* %%x%d\n", ei, index - 1);
            }
            return null;
        }
        if (ctx.children.size() == 5) {
            if (ctx.WHILE() != null) {
                loopStack.push(new Loop(labelIndex, labelIndex + 1, labelIndex + 2));
                labelIndex += 3;
                assert loopStack.peek() != null;
                printf("\tbr label %%l%d\n", loopStack.peek().cond);
                assert loopStack.peek() != null;
                printf("\nl%d:\n", loopStack.peek().cond);
                visit(ctx.cond());
                int cn = index - 1;
                assert loopStack.peek() != null;
                printf("\tbr i1 %%x%d, label %%l%d, label %%l%d\n", cn, loopStack.peek().loop, loopStack.peek().next);
                assert loopStack.peek() != null;
                printf("\nl%d:\n", loopStack.peek().loop);
                visit(ctx.stmt(0));
                assert loopStack.peek() != null;
                printf("\tbr label %%l%d\n", loopStack.peek().cond);
                assert loopStack.peek() != null;
                printf("\nl%d:\n", loopStack.peek().next);
                loopStack.pop();
                return null;
            }
            visit(ctx.cond());
            int cn = index - 1;
            blockMap.put(ctx.stmt(0), labelIndex++);
            int ncn = labelIndex++;
            printf("\tbr i1 %%x%d, label %%l%d, label %%l%d\n", cn, blockMap.get(ctx.stmt(0)), ncn);
            visit(ctx.stmt(0));
            printf("\tbr label %%l%d\n", ncn);
            printf("\nl%d:\n", ncn);
            return null;
        }
        if (ctx.children.size() == 7) {
            visit(ctx.cond());
            int cn = index - 1;
            blockMap.put(ctx.stmt(0), labelIndex++);
            blockMap.put(ctx.stmt(1), labelIndex++);
            printf("\tbr i1 %%x%d, label %%l%d, label %%l%d\n", cn, blockMap.get(ctx.stmt(0)), blockMap.get(ctx.stmt(1)));
            visit(ctx.stmt(0));
            int ncn = labelIndex++;
            printf("\tbr label %%l%d\n", ncn);
            visit(ctx.stmt(1));
            printf("\tbr label %%l%d\n", ncn);
            printf("\nl%d:\n", ncn);
        }
        return null;
    }

    @Override
    public Void visitCond(ZccParser.CondContext ctx) {
        visit(ctx.lOrExp());
        return null;
    }

    @Override
    public Void visitLOrExp(ZccParser.LOrExpContext ctx) {
        visit(ctx.lAndExp(0));
        int vn = index - 1;
        for (int i = 0; i < ctx.OR().size(); i++) {
            visit(ctx.lAndExp(i + 1));
            printf("\t%%x%d = or i1 %%x%d, %%x%d\n", index, vn, index - 1);
            vn = index++;
        }
        return null;
    }

    @Override
    public Void visitLAndExp(ZccParser.LAndExpContext ctx) {
        visit(ctx.eqExp(0));
        int vn = index - 1;
        for (int i = 0; i < ctx.AND().size(); i++) {
            visit(ctx.eqExp(i + 1));
            printf("\t%%x%d = and i1 %%x%d, %%x%d\n", index, vn, index - 1);
            vn = index++;
        }
        return null;
    }

    @Override
    public Void visitEqExp(ZccParser.EqExpContext ctx) {
        visit(ctx.relExp(0));
        int vn = index - 1;
        if (ctx.relExp().size() == 1 && ctx.relExp(0).addExp().size() == 1) {
            printf("\t%%x%d = icmp ne i32 0, %%x%d\n", index, index - 1);
            index++;
            return null;
        }
        for (int i = 0; i < ctx.eOp().size(); i++) {
            visit(ctx.relExp(i + 1));
            if (ctx.eOp().get(i).EQ() != null) {
                printf("\t%%x%d = icmp eq i32 %%x%d, %%x%d\n", index, vn, index - 1);
            } else if (ctx.eOp().get(i).NE() != null) {
                printf("\t%%x%d = icmp ne i32 %%x%d, %%x%d\n", index, vn, index - 1);
            }
            vn = index++;
        }
        return null;
    }

    @Override
    public Void visitRelExp(ZccParser.RelExpContext ctx) {
        visit(ctx.addExp(0));
        int vn = index - 1;

        for (int i = 0; i < ctx.cmpOp().size(); i++) {
            visit(ctx.addExp(i + 1));
            if (ctx.cmpOp().get(i).GE() != null) {
                printf("\t%%x%d = icmp sge i32 %%x%d, %%x%d\n", index, vn, index - 1);
            } else if (ctx.cmpOp().get(i).LE() != null) {
                printf("\t%%x%d = icmp sle i32 %%x%d, %%x%d\n", index, vn, index - 1);
            } else if (ctx.cmpOp().get(i).GT() != null) {
                printf("\t%%x%d = icmp sgt i32 %%x%d, %%x%d\n", index, vn, index - 1);
            } else if (ctx.cmpOp().get(i).LT() != null) {
                printf("\t%%x%d = icmp slt i32 %%x%d, %%x%d\n", index, vn, index - 1);
            }
            vn = index++;
        }
        return null;
    }

    @Override
    public Void visitConstDecl(ZccParser.ConstDeclContext ctx) {
        for (ZccParser.ConstDefContext constDefContext : ctx.constDef()) {
            visit(constDefContext);
        }
        return null;
    }

    @Override
    public Void visitConstDef(ZccParser.ConstDefContext ctx) {
        String name = ctx.ident().IDENT().toString();
        Variable variable = findInMap(name);
        if (variable != null && variable.level == level) {
            ErrorHandler.err("constant already declare");
        }
        if (ctx.LBRACK().size() == 0) { // 变量
            calConstExp = true;
            visit(ctx.constInitVal());
            calConstExp = false;
            addNewConst(name, constExpRes, level);
        } else { // 数组
            if (global) { // 全局
                int[] di = new int[ctx.LBRACK().size()];
                int sum = 1;
                for (int i = 0; i < ctx.LBRACK().size(); i++) {
                    calConstExp = true;
                    visit(ctx.constExp(i));
                    calConstExp = false;
                    if (constExpRes < 0) {
                        ErrorHandler.err("cannot user negative as dimensions");
                    }
                    di[i] = constExpRes;
                }
                printf("@g%d = dso_local global ", globalIndex);
                for (int i : di) {
                    printf("[%d x ", i);
                    sum *= i;
                }
                print("i32");
                for (int i = 0; i < di.length; i++) {
                    print("]");
                }
                print(" zeroinitializer\n");
                int ai = globalIndex;
                int[] arrayVal = new int[sum];
                addNewConstArray(name, globalIndex++, arrayVal, level, di);
                if (di.length == 1) {
                    preprocessor.append(String.format("\t%%x%d = getelementptr [%d x i32],[%d x i32]* @g%d, i32 0, i32 0\n", index++, di[0], di[0], ai));
                    int bpi = index - 1;
                    if (ctx.constInitVal().LBRACE() == null) {
                        ErrorHandler.err("use {} to init an array");
                    }
                    for (int i = 0; i < di[0]; i++) {
                        preprocessor.append(String.format("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i));
                        int pi = index - 1;
                        if (i < ctx.constInitVal().constInitVal().size()) {
                            calConstExp = true;
                            visit(ctx.constInitVal().constInitVal(i));
                            calConstExp = false;
                            preprocessor.append(String.format("\tstore i32 %d, i32* %%x%d\n", constExpRes, pi));
                            arrayVal[i] = constExpRes;
                        } else {
                            preprocessor.append(String.format("\tstore i32 0, i32* %%x%d\n", pi));
                            arrayVal[0] = 0;
                        }
                    }
                } else if (di.length == 2) {
                    preprocessor.append(String.format("\t%%x%d = getelementptr [%d x [%d x i32]],[%d x [%d x i32]]* @g%d, i32 0, i32 0\n", index++, di[0], di[1], di[0], di[1], ai));
                    ai = index - 1;
                    preprocessor.append(String.format("\t%%x%d = getelementptr [%d x i32],[%d x i32]* %%x%d, i32 0, i32 0\n", index++, di[1], di[1], ai));
                    int bpi = index - 1;
                    if (ctx.constInitVal().LBRACE() == null) {
                        ErrorHandler.err("use {} to init an array");
                    }
                    for (int i = 0; i < di[0]; i++) {
                        for (int j = 0; j < di[1]; j++) {
                            int in = i * di[1] + j;
                            preprocessor.append(String.format("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i * di[1] + j));
                            int pi = index - 1;
                            if (i < ctx.constInitVal().constInitVal().size() && j < ctx.constInitVal().constInitVal(i).constInitVal().size()) {
                                calConstExp = true;
                                visit(ctx.constInitVal().constInitVal(i).constInitVal(j));
                                calConstExp = false;
                                preprocessor.append(String.format("\tstore i32 %d, i32* %%x%d\n", constExpRes, pi));
                                arrayVal[in] = constExpRes;

                            } else {
                                preprocessor.append(String.format("\tstore i32 0, i32* %%x%d\n", pi));
                                arrayVal[in] = 0;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < di.length; i++) {
                        preprocessor.append(String.format("\t%%x%d = getelementptr ", index++));
                        for (int j = i; j < di.length; j++) {
                            preprocessor.append(String.format("[%d x ", di[j]));
                        }
                        preprocessor.append("i32");
                        for (int j = i; j < di.length; j++) {
                            preprocessor.append("]");
                        }
                        preprocessor.append(",");
                        for (int j = i; j < di.length; j++) {
                            preprocessor.append(String.format("[%d x ", di[j]));
                        }
                        preprocessor.append("i32");
                        for (int j = i; j < di.length; j++) {
                            preprocessor.append("]");
                        }
                        preprocessor.append(String.format("* %s%d, i32 0, i32 0\n", i == 0 ? "@g" : "%x", ai));
                        ai = index - 1;
                    }
                    int bpi = index - 1;
                    for (int i = 0; i < sum; i++) {
                        int tSum = i;
                        int rSum = sum;
                        ZccParser.ConstInitValContext constInitValContext = ctx.constInitVal();
                        boolean trial = false;
                        try {
                            for (int k : di) {
                                rSum /= k;
                                constInitValContext = constInitValContext.constInitVal(tSum / rSum);
                                tSum %= rSum;
                            }
                            boolean oldCalConstExp = calConstExp;
                            calConstExp = true;
                            visit(constInitValContext);
                            calConstExp = oldCalConstExp;
                            trial = true;
                        } catch (Exception ignored) {
                        }

                        preprocessor.append(String.format("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i));
                        preprocessor.append(String.format("\tstore i32 %d, i32* %%x%d\n", trial ? constExpRes : 0, index - 1));
                    }
                }

            } else { // 非全局 数组
                int[] di = new int[ctx.LBRACK().size()];
                int sum = 1;
                for (int i = 0; i < ctx.LBRACK().size(); i++) {
                    calConstExp = true;
                    visit(ctx.constExp(i));
                    calConstExp = false;
                    if (constExpRes < 0) {
                        ErrorHandler.err("cannot user negative as dimensions");
                    }
                    di[i] = constExpRes;
                }
                printf("\t%%x%d = alloca ", index);
                for (int i : di) {
                    printf("[%d x ", i);
                    sum *= i;
                }
                print("i32");
                for (int i = 0; i < di.length; i++) {
                    print("]");
                }
                print("\n");
                int ai = index;
                int[] arrayVal = new int[sum];
                assert variable != null;
//                int length = variable.isParam ? di.length - 1 : di.length;
                addNewConstArray(name, index++, arrayVal, level, di);
                int length = di.length;
                if (length == 1) {
                    // %3 = getelementptr [2 x [2 x i32]],[2 x [2 x i32]]* %2, i32 0, i32 0
                    //    %4 = getelementptr [2 x i32],[2 x i32]* %3, i32 0, i32 0
                    printf("\t%%x%d = getelementptr [%d x i32],[%d x i32]* %%x%d, i32 0, i32 0\n", index++, di[0], di[0], ai);
                    int bpi = index - 1;
                    if (ctx.constInitVal().LBRACE() == null) {
                        ErrorHandler.err("use {} to init an array");
                    }
                    // %5 = getelementptr i32,i32* %4, i32 1
                    //    store i32 0, i32* %5
                    for (int i = 0; i < di[0]; i++) {
                        printf("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i);
                        int pi = index - 1;
                        if (i < ctx.constInitVal().constInitVal().size()) {
                            calConstExp = true;
                            visit(ctx.constInitVal().constInitVal(i));
                            calConstExp = false;
                            printf("\tstore i32 %d, i32* %%x%d\n", constExpRes, pi);
                            arrayVal[i] = constExpRes;
                        } else {
                            printf("\tstore i32 0, i32* %%x%d\n", pi);
                            arrayVal[i] = 0;
                        }
                    }
                } else if (length == 2) {
                    printf("\t%%x%d = getelementptr [%d x [%d x i32]],[%d x [%d x i32]]* %%x%d, i32 0, i32 0\n", index++, di[0], di[1], di[0], di[1], ai);
                    ai = index - 1;
                    printf("\t%%x%d = getelementptr [%d x i32],[%d x i32]* %%x%d, i32 0, i32 0\n", index++, di[1], di[1], ai);
                    int bpi = index - 1;
                    if (ctx.constInitVal().LBRACE() == null) {
                        ErrorHandler.err("use {} to init an array");
                    }
                    // %5 = getelementptr i32,i32* %4, i32 1
                    //    store i32 0, i32* %5
                    for (int i = 0; i < di[0]; i++) {
                        for (int j = 0; j < di[1]; j++) {
                            int in = i * di[1] + j;
                            printf("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i * di[1] + j);
                            int pi = index - 1;
                            if (i < ctx.constInitVal().constInitVal().size() && j < ctx.constInitVal().constInitVal(i).constInitVal().size()) {
                                calConstExp = true;
                                visit(ctx.constInitVal().constInitVal(i).constInitVal(j));
                                calConstExp = false;
                                printf("\tstore i32 %d, i32* %%x%d\n", constExpRes, pi);
                                arrayVal[in] = constExpRes;
                            } else {
                                printf("\tstore i32 0, i32* %%x%d\n", pi);
                                arrayVal[in] = 0;
                            }
                        }
                    }
                } else {
                    for (int i = 0; i < di.length; i++) {
                        printf("\t%%x%d = getelementptr ", index++);
                        for (int j = i; j < di.length; j++) {
                            printf("[%d x ", di[j]);
                        }
                        printf("i32");
                        for (int j = i; j < di.length; j++) {
                            printf("]");
                        }
                        printf(",");
                        for (int j = i; j < di.length; j++) {
                            printf("[%d x ", di[j]);
                        }
                        printf("i32");
                        for (int j = i; j < di.length; j++) {
                            printf("]");
                        }
                        printf("* %%x%d, i32 0, i32 0\n", i == 0 ? "@g" : "%x", ai);
                        ai = index - 1;
                    }
                    int bpi = index - 1;
                    for (int i = 0; i < sum; i++) {
                        int tSum = i;
                        int rSum = sum;
                        ZccParser.ConstInitValContext constInitValContext = ctx.constInitVal();
                        boolean trial = false;
                        try {
                            for (int k : di) {
                                rSum /= k;
                                constInitValContext = constInitValContext.constInitVal(tSum / rSum);
                                tSum %= rSum;
                            }
                            boolean oldCalConstExp = calConstExp;
                            calConstExp = true;
                            visit(constInitValContext);
                            calConstExp = oldCalConstExp;
                            trial = true;
                        } catch (Exception ignored) {
                        }

                        printf("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i);
                        printf("\tstore i32 %d, i32* %%x%d\n", trial ? constExpRes : 0, index - 1);
                    }
                }

            }
        }
        return null;
    }

    @Override
    public Void visitConstInitVal(ZccParser.ConstInitValContext ctx) {
        calConstExp = true;
        visit(ctx.constExp());
        calConstExp = false;
        return null;
    }

    @Override
    public Void visitConstExp(ZccParser.ConstExpContext ctx) {
        visit(ctx.addExp());
        return null;
    }

    @Override
    public Void visitAddExp(ZccParser.AddExpContext ctx) {
        if (calConstExp || global) {
            int res;
            visit(ctx.mulExp(0));
            res = constExpRes;
            for (int i = 0; i < ctx.unaryOp().size(); i++) {
                visit(ctx.mulExp(i + 1));
                if (ctx.unaryOp().get(i).ADD() != null) {
                    res += constExpRes;
                } else {
                    res -= constExpRes;
                }
            }
            constExpRes = res;
            return null;
        }

        visit(ctx.mulExp(0));
        int vn = index - 1;
        for (int i = 0; i < ctx.unaryOp().size(); i++) {
            visit(ctx.mulExp(i + 1));
            if (ctx.unaryOp().get(i).ADD() != null) {
                printf("\t%%x%d = add i32 %%x%d, %%x%d\n", index, vn, index - 1);
            } else if (ctx.unaryOp().get(i).SUB() != null) {
                printf("\t%%x%d = sub i32 %%x%d, %%x%d\n", index, vn, index - 1);
            }
            vn = index++;
        }
        return null;
    }

    @Override
    public Void visitMulExp(ZccParser.MulExpContext ctx) {
        if (calConstExp || global) {
            int res;
            visit(ctx.unaryExp(0));
            res = constExpRes;
            for (int i = 0; i < ctx.pUnayOp().size(); i++) {
                visit(ctx.unaryExp(i + 1));
                if (ctx.pUnayOp().get(i).MUL() != null) {
                    res *= constExpRes;
                } else if (ctx.pUnayOp().get(i).DIV() != null) {
                    res /= constExpRes;
                } else {
                    res %= constExpRes;
                }
            }
            constExpRes = res;
            return null;
        }

        visit(ctx.unaryExp(0));
        int vn = index - 1;
        for (int i = 0; i < ctx.pUnayOp().size(); i++) {
            visit(ctx.unaryExp(i + 1));
            if (ctx.pUnayOp().get(i).MUL() != null) {
                printf("\t%%x%d = mul i32 %%x%d, %%x%d\n", index, vn, index - 1);
            } else if (ctx.pUnayOp().get(i).DIV() != null) {
                printf("\t%%x%d = sdiv i32 %%x%d, %%x%d\n", index, vn, index - 1);
            } else {
                printf("\t%%x%d = srem i32 %%x%d, %%x%d\n", index, vn, index - 1);
            }
            vn = index++;
        }
        return null;
    }

    @Override
    public Void visitUnaryExp(ZccParser.UnaryExpContext ctx) {
        if (calConstExp || global) {
            if (ctx.ident() != null) {
                ErrorHandler.err("cannot use function return value to define constant");
            }
            if (ctx.primaryExp() != null) {
                visit(ctx.primaryExp());
            } else if (ctx.unaryOp() != null) {
                visit(ctx.unaryExp());
                int res = constExpRes;
                if (ctx.unaryOp().SUB() != null) {
                    res = -res;
                }
                constExpRes = res;
            }
            return null;
        }
        if (ctx.primaryExp() != null) {
            visit(ctx.primaryExp());
        } else if (ctx.unaryOp() != null) {
            visit(ctx.unaryExp());
            if (ctx.unaryOp().SUB() != null) {
                printf("\t%%x%d = sub i32 0, %%x%d\n", index, index - 1);
                index++;
            } else if (ctx.unaryOp().NOT() != null) {
                printf("\t%%x%d = icmp eq i32 0, %%x%d\n", index, index - 1);
                index++;
                printf("\t%%x%d = zext i1 %%x%d to i32\n", index, index - 1);
                index++;
            }
        } else {
            paramsStack.push(new ArrayList<>());
            String funcName = ctx.ident().IDENT().toString();
            Function function = functionMap.get(funcName);
            if (function == null) {
                ErrorHandler.err("cannot found %s()", funcName);
            }
            if (ctx.funcRParams() != null) {
                boolean oldLeft = left;
                left = true;
                visit(ctx.funcRParams());
                left = oldLeft;
            }
            List<Variable> list = paramsStack.pop();
            assert function != null;
            if ("void".equals(function.returnType)) {
                printf("\tcall %s @%s(", function.returnType, function.name);
            } else {
                if (function.parameterType.size() != list.size()) {
                    ErrorHandler.err("params num wrong");
                }
                printf("\t%%x%d = call %s @%s(", index++, function.returnType, function.name);
            }
            for (int i = 0; i < list.size(); i++) {
                if (i != 0) {
                    print(", ");
                }
                int[] dimension = function.parameterType.get(i).dimension;
                for (int j = 1; j < dimension.length; j++) {
                    printf("[%d x ", dimension[j]);
                }
                printf("i32");
                for (int j = 1; j < dimension.length; j++) {
                    printf("]");
                }
                if (dimension.length != 0) {
                    print("*");
                }
                if (list.get(i).level == 0) {
                    printf(" @g%d", list.get(i).index);
                } else {
                    printf(" %%x%d", list.get(i).index);
                }
            }
            printf(")\n");

        }
        return null;
    }

    @Override
    public Void visitFuncRParams(ZccParser.FuncRParamsContext ctx) {
        for (ZccParser.ExpContext expContext : ctx.exp()) {
            boolean trial = false;
            String name = null;
            try {
                name = expContext.addExp().mulExp(0).unaryExp(0).primaryExp().lVal().ident().IDENT().toString();
                if (expContext.addExp().mulExp().size() == 1 && expContext.addExp().mulExp(0).unaryExp().size() == 1 && expContext.addExp().mulExp(0).unaryExp(0).primaryExp().lVal().exp().size() == 0) {
                    trial = true;
                }
            } catch (Exception ignored) {
            }
            if (trial) {
                Variable inMap = findInMap(name);
                if (inMap == null) {
                    ErrorHandler.err("cannot find %s", name);
                }
                assert inMap != null;
                if (inMap.dimension.length == 0) {
                    Variable variable = new Variable(inMap, -1);
                    if (variable.type == Variable.Type.VAR) {
                        if (inMap.level != 0) {
                            printf("\t%%x%d = load i32, i32* %%x%d\n", index, inMap.index);
                        } else {
                            printf("\t%%x%d = load i32, i32* @g%d\n", index, inMap.index);
                        }
                    } else {
                        printf("\t%%x%d = load i32, i32* %d\n", index, variable.val);
                    }
                    variable.index = index++;
                    inMap = variable;
                    assert paramsStack.peek() != null;
                    paramsStack.peek().add(inMap);
                    continue;
                } else if (inMap.isParam && expContext.addExp().mulExp(0).unaryExp(0).primaryExp().lVal().exp().size() == 0) {
                    assert paramsStack.peek() != null;
                    paramsStack.peek().add(inMap);
                    continue;
                }

            }
            boolean oldParam = param;
            param = true;
            visit(expContext);
            param = oldParam;
            assert paramsStack.peek() != null;
            paramsStack.peek().add(Variable.newVariable("", index - 1, level));
        }
        return null;
    }

    @Override
    public Void visitPrimaryExp(ZccParser.PrimaryExpContext ctx) {
        if (ctx.lVal() != null) {
            visit(ctx.lVal());
        } else if (ctx.number() != null) {
            visit(ctx.number());
        } else if (ctx.children.size() == 3) {
            visit(ctx.exp());
        }
        return null;
    }

    @Override
    public Void visitLVal(ZccParser.LValContext ctx) {
        String name = ctx.ident().IDENT().toString();
        Variable variable = findInMap(name);
        if (variable == null) {
            ErrorHandler.err("cannot find constant %s", name);
        }
        if (calConstExp || global) {
            assert variable != null;
            if (variable.type == Variable.Type.VAR) {
                ErrorHandler.err("cannot use variable to declare constant");
            }
            if (ctx.LBRACK().size() == 0) {
                constExpRes = variable.val;
                return null;
            }
//            if (variable.dimension.length != ctx.LBRACK().size()) {
//                ErrorHandler.err("cannot directly use array");
//            }
            int sum = variable.arrayVal.length;
            int arrayIndex = 0;
            for (int i = 0; i < variable.dimension.length; i++) {
                boolean oldLeft = left;
                left = false;
                visit(ctx.exp(i));
                left = oldLeft;
                sum /= variable.dimension[i];
                arrayIndex += sum * constExpRes;
            }
            constExpRes = variable.arrayVal[arrayIndex];
            return null;
        }
        assert variable != null;
        if (ctx.exp().size() > variable.dimension.length) {
            ErrorHandler.err("what are you doing?");
        }
        if (variable.dimension.length == 0) {
            if (variable.level != 0) {
                if (variable.type == Variable.Type.VAR) {
                    printf("\t%%x%d = load i32, i32* %%x%d\n", index++, variable.index);
                } else {
                    printf("\t%%x%d = add i32 0, %d\n", index++, variable.val);
                }
            } else {
                if (variable.type == Variable.Type.VAR) {
                    printf("\t%%x%d = load i32, i32* @g%d\n", index++, variable.index);
                } else {
                    printf("\t%%x%d = add i32 0, %d\n", index++, variable.val);
                }
            }
        } else {
            int pi = variable.index;
            for (int i = 0; i < ctx.exp().size(); i++) {
                if (variable.isParam && i == 0) {
                    continue;
                }
                printf("\t%%x%d = getelementptr ", index++);
                for (int j = i; j < variable.dimension.length; j++) {
                    printf("[%d x ", variable.dimension[j]);
                }
                print("i32");
                for (int j = i; j < variable.dimension.length; j++) {
                    printf("]");
                }
                print(",");
                for (int j = i; j < variable.dimension.length; j++) {
                    printf("[%d x ", variable.dimension[j]);
                }
                print("i32");
                for (int j = i; j < variable.dimension.length; j++) {
                    printf("]");
                }
                print("* ");
                if (variable.level == 0 && i == 0) {
                    printf("@g");
                } else {
                    printf("%%x");
                }
                if (i == variable.dimension.length) {
                    printf("%d, i32 0\n", pi);
                } else {
                    printf("%d, i32 0, i32 0\n", pi);
                }
                pi = index - 1;
            }
            // TODO 如果是普通的不需要
            printf("\t%%x%d = add i32 0, 0\n", index);
            int si = index++;

            int sum = 1;
            for (int i = variable.dimension.length - 1; i >= ctx.exp().size(); i--) {
                sum *= variable.dimension[i];
            }
            for (int i = ctx.exp().size() - 1; i >= 0; i--) {
                boolean oldLeft = left;
                left = false;
                visit(ctx.exp(i));
                left = oldLeft;
                int ei = index - 1;
                printf("\t%%x%d = mul i32 %%x%d, %d\n", index++, ei, sum);
                int mi = index - 1;
                printf("\t%%x%d = add i32 %%x%d, %%x%d\n", index, si, mi);
                si = index;
                index++;
                sum *= variable.dimension[i];

            }
            printf("\t%%x%d = getelementptr ", index++);
            for (int j = ctx.exp().size(); j < variable.dimension.length; j++) {
                printf("[%d x ", variable.dimension[j]);
            }
            print("i32");
            for (int j = ctx.exp().size(); j < variable.dimension.length; j++) {
                printf("]");
            }
            print(",");
            for (int j = ctx.exp().size(); j < variable.dimension.length; j++) {
                printf("[%d x ", variable.dimension[j]);
            }
            print("i32");
            for (int j = ctx.exp().size(); j < variable.dimension.length; j++) {
                printf("]");
            }
            print("* ");
            if (variable.level == 0 && ctx.exp().size() == 0) {
                printf("@g");
            } else {
                printf("%%x");
            }
            if (ctx.exp().size() == variable.dimension.length) {
                printf("%d, i32 %%x%d\n", pi, si);
            } else {
                printf("%d, i32 0, i32 %%x%d\n", pi, si);
            }
            pi = index - 1;
            if (!left || param && ctx.exp().size() == variable.dimension.length) {
                printf("\t%%x%d = load i32, i32* %%x%d\n", index++, pi);
            }
        }
        return null;
    }

    @Override
    public Void visitNumber(ZccParser.NumberContext ctx) {
        int val;
        if (ctx.DECIMAL_CONST() != null) {
            val = Integer.parseInt(ctx.DECIMAL_CONST().toString());
        } else if (ctx.HEXADECIMAL_CONST() != null) {
            val = 0;
            String str = ctx.HEXADECIMAL_CONST().toString().toLowerCase();
            for (int i = 2; i < str.length(); i++) {
                val *= 16;
                val += Character.isDigit(str.charAt(i)) ? str.charAt(i) - '0' : str.charAt(i) - 'a' + 10;
            }
        } else {
            val = 0;
            String str = ctx.OCTAL_CONST().toString();
            for (int i = 2; i < str.length(); i++) {
                val *= 8;
                val += str.charAt(i) - '0';
            }
        }
        if (calConstExp || global) {
            constExpRes = val;
        } else {
            printf("\t%%x%d = add i32 0, %d\n", index++, val);
        }
        return null;
    }

    @Override
    public Void visitVarDecl(ZccParser.VarDeclContext ctx) {
        for (ZccParser.VarDefContext varDefContext : ctx.varDef()) {
            visit(varDefContext);
        }
        return null;
    }

    @Override
    public Void visitVarDef(ZccParser.VarDefContext ctx) {
        String name = ctx.ident().IDENT().toString();
        Variable variable = findInMap(name);
        if (variable != null && variable.level == level) {
            ErrorHandler.err("variable already declare");
        }
        if (ctx.ASSIGN() == null) { // 非初始化
            if (!global) { // 非初始化 非全局
                if (ctx.LBRACK().size() == 0) { // 非初始化 非全局 变量
                    printf("\t%%x%d = alloca i32\n", index);
                    addNewVar(name, index++, level);
                } else { // 非初始化 非全局 数组
                    int[] di = new int[ctx.LBRACK().size()];
                    for (int i = 0; i < ctx.LBRACK().size(); i++) {
                        calConstExp = true;
                        visit(ctx.constExp(i));
                        calConstExp = false;
                        if (constExpRes < 0) {
                            ErrorHandler.err("cannot user negative as dimensions");
                        }
                        di[i] = constExpRes;
                    }
                    printf("\t%%x%d = alloca ", index);
                    for (int i : di) {
                        printf("[%d x ", i);
                    }
                    print("i32");
                    for (int i = 0; i < di.length; i++) {
                        print("]");
                    }
                    print("\n");
                    addNewVarArray(name, index++, level, di);
                }
            } else { // 非初始化 全局
                if (ctx.LBRACK().size() == 0) { // 非初始化 全局 变量
                    printf("@g%d = dso_local global i32 0\n", globalIndex);
                    addNewVar(name, globalIndex++, level);
                } else { // 非初始化 全局 数组
                    int[] di = new int[ctx.LBRACK().size()];
                    for (int i = 0; i < ctx.LBRACK().size(); i++) {
                        calConstExp = true;
                        visit(ctx.constExp(i));
                        calConstExp = false;
                        if (constExpRes < 0) {
                            ErrorHandler.err("cannot user negative as dimensions");
                        }
                        di[i] = constExpRes;
                    }
                    printf("@g%d = dso_local global ", globalIndex);
                    for (int i : di) {
                        printf("[%d x ", i);
                    }
                    print("i32");
                    for (int i = 0; i < di.length; i++) {
                        print("]");
                    }
                    print(" zeroinitializer\n");
                    addNewVarArray(name, globalIndex++, level, di);
                }
            }
        } else { // 初始化
            int vn = index;
            if (!global) { // 初始化 非全局
                if (ctx.LBRACK().size() == 0) { // 初始化 非全局 变量
                    printf("\t%%x%d = alloca i32\n", index);
                    addNewVar(name, index++, level);
                    visit(ctx.initVal());
                    printf("\tstore i32 %%x%d, i32* %%x%d\n", index - 1, vn);
                } else { // 初始化 非全局 数组
                    int[] di = new int[ctx.LBRACK().size()];
                    int sum = 1;
                    for (int i = 0; i < ctx.LBRACK().size(); i++) {
                        calConstExp = true;
                        visit(ctx.constExp(i));
                        calConstExp = false;
                        if (constExpRes < 0) {
                            ErrorHandler.err("cannot user negative as dimensions");
                        }
                        di[i] = constExpRes;
                        sum *= constExpRes;
                    }
                    printf("\t%%x%d = alloca ", index);
                    for (int i : di) {
                        printf("[%d x ", i);
                    }
                    print("i32");
                    for (int i = 0; i < di.length; i++) {
                        print("]");
                    }
                    print("\n");
                    int ai = index;
                    addNewVarArray(name, index++, level, di);
                    if (di.length == 1) {
                        // %3 = getelementptr [2 x [2 x i32]],[2 x [2 x i32]]* %2, i32 0, i32 0
                        //    %4 = getelementptr [2 x i32],[2 x i32]* %3, i32 0, i32 0
                        printf("\t%%x%d = getelementptr [%d x i32],[%d x i32]* %%x%d, i32 0, i32 0\n", index++, di[0], di[0], ai);
                        int bpi = index - 1;
                        if (ctx.initVal().LBRACE() == null) {
                            ErrorHandler.err("use {} to init an array");
                        }
                        // %5 = getelementptr i32,i32* %4, i32 1
                        //    store i32 0, i32* %5
                        for (int i = 0; i < di[0]; i++) {
                            printf("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i);
                            int pi = index - 1;
                            if (i < ctx.initVal().initVal().size()) {
                                visit(ctx.initVal().initVal(i));
                                printf("\tstore i32 %%x%d, i32* %%x%d\n", index - 1, pi);
                            } else {
                                printf("\tstore i32 0, i32* %%x%d\n", pi);
                            }
                        }
                    } else if (di.length == 2) {
                        printf("\t%%x%d = getelementptr [%d x [%d x i32]],[%d x [%d x i32]]* %%x%d, i32 0, i32 0\n", index++, di[0], di[1], di[0], di[1], ai);
                        ai = index - 1;
                        printf("\t%%x%d = getelementptr [%d x i32],[%d x i32]* %%x%d, i32 0, i32 0\n", index++, di[1], di[1], ai);
                        int bpi = index - 1;
                        if (ctx.initVal().LBRACE() == null) {
                            ErrorHandler.err("use {} to init an array");
                        }
                        // %5 = getelementptr i32,i32* %4, i32 1
                        //    store i32 0, i32* %5
                        for (int i = 0; i < di[0]; i++) {
                            for (int j = 0; j < di[1]; j++) {
                                printf("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i * di[1] + j);
                                int pi = index - 1;
                                if (i < ctx.initVal().initVal().size() && j < ctx.initVal().initVal(i).initVal().size()) {
                                    visit(ctx.initVal().initVal(i).initVal(j));
                                    printf("\tstore i32 %%x%d, i32* %%x%d\n", index - 1, pi);
                                } else {
                                    printf("\tstore i32 0, i32* %%x%d\n", pi);
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < di.length; i++) {
                            printf("\t%%x%d = getelementptr ", index++);
                            for (int j = i; j < di.length; j++) {
                                printf("[%d x ", di[j]);
                            }
                            printf("i32");
                            for (int j = i; j < di.length; j++) {
                                printf("]");
                            }
                            printf(",");
                            for (int j = i; j < di.length; j++) {
                                printf("[%d x ", di[j]);
                            }
                            printf("i32");
                            for (int j = i; j < di.length; j++) {
                                printf("]");
                            }
                            printf("* %s%d, i32 0, i32 0\n", "%x", ai);
                            ai = index - 1;
                        }
                        int bpi = index - 1;
                        for (int i = 0; i < sum; i++) {
                            int tSum = i;
                            int rSum = sum;
                            ZccParser.InitValContext initValContext = ctx.initVal();
                            boolean trial = false;
                            try {
                                for (int k : di) {
                                    rSum /= k;
                                    initValContext = initValContext.initVal(tSum / rSum);
                                    tSum %= rSum;
                                }
                                boolean oldCalConstExp = calConstExp;
                                calConstExp = false;
                                visit(initValContext);
                                calConstExp = oldCalConstExp;
                                trial = true;
                            } catch (Exception ignored) {
                            }

                            printf("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i);
                            printf("\tstore i32 %s%d, i32* %%x%d\n", trial ? "%x" : "", trial ? index - 2 : 0, index - 1);
                        }
                    }
                }
            } else { // 初始化 全局
                if (ctx.LBRACK().size() == 0) { // 初始化 全局 变量
                    visit(ctx.initVal());
                    addNewVar(name, globalIndex, level);
                    printf("@g%d = dso_local global i32 %d\n", globalIndex++, constExpRes);
                } else { // 初始化 全局 数组
                    int[] di = new int[ctx.LBRACK().size()];
                    int sum = 1;
                    for (int i = 0; i < ctx.LBRACK().size(); i++) {
                        calConstExp = true;
                        visit(ctx.constExp(i));
                        calConstExp = false;
                        if (constExpRes < 0) {
                            ErrorHandler.err("cannot user negative as dimensions");
                        }
                        di[i] = constExpRes;
                        sum *= constExpRes;
                    }
                    printf("@g%d = dso_local global ", globalIndex);
                    for (int i : di) {
                        printf("[%d x ", i);
                    }
                    print("i32");
                    for (int i = 0; i < di.length; i++) {
                        print("]");
                    }
                    print(" zeroinitializer\n");
                    int ai = globalIndex;
                    addNewVarArray(name, globalIndex++, level, di);
                    if (di.length == 1) {
                        // %3 = getelementptr [2 x [2 x i32]],[2 x [2 x i32]]* %2, i32 0, i32 0
                        //    %4 = getelementptr [2 x i32],[2 x i32]* %3, i32 0, i32 0
                        preprocessor.append(String.format("\t%%x%d = getelementptr [%d x i32],[%d x i32]* @g%d, i32 0, i32 0\n", index++, di[0], di[0], ai));
                        int bpi = index - 1;
                        if (ctx.initVal().LBRACE() == null) {
                            ErrorHandler.err("use {} to init an array");
                        }
                        // %5 = getelementptr i32,i32* %4, i32 1
                        //    store i32 0, i32* %5
                        for (int i = 0; i < di[0]; i++) {
                            preprocessor.append(String.format("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i));
                            int pi = index - 1;
                            if (i < ctx.initVal().initVal().size()) {
                                visit(ctx.initVal().initVal(i));
                                preprocessor.append(String.format("\tstore i32 %d, i32* %%x%d\n", constExpRes, pi));
                            } else {
                                preprocessor.append(String.format("\tstore i32 0, i32* %%x%d\n", pi));
                            }
                        }
                    } else if (di.length == 2) {
                        preprocessor.append(String.format("\t%%x%d = getelementptr [%d x [%d x i32]],[%d x [%d x i32]]* @g%d, i32 0, i32 0\n", index++, di[0], di[1], di[0], di[1], ai));
                        ai = index - 1;
                        preprocessor.append(String.format("\t%%x%d = getelementptr [%d x i32],[%d x i32]* %%x%d, i32 0, i32 0\n", index++, di[1], di[1], ai));
                        int bpi = index - 1;
                        if (ctx.initVal().LBRACE() == null) {
                            ErrorHandler.err("use {} to init an array");
                        }
                        // %5 = getelementptr i32,i32* %4, i32 1
                        //    store i32 0, i32* %5
                        for (int i = 0; i < di[0]; i++) {
                            for (int j = 0; j < di[1]; j++) {
                                preprocessor.append(String.format("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i * di[1] + j));
                                int pi = index - 1;
                                if (i < ctx.initVal().initVal().size() && j < ctx.initVal().initVal(i).initVal().size()) {
                                    visit(ctx.initVal().initVal(i).initVal(j));
                                    preprocessor.append(String.format("\tstore i32 %d, i32* %%x%d\n", constExpRes, pi));
                                } else {
                                    preprocessor.append(String.format("\tstore i32 0, i32* %%x%d\n", pi));
                                }
                            }
                        }
                    } else {
                        for (int i = 0; i < di.length; i++) {
                            preprocessor.append(String.format("\t%%x%d = getelementptr ", index++));
                            for (int j = i; j < di.length; j++) {
                                preprocessor.append(String.format("[%d x ", di[j]));
                            }
                            preprocessor.append("i32");
                            for (int j = i; j < di.length; j++) {
                                preprocessor.append("]");
                            }
                            preprocessor.append(",");
                            for (int j = i; j < di.length; j++) {
                                preprocessor.append(String.format("[%d x ", di[j]));
                            }
                            preprocessor.append("i32");
                            for (int j = i; j < di.length; j++) {
                                preprocessor.append("]");
                            }
                            preprocessor.append(String.format("* %s%d, i32 0, i32 0\n", i == 0 ? "@g" : "%x", ai));
                            ai = index - 1;
                        }
                        int bpi = index - 1;
                        for (int i = 0; i < sum; i++) {
                            int tSum = i;
                            int rSum = sum;
                            ZccParser.InitValContext initValContext = ctx.initVal();
                            boolean trial = false;
                            try {
                                for (int k : di) {
                                    rSum /= k;
                                    initValContext = initValContext.initVal(tSum / rSum);
                                    tSum %= rSum;
                                }
                                boolean oldCalConstExp = calConstExp;
                                calConstExp = false;
                                visit(initValContext);
                                calConstExp = oldCalConstExp;
                                trial = true;
                            } catch (Exception ignored) {
                            }

                            preprocessor.append(String.format("\t%%x%d = getelementptr i32,i32* %%x%d, i32 %d\n", index++, bpi, i));
                            preprocessor.append(String.format("\tstore i32 %d, i32* %%x%d\n", trial ? constExpRes : 0, index - 1));
                        }
                    }
                }
            }
        }
        return null;
    }

    @Override
    public Void visitInitVal(ZccParser.InitValContext ctx) {
        visit(ctx.exp());
        return null;
    }

    @Override
    public Void visitExp(ZccParser.ExpContext ctx) {
        visit(ctx.addExp());
        return null;
    }


    void print(String msg) {
        System.out.print(msg);
    }

    void printf(String msg, Object... params) {
        System.out.printf(msg, params);
    }

    void debug(String msg, Object... params) {
        if (!debug) {
            return;
        }
        System.err.printf(msg, params);
    }

    void debug(String msg) {
        if (!debug) {
            return;
        }
        System.err.print(msg);
    }

    Variable findInMap(String name) {
        for (int i = varMapStack.size() - 1; i >= 0; i--) {
            Variable variable = varMapStack.get(i).get(name);
            if (variable != null) {
                return variable;
            }
        }
        return null;
    }

    void addNewConst(String name, int val, int level) {
        varMapStack.get(varMapStack.size() - 1).put(name, Variable.newConstant(name, val, level));
    }

    void addNewVar(String name, int index, int level) {
        varMapStack.get(varMapStack.size() - 1).put(name, Variable.newVariable(name, index, level));
    }

    void addNewVar(String name, int index, int level, boolean isParam) {
        Variable variable = Variable.newVariable(name, index, level);
        variable.isParam = isParam;
        varMapStack.get(varMapStack.size() - 1).put(name, variable);
    }

    void addNewConstArray(String name, int index, int[] arrayVal, int level, int[] di) {
        varMapStack.get(varMapStack.size() - 1).put(name, Variable.newConstantArray(name, index, arrayVal, level, di));
    }

    void addNewVarArray(String name, int index, int level, int[] di) {
        varMapStack.get(varMapStack.size() - 1).put(name, Variable.newVariableArray(name, index, level, di));
    }

    void addNewVarArray(String name, int index, int level, int[] di, boolean isParam) {
        Variable variable = Variable.newVariableArray(name, index, level, di);
        variable.isParam = isParam;
        varMapStack.get(varMapStack.size() - 1).put(name, variable);
    }
}

class Function {
    String name;
    String returnType;
    List<Variable> parameterType;

    public Function() {
    }

    public Function(String name, String returnType, List<Variable> parameterType) {
        this.name = name;
        this.returnType = returnType;
        this.parameterType = parameterType;
    }
}

class Variable {
    String name;
    int val;
    int[] arrayVal;
    int index;
    boolean isParam = false;

    /**
     * 变量类型
     */
    enum Type {
        VAR, // 变量
        CONST // 常量
    }

    Type type;
    int[] dimension;
    int level;

    public Variable() {
    }

    public static Variable newParam(String name, int[] dimension) {
        Variable variable = new Variable();
        variable.name = name;
        variable.dimension = dimension;
        variable.type = Type.VAR;
        variable.isParam = true;
        return variable;
    }

    public static Variable newVariable(String name, int index, int level) {
        Variable variable = new Variable();
        variable.index = index;
        variable.type = Type.VAR;
        variable.name = name;
        variable.dimension = new int[0];
        variable.level = level;
        return variable;
    }

    public static Variable newConstant(String name, int val, int level) {
        Variable variable = new Variable();
        variable.val = val;
        variable.type = Type.CONST;
        variable.name = name;
        variable.level = level;
        variable.dimension = new int[0];
        return variable;
    }

    public static Variable newVariableArray(String name, int index, int level, int[] di) {
        Variable variable = new Variable();
        variable.index = index;
        variable.type = Type.VAR;
        variable.name = name;
        variable.dimension = di;
        variable.level = level;
        int length = 1;
        for (int j : di) {
            length *= j;
        }
        variable.arrayVal = new int[length];
        return variable;
    }

    public static Variable newConstantArray(String name, int index, int[] arrayVal, int level, int[] di) {
        Variable variable = new Variable();
        variable.index = index;
        variable.type = Type.CONST;
        variable.arrayVal = arrayVal;
        variable.name = name;
        variable.level = level;
        variable.dimension = di;
        return variable;
    }

    public Variable(Variable variable, int level) {
        this.index = variable.index;
        this.type = variable.type;
        this.level = level;
        this.name = variable.name;
        this.val = variable.val;
    }
}

class Loop {
    Integer cond;
    Integer loop;
    Integer next;

    public Loop(Integer cond, Integer loop, Integer next) {
        this.cond = cond;
        this.loop = loop;
        this.next = next;
    }
}