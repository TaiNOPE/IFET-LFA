package com.mycompany.lfa_trab03;


public class LFA_Trab03 {
    
    
    public static void main(String[] args) {
        ex03();
    }
    
    
    public static void ex03(){
        Automata a;
        String alphabet[] = {"a", "b", "c"};
        String states[] = {"q0", "q1", "q2", "q3", "qf"};
        
        a = new Automata(alphabet, states);
        a.setInitialState("q0");
        a.addFinalState("qf");
        //current_state, tape_read, stack_read, stack_write, next_state
        a.setTransition("q0", "a", "", "A", "q0");
        a.setTransition("q0", "b", "A", "", "q1");
        a.setTransition("q1", "b", "A", "", "q1");
        a.setTransition("q1", "b", "", "B", "q2");
        a.setTransition("q2", "b", "", "B", "qf");
        a.setTransition("q2", "c", "B", "", "qf");
        a.setTransition("qf", "c", "B", "", "qf");
        a.setTransition("qf", "", "A", "", "q3");
        a.setTransition("qf", "", "B", "", "q3");
        a.setTransition("q3", "", "A", "", "q3");
        a.setTransition("q3", "", "B", "", "q3");
        
        a.isWordAccepted("abba");
        a.isWordAccepted("aabbbc");
        a.isWordAccepted("aabbbcc");
        a.isWordAccepted("aaa");
        a.isWordAccepted("aabb");
        a.isWordAccepted("aab");
    }
    
    
    public static void ex04(){
        /*
        Automata a;
        String alphabet[] = {"a", "b", "c"};
        String states[] = {"q0", "q1", "q2", "q3", "qf"};
        
        a = new Automata(alphabet, states);
        a.setInitialState("q0");
        a.addFinalState("q1");
        a.addFinalState("qf");
        //current_state, tape_read, stack_read, stack_write, next_state
        a.setTransition("q0", "a", "", "A", "q0");
        a.setTransition("q0", "b", "A", "", "q1");
        a.setTransition("q1", "b", "A", "", "q1");
        a.setTransition("q1", "b", "", "B", "q2");
        a.setTransition("q2", "b", "", "B", "q2");
        a.setTransition("q2", "c", "B", "", "q3");
        a.setTransition("q3", "c", "B", "", "q3");
        a.setTransition("q3", "", "", "", "qf");
        
        a.isWordAccepted("abba");
        a.isWordAccepted("aabbbc");
        a.isWordAccepted("aabbbcc");
        a.isWordAccepted("aabbc");
        a.isWordAccepted("aaa");
        a.isWordAccepted("aabb");
*/
    }
}
