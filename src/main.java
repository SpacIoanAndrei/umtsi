import java.util.*;

public class main {
    public static void main(String[] args) {
        System.out.println(problem());

    }

    /**
     *lists A,B with the property average(A)==average(B) is equivalent to finding a sum sublistSum of x elements
     * such as sublistSum = (sumOfAllElements * x) / numberOfElements.
     * This method also computes the sums of different combinations of splitting the array and saves
     * them in a list. This allows for faster execution time because each sum is calculated once,
     * but uses more memory.
     *
     * input data is introduced in console
     * @return true if it is possible to split array in lists A,B such as average(A)==average(B)
     */
    public static boolean problem(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce length of array:");
        int length = scanner.nextInt();
        int[] input = new int[length];

        //read elements of array
        System.out.println("Introduce the elements of the array, separated with space:");
        for (int i=0;i<length;i++)
            input[i] = scanner.nextInt();

        // check if the array can be split; it rules out some cases, but not all of them
        int suma = Arrays.stream(input).sum();
        boolean flag = false;
        for (int i=1;i<length;i++)
            if ((i*suma) % length == 0) {
                //System.out.println(i*suma+" "+length);
                flag = true;
                break;
            }
        if (!flag)
            return  false;


        List<Set<Integer>> partialSums = new ArrayList<>();
        // create sets
        for (int i=0;i<length;i++)
            partialSums.add(new HashSet<>());
        partialSums.get(0).add(0);

        //calculate the sums of the possible subarrays of input array
        for (int i=1;i<length;i++){                         //parcurgem datele de la intrare
            for (int j=i;j>0;j--){                          //parcurgem seturile din lista, de la submultimile mari la cele mici
                                                            //exemplu: pe j=5 -> sumele pentru submultimile din input cu 5 elemente
                for (int k : partialSums.get(j-1)){          //calculam pe fiecare set curent k, suma dintre valoarea de pe intrare de pe pozitia i
                                                            //si sumele din setul anterior (k-1)
                    partialSums.get(j).add(input[i] + k);
                }
            }
        }

        for (int i=1; i< length;i++)                        //check if there is a sum for formula, then the array can be split
            if ((i*suma) % length == 0 && partialSums.get(i).contains(i*suma/length))
                return true;
        return false;
    }
}
