package com.mycompany.lfa_trab03;



import java.util.ArrayList;
import java.util.Arrays;



public class Automata {
    // Pilha.
    private final ArrayList<String> stack;
    
    // Alfabeto, contendo as letras aceitas pelo automato.
    private final ArrayList<String> alphabet;
    
    // Estados do automato.
    private final ArrayList<String> states;
    
    // Estados do automato. (Deve ser um estado contido em [states]).
    private final ArrayList<String> finalStates;
    
    // Estado inicial
    private String initialState;
    
    // Matriz de transição, onde cada célula é um vetor contendo transições
    // possíveis para diferentes letras lidas da pilha.
    private final ArrayList<String[]>[][] transitionMatrix;
    
    
    public Automata(String alphabet[], String states[]){
        this.stack = new ArrayList<>();
        this.alphabet = new ArrayList<>();
        this.states = new ArrayList<>();
        this.finalStates = new ArrayList<>();
        this.initialState = "";
        
        // Inicia matriz de transição.
        // [alphabet.length+1] pois será adicionado a entrada vazia;
        this.transitionMatrix = new ArrayList[states.length][alphabet.length + 1];
        
        // Inicia todas as células da matriz de transição com um ArrayList<String[]> vazio.
        for(int sta = 0; sta < states.length; sta++){
            for(int alp = 0; alp < alphabet.length + 1; alp++){
                this.transitionMatrix[sta][alp] = new ArrayList<>();
            }
        }
        
        // Atribui o alfabeto (passado pelo construtor do objeto) aceito pelo automato.
        this.alphabet.addAll(Arrays.asList(alphabet));
        
        // Adiciona entrada vazia no alfabeto.
        this.alphabet.add("");
        
        // Atribui os estados (passado pelo construtor do objeto) aceitos pelo automato.
        this.states.addAll(Arrays.asList(states));
    }
    
    
    // Retorna TRUE se [letter] for um item no ALFABETO.
    public boolean isLetterValid(String letter){
        return this.alphabet.contains(letter);
    }
    
    
    // Retorna TRUE se [state] for um item nos ESTADOS.
    public boolean isStateValid(String state){
        return this.states.contains(state);
    }
    
    
    // Retorna TRUE se [state] for um item nos ESTADOS FINAIS.
    public boolean isFinalStateValid(String state){
        return this.finalStates.contains(state);
    }
    
    
    // Define o estado inicial do automato.
    public void setInitialState(String state){
        if( !this.isStateValid(state) ){
            System.out.println("Estado inicial invalido: " + state);
            return;
        }
        this.initialState = state;
        System.out.println("Estado inicial definido: " + state);
    }
    
    
    // Adiciona um estado final ao automato.
    // É possível ter vários estados finais.
    public void addFinalState(String state){
        if( !this.isStateValid(state) ){
            System.out.println("Estado final invalido: " + state);
            return;
        }
        this.finalStates.add(state);
        System.out.println("Estado final adicionado: " + state);
    }
    
    
    // Adiciona uma transição de um estado para outro.
    // Se tiver em [state] e for lido [tapeRead] na FITA, e for lido [stackRead] na PILHA, 
    // escreve [stackWrite] na PILHA e vai para o estado [nextState].
    public void setTransition(String state, String tapeRead, String stackRead, String stackWrite, String nextState){
        if( !this.isStateValid(state) ){
            System.out.println("Estado atual invalido: " + state);
            return;
        }
        if( tapeRead.length() > 1 ){
            System.out.println("Letra invalida: " + tapeRead);
            return;
        }
        if( !this.isStateValid(nextState) ){
            System.out.println("Estado destino invalido: " + nextState);
            return;
        }
        
        // Cria ujm vetor com a transição, que será salva na célula da matriz.
        String transition[] = new String[4];
        transition[0] = tapeRead;
        transition[1] = stackRead;
        transition[2] = stackWrite;
        transition[3] = nextState;
        
        System.out.println("Transicao adicionada: " + state + "; tr: " + tapeRead + "; sr: " + stackRead + "; sw: " + stackWrite + "; tgt: " + nextState);
        
        int stateId = this.states.indexOf( state );
        int letterId = this.alphabet.indexOf( tapeRead );
        this.transitionMatrix[stateId][letterId].add( transition );
    }
    
    
    // Teste se uma palavra é aceita pelo autômato.
    public boolean isWordAccepted(String word){
        System.out.println("\n\n\nTestando a palavra: " + word);
        String currentState = initialState;
        this.stack.clear();
        
        // Percorre cada letra da palavra.
        for(int i = 0; i < word.length(); i++){
            String letter = String.valueOf(word.charAt(i));
            System.out.println("\nTestando letra: " + letter);
            
            // Verifica se a letra é valida (se o alfabeto posui tal letra).
            if( !isLetterValid(letter) ){
                System.out.println("\tLetra nao encontrada no alfabeto.");
                return false;
            }
            
            // Cria um vetor com as possíveis transições, dado o ESTADO ATUAL
            // e a LETRA que está sendo testada.
            ArrayList transitions = transitionMatrix
                    [states.indexOf(currentState)]
                    [alphabet.indexOf(letter)];
            
            System.out.println("\tVerificando transicoes para: " + letter  + " em " + currentState + "...");
            
            // Testa cada uma das possíveis transições.
            for(int j = 0; j < transitions.size(); j++){
                String[] tr = (String[]) transitions.get(j);
                
                // Valor que DEVE SER LIDO da FITA.
                String tapeRead = tr[0];
                
                // Valor que DEVE SER LIDO da PILHA.
                String stackRead = tr[1];
                
                // Valor que DEVE SER ESCRITO na PILHA caso O VALOR LIDO DA FITA
                // E PILHA sejam satisfeitos.
                String stackWrite = tr[2];
                
                // Próximo estado que será transitado CASO A LEITURA DE FITA E PILHA
                // sejam satisfeitos.
                String nextState = tr[3];
                
                // Testa se o valor lido da fita é compatível com esta transição.
                if( tapeRead.equals(letter) ){
                    System.out.println("\tTestando transicao: tr = " + tapeRead + "; sr = " + stackRead + "; sw = " + stackWrite + "; ns = " + nextState);
                    
                    String lastStackItem;
                    
                    // Verifica se precisa ler algo da pilha.
                    if( !stackRead.equals("") ){
                        
                        // Verifica se está tentando ler algo de uma pilha vazia.
                        if( !this.stack.isEmpty() ){
                            lastStackItem = this.stack.removeLast();
                            System.out.println("\t\tValor lido da pilha: " + lastStackItem);
                        }else{
                            System.out.println("\t\tPilha vazia. Nao e possivel ler.");
                            continue;
                        }
                        
                        // Verifica se o valor lido da PILHA condiz com o esperado.
                        // Caso não, a palavra é rejeitada imediatamente.
                        if( !lastStackItem.equals(stackRead) ){
                            System.out.println("\t\tValor lido da pilha invalido. Esperado: " + stackRead + "; Encontrado: " + lastStackItem + ";");
                            
                            // Interrompe o teste e pula para proxima transição encontrada.
                            // A palavra não é aceita pois o item lido da pilha
                            // não condiz com o valor esperado.
                            
                            continue;
                        }
                    }
                    
                    // Verifica se precisa escrever algo na pilha, e escreve caso preciso.
                    if( !stackWrite.equals("") ){
                        this.stack.addLast( stackWrite );
                        System.out.println("\t\tAdicionando na pilha: " + stackWrite);
                    }
                    
                    // Transita para o proximo estado caso a leitura e escrita
                    // da pilha forem satisfeitos.
                    currentState = nextState;
                    
                    // Interrompe o loop de TESTE DE TRANSIÇÕES PARA ESTA LETRA.
                    // Este loop é executado novamente para a próxima letra.
                    break;
                }
            }
   
            // Imprime a pilha.
            System.out.print("\t\tPilha: ");
            this.stack.forEach( (value) -> {
                System.out.print(value + " ");
            });
            System.out.println("");
            // Neste ponto a letra lida não teve inconsistÊncias.
            System.out.println("\tNovo estado atual: " + currentState);
        }
        
        if( !finalStates.contains(currentState) ){
            System.out.println("O automato nao parou em um estado final.");

            // Verifica se é possivel fazer movimentos vazios do estado atual até um estado final.
            System.out.println("Terminou em um estado nao final.");
            System.out.println("Tentando chegar em um estado final por movimentos vazios...");
            
            var transitions = 
                    this.transitionMatrix
                            [this.states.indexOf(currentState)]
                            [this.alphabet.indexOf("")]; // Entrada vazia
            
            for( int i = 0; i < transitions.size(); i++){
                String[] tr = (String[]) transitions.get(i);
                String nextState = tr[3];
                
                if( this.finalStates.contains(nextState) ){
                    System.out.println("Foi possivel chegar em um estafo final.");
                    return true;
                }
            }
            
            // Se nao for possivel chegar em um estado final por m,ovimentos vazios
            System.out.println("Nao foi possivel chegar em um estado final");
            return false;
        }
        
        System.out.println("O automato parou em um estado final.");
        return true;
    }
}
