package com.mycompany.lfa_trabalho2;


public class LFA_Trabalho2 {
    static final ExpressaoRegular ex = new ExpressaoRegular();
    
    static final String VAR_TYPE = "(void|int|float|String|char|boolean|Object)";
    static final String FUNCTION_ARG = "(" + VAR_TYPE + ex.BRANCO + ex.IDENT + ")?";
    static final String FUNCTION_ARGS = "(\\(" + FUNCTION_ARG + "(," + ex.BRANCOS + FUNCTION_ARG + ")*\\))";
    static final String COMPARISON_OPERATORS = "(\\==|\\=|\\<|\\<=|\\>|\\>=|!=)";
    static final String VECTOR = "(" + ex.IDENT + "\\[(" + ex.NUMEROS + "|" + ex.IDENT + ")\\](\\." + ex.IDENT + ")?)";
    static final String FUNCTION_CALL = "(" + ex.IDENT + "\\(" + ex.IDENT + "(," + ex.BRANCOS + ex.IDENT + ")*" + "\\))";
    static final String VALUE = "(" + ex.NUMEROS + "|" + VECTOR + "|" + ex.IDENT + "|" + FUNCTION_CALL + ")";
    static final String OPERATORS = "(\\+|\\-|\\*|\\/|\\%)";
    
    static final String FUNCTION_SIGNATURE = "(" + VAR_TYPE + ex.BRANCOS + ex.IDENT + FUNCTION_ARGS + ")" + ex.BRANCOS + ";";
    static final String MATH_EXPRESSION = "(" + ex.BRANCOS + VALUE + ex.BRANCOS + OPERATORS + ex.BRANCOS + VALUE + ")(" + 
                ex.BRANCOS + OPERATORS + ex.BRANCOS + VALUE + ")*";
    static final String COMPARABLE = "(" + ex.NUMEROS + "|" + MATH_EXPRESSION + "|" + FUNCTION_CALL + "|" + ex.IDENT + ")";
    static final String CONDITIONAL = "(if\\(" + COMPARABLE + ex.BRANCOS + COMPARISON_OPERATORS + ex.BRANCOS + COMPARABLE + "\\))";
    
    
    public static void functionRegEx(String arg){       
        ex.confere(FUNCTION_SIGNATURE, arg);
    }
    
    
    public static void functionArgsRegEx(String arg){        
        ex.confere(FUNCTION_ARGS, arg);
    }
    
    
    public static void conditionalRegEx(String arg){
        ex.confere(CONDITIONAL, arg);
    }
    
    
    public static void mathExprRegEx(String arg){
        ex.confere(MATH_EXPRESSION, arg);
    }
    
    
    public static void main(String[] args){
        System.out.println("\n1 - Teste de assinatura de funcoes:");
        functionRegEx("int func1(float asdf);");
        functionRegEx("int func2(Object obj, boolean arg);");
        functionRegEx("void func3();");
        functionRegEx("void funcao01(int a, float b) ;");
        functionRegEx("String funcao2();");
        
        System.out.println("\n2 - Teste de parametros de funcoes:");
        functionArgsRegEx("(int a, float b)");
        functionArgsRegEx("(float media, String nome)");
        
        System.out.println("\n3 - Tetse de condicional");
        conditionalRegEx("if(ano < 1990)");
        conditionalRegEx("if(3*a != 4+t)");
        conditionalRegEx("if(1 > 5)");
        
        System.out.println("\n4 - Teste de expressao matematica:");
        mathExprRegEx("3 + media/3");
        mathExprRegEx("3 + media[z].x");
        mathExprRegEx("soma(x, y) + 1");
        mathExprRegEx("-4 + beta * media[1].x * soma(a,b)/4 * vetor[5].idade");
    }
}
