/**
 * Created by Ramtin on 2017-06-21.
 */

public class Main {

    public static void main(String[] args) {

        ToweringProblem tp = new ToweringProblem();
        tp.readInt();
        tp.solve1(tp.blocks);
        tp.solve2(tp.blocks);
        tp.sortOutput(tp.output,1);
        tp.sortOutput(tp.output, 2);
        for(int i = 0; i<6; i++){
            System.out.print(tp.output[i]+ " ");
        }
    }
}


