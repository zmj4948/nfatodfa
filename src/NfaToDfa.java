import java.io.FileNotFoundException;
import java.util.*;

public class NfaToDfa {
    public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
        Set<Set<T>> sets = new HashSet<Set<T>>();
        if (originalSet.isEmpty()) {
            sets.add(new HashSet<T>());
            return sets;
        }
        List<T> list = new ArrayList<T>(originalSet);
        T head = list.get(0);
        Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
        for (Set<T> set : powerSet(rest)) {
            Set<T> newSet = new HashSet<T>();
            newSet.add(head);
            newSet.addAll(set);
            sets.add(newSet);
            sets.add(set);
        }
        return sets;
    }

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
            Set<Set<String>> power=powerSet(ts);

            nfa_dfa.setQ(power);
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
