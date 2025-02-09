package com.mycompany.lfa_trab03;

import java.util.ArrayList;
import java.util.Arrays;


public class Automata {
    private ArrayList<String> stack;
    private ArrayList<String> alphabet;
    private ArrayList<String> states;
    private ArrayList<String> finalStates;
    private String initialState;
    private String[][] transitionMatrix;
    
    
    
    public Automata(String alphabet[], String states[]){
        this.stack = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.states = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.initialState = "";
        this.transitionMatrix = new String[states.length][alphabet.length];
                
        this.alphabet.addAll(Arrays.asList(alphabet));
        this.states.addAll(Arrays.asList(states));
    }
    
    
    public boolean isLetterValid(String letter){
        return this.alphabet.contains(letter);
    }
    
    
    public boolean isStateValid(String state){
        return this.states.contains(state);
    }
    
    
    public boolean isFinalStateValid(String state){
        return this.finalStates.contains(state);
    }
    
    
    public void setInitialState(String state){
        if( !this.isStateValid(state) ){
            System.out.println("hell nah");
            return;
        }
        System.out.println("ok");
        this.initialState = state;
    }
    
    
    public void addFinalState(String state){
        if( !this.isStateValid(state) ){
            System.out.println("hell nah");
            return;
        }
        System.out.println("ok");
        this.finalStates.add(state);
    }
    
    
    public void setTransition(String state, String read, String target){
        if( !this.isStateValid(state) ){
            System.out.println("invalid state");
            return;
        }
        if( !this.isLetterValid(read) ){
            System.out.println("invalid letter");
            return;
        }
        if( !this.isStateValid(target) ){
            System.out.println("invalid target state");
            return;
        }
        
        transitionMatrix
                [this.states.indexOf(state)]
                [this.alphabet.indexOf(read)] 
                = target;
    }
    
    
    public boolean isWordAccepted(String word){
        String currentState = initialState;
        
        for(int i = 0; i < word.length(); i++){
            String letter = String.valueOf(word.charAt(i));
            System.out.println("testing letter: " + letter);
            if( !isLetterValid(letter) ){
                System.out.println("invalid letter");
                return false;
            }
            
            currentState = transitionMatrix
                    [states.indexOf(currentState)]
                    [alphabet.indexOf(letter)];
            System.out.println("moved to: " + currentState);
        }
        
        return finalStates.contains(currentState);
    }
}
