import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Set;

public class NfaToDfa {
    public static void main(String[] arg)throws FileNotFoundException {
        Scanner var1 = new Scanner(System.in);
        System.out.print("NFA specification file name: ");
        while(var1.hasNextLine()){
            String var2=var1.nextLine();
            System.out.println("NFA: ");
            nfa current_nfa=new nfa(var2);
            System.out.println("DFA: ");
            nfa nfa_dfa=new nfa();
            Set<String> ts= current_nfa.getQ();
            //TO-DO: create power set
            System.out.print("Q_ ="+nfa_dfa.getQ());
            //set is power set
            nfa_dfa.setSigma(current_nfa.getSigma());
            System.out.println("Sigma_ ="+current_nfa);
            nfa_dfa.setStart(current_nfa.getStart());
            System.out.println("s ="+nfa_dfa.getStart());
            //TO-DO: create accept state
            //start state is start state
            //accept is anything that has an accept state
            System.out.println("Output file name :");
            String file=var1.nextLine();
            System.out.println("Writing to file: "+file);

        }
    }
}
