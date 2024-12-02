package com.mycompany.lfa_trabalho2;


public class LFA_Trabalho2 {
    
    public static void functionRegEx(String arg){
        ExpressaoRegular ex = new ExpressaoRegular();
        
        String VAR_TYPE = "(void|int|float|String|char|boolean|Object)";
        // Um unico argumento: {tipo}{branco|brancos}{nome}({arg}?)
        String FUNCTION_ARG = "(" + VAR_TYPE + ex.BRANCO + ex.IDENT + ")?";
        // Varios argumentos:
        String FUNCTION_ARGS = "(" + FUNCTION_ARG + "(," + ex.BRANCOS + FUNCTION_ARG + ")*)";
        // RegEx
        // {tipo}{espaço}({arg|args|vazio});
        String funcRegEx = "(" + VAR_TYPE + ex.BRANCOS + ex.IDENT + "\\(" + FUNCTION_ARGS + "\\));";
        
        ex.confere(funcRegEx, arg);
    }
    
    
    public static void main(String[] args){
        functionRegEx("int func1(float asdf);");
        functionRegEx("int func2(Object obj, boolean arg);");
        functionRegEx("void func3();");
    }
}
