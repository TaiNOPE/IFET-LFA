package com.mycompany.lfa_trab03;


public class LFA_Trab03 {
    public static void main(String[] args) {
        String alphabet[] = {"a", "b"};
        String states[] = {"q0", "q1", "q2", "qf"};
        
        Automata a = new Automata(alphabet, states);
        a.setInitialState("q0");
        a.addFinalState("qf");
        a.setTransition("q0", "a", "q1");
        a.setTransition("q0", "b", "q2");
        a.setTransition("q1", "a", "qf");
        a.setTransition("q1", "b", "q2");
        a.setTransition("q2", "a", "q1");
        a.setTransition("q2", "b", "qf");
        a.setTransition("qf", "a", "qf");
        a.setTransition("qf", "b", "qf");
        
        System.out.println(a.isWordAccepted("ababa"));
    }
}
