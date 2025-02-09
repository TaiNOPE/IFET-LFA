package com.mycompany.lfa_trab03;


public class LFA_Trab03 {
    public static Automata a;
    
    public static void main(String[] args) {
        String alphabet[] = {"a", "b"};
        String states[] = {"q0", "q1", "q2", "qf"};
        
        a = new Automata(alphabet, states);
        a.setInitialState("q0");
        a.addFinalState("qf");
        //current_state, tape_read, stack_read, stack_write, next_state
        a.setTransition("q0", "a", "", "", "q1");
        a.setTransition("q0", "b", "", "", "q2");
        a.setTransition("q1", "a", "", "", "qf");
        a.setTransition("q1", "b", "", "", "q2");
        a.setTransition("q2", "a", "", "", "q1");
        a.setTransition("q2", "b", "", "", "qf");
        a.setTransition("qf", "a", "", "", "qf");
        a.setTransition("qf", "b", "", "", "qf");
        
        testWord("abba");
        testWord("abba");
        testWord("aaa");
        testWord("bbb");
        testWord("abbac");
        testWord("c");
        testWord("abababa");
    }
    
    
    public static void testWord(String word){
        System.out.println("\n\n\nTestando palavra: " + word);
        
        if( a.isWordAccepted(word) ){
            System.out.println("PALAVRA ACEITA.");
        }else{
            System.out.println("PALAVRA REJEITADA.");
        }
    }
}
