/**
 * Created by Ramtin on 2017-06-15.
 */
public class DynamicArrayDemo {

    public static void main(String[] args){
        DynamicArray<Integer> da = new DynamicArray<>();

        da.put(1);
        System.out.println("Size: "+ da.getSize());
        da.put(2);
        System.out.println("Size: "+ da.getSize());
        da.put(3);
        System.out.println("Size: "+ da.getSize());
        System.out.println(da.get(2));

        da.delete(0);
        System.out.println(da.get(0));


    }
}
