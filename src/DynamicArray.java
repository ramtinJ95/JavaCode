import java.util.Arrays;

/**
 * Created by Ramtin on 2017-06-15.
 */
public class DynamicArray<T> {

    Object[] data;
    int size;

    public DynamicArray(){
        size = 0;
        data = new Object[1];
    }

    public int getSize(){
        return data.length;
    }

    public T get(int index){
        return (T) data[index];
    }

    public void delete(int index){
        data[index] = null;
    }

    public void put(Object element){
        ensureCapacity(size+1);
        data[size++] = element;
    }
    // Checks so that the array got enough room.
    public void ensureCapacity(int minCapacity){
        int oldCapacity = getSize();
        if(minCapacity > oldCapacity){
            int newCapacity = oldCapacity * 2; // we want to dubble size of array
            if(newCapacity < minCapacity) newCapacity = minCapacity;
            data = Arrays.copyOf(data, newCapacity);
        }
    }

}
