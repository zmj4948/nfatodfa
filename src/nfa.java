import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class nfa {
    Set<Set<String> Q;
    //finite set of states
    Set<String> Sigma;
    //a finiite alphabet
    Map<String, String> delta;
    //transition function
    String start;
    //start state
    Set<String> accept;
    //accept states


    public nfa(String fileName) throws FileNotFoundException {
        Scanner filescanner = new Scanner(new File(fileName));
        this.Q = null;
        this.Sigma = null;
        this.delta = new TreeMap();
        this.start = null;
        this.accept = null;
        int count = 0;
        while (filescanner.hasNextLine()) {
            String line = filescanner.nextLine();
            if (line.startsWith("#")) {
                //skip because this is a comment
                ;
            } else if (count == 0) {
                //first set of states
                Scanner var4 = new Scanner(line);
                String var9;
                if (this.Q == null) {
                    for (this.Q = new TreeSet(); var4.hasNext(); this.Q.add(var9)) {
                        var9 = var4.next();
                        if (!this.legalState(var9)) {
                            System.out.println("state contains non-alpha-chars  besides @ and - : " + var9);
                            System.exit(1);
                        }
                    }

                    System.out.println("Q = " + this.Q);
                    count++;
                }
            } else if (count == 1) {
                //create the alphabet
                if (this.Sigma == null) {
                    this.Sigma = new TreeSet();
                    String var9;
                    if (!line.startsWith("@")) {
                        Scanner var4 = new Scanner(line);
                        for (; var4.hasNext(); this.Sigma.add(line)) {
                            var9 = var4.next();
                            if (var9.length() != 1) {
                                System.out.println("no symbols more than length  1: " + var9);
                                System.exit(1);
                            }

                            if (!Character.isLetterOrDigit(var9.charAt(0))) {
                                System.out.println("non-alphanumeric symbol and not @: " + var9);
                                System.exit(1);
                            }
                        }
                        this.Sigma.add(".");
                    }else{
                        this.Sigma.add("@");
                    }
                    //add the E, represented by a period
                    System.out.println("Sigma = " + this.Sigma);
                    count++;
                }
            }else if(count==2){
                String[] var5;
                Scanner var4 = new Scanner(line);
                if (this.start == null) {
                    var5 = line.split("\\s+");
                    if (var5.length > 1) {
                        System.out.println("only one start state allowed " + line);
                        System.exit(1);
                    }

                    this.start = var4.next();
                    if (!this.Q.contains(this.start)) {
                        System.out.println("start state " + this.start + " must be in set of states " + this.Q);
                        System.exit(1);
                    }
                }
                count++;

            }else if(count==3) {
                Scanner var4 = new Scanner(line);
                String var9;
                if (this.accept == null) {
                    this.accept = new TreeSet();
                    if (!line.startsWith("@")) {
                        for (; var4.hasNext(); this.accept.add(var9)) {
                            var9 = var4.next();
                            if (!this.Q.contains(var9)) {
                                System.out.println("accept state " + var9 + " not in set of states " + this.Q);
                                System.exit(1);
                            }
                        }
                    }if(this.accept.isEmpty()){
                        //can have no accept states because that can be a thing
                        this.accept.add("@");
                    }
                }
            }else {
                Scanner var4 = new Scanner(line);
                String[] var5;
                var5 = line.split("\\s+");
                if (var5.length != 3) {
                    System.out.println("illegal transition specification " + line);
                    System.exit(1);
                }

                    String var6 = var4.next();
                    String var7 = var4.next();
                    String var8 = var4.next();
                    if (!this.Q.contains(var6) || !this.Q.contains(var8)) {
                        System.out.println("states " + var6 + " and " + var8 + " must both be in " + this.Q);
                        System.exit(1);
                    }

                    if (!this.Sigma.contains(var7)) {
                        System.out.println(var7 + " not in alphabet " + this.Sigma);
                        System.exit(1);
                    }

                    this.delta.put(var6 + "," + var7, var8);
                }
            }
    }

    public nfa() {
        this.accept=null;
        this.start=null;
        this.Q=null;
        this.delta=null;
        this.Sigma=null;

    }

    public Set<String> getQ() {
        return Q;
    }

    public void setQ(Set<Set<String>> q) {
        Q = q;
    }

    public Set<String> getSigma() {
        return Sigma;
    }

    public void setSigma(Set<String> sigma) {
        for(String i: sigma){
            if(i.equals(".")){
                sigma.remove(i);
            }
        }
        Sigma = sigma;
    }

    public Map<String, String> getDelta() {
        return delta;
    }

    public void setDelta(Map<String, String> delta) {
        this.delta = delta;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public Set<String> getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        for(String state: this.Q){

        }
        this.accept = accept;
    }

    private boolean legalState(String var1) {
        for(int var2 = 0; var2 < var1.length(); ++var2) {
            char var3 = var1.charAt(var2);
            if (!Character.isLetterOrDigit(var3) && var3 != '-' && var3 != '@') {
                return false;
            }
        }

        return true;
    }
}
