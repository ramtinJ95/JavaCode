import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 * Created by Ramtin on 2017-06-21.
 */
public class ToweringProblem {
    public int[] blocks = new int[8];
    public int[] output = new int[6];

    private int[] output2 = new int[3];

    public ToweringProblem(){
    }

    public void readInt(){
        String data;
        try(BufferedReader in = new BufferedReader(new InputStreamReader((System.in)))){
            data = in.readLine();
            String[] tokens = data.split(" ");
            int i = 0;
            for(String token : tokens){
                blocks[i] = Integer.parseInt(token);
                i++;
            }

            } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public int getNumbers(int[] blocks, int index){
        return blocks[index];
    }
    public int[] solve1(int[] blocks){
        int sum1 = getNumbers(blocks, 6);
        for(int a = 0; a < (blocks.length - 2); a++){
            for(int b = 0; b <(blocks.length - 2); b++){
                for(int c = 0; c <(blocks.length - 2); c++){
                    int i,j,k = 0;
                    i = blocks[a];
                    j = blocks[b];
                    k = blocks[c];
                    if(i + j + k == sum1){
                        if((i !=j) && (j!= k) && (i != k)){
                            output[0] = i;
                            output[1] = j;
                            output[2] = k;
                        }

                    }
                }
            }
        }
        return output;
    }
    public int[] solve2(int[] blocks){
        int sum2 = getNumbers(blocks, 7);
        for(int a = 0; a < (blocks.length - 2); a++){
            for(int b = 0; b <(blocks.length - 2); b++){
                for(int c = 0; c <(blocks.length - 2); c++){
                    int i,j,k = 0;
                    i = blocks[a];
                    j = blocks[b];
                    k = blocks[c];
                    if(i + j + k == sum2){
                        if((i !=j) && (j!= k) && (i != k)){
                            output[3] = i;
                            output[4] = j;
                            output[5] = k;
                        }

                    }
                }
            }
        }
        return output;
    }
    public void sortOutput(int[] output, int choice){
       if(choice == 1){
           output2[0] = output[0];
           output2[1] = output[1];
           output2[2] = output[2];
           Arrays.sort(output2);
           output[0] = output2[2];
           output[1] = output2[1];
           output[2] = output2[0];
       }
        if(choice == 2){
            output2[0] = output[3];
            output2[1] = output[4];
            output2[2] = output[5];
            Arrays.sort(output2);
            output[3] = output2[2];
            output[4] = output2[1];
            output[5] = output2[0];
        }
    }
}