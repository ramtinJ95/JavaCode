/**
 * Created by Ramtin on 2016-07-24.
 */
public class Vektor4f {
    private final float x;
    private final float y;
    private final float z;
    private final float w;

    public Vektor4f(float x,float y,float z,float w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public Vektor4f normalize(){
        float length = length();
        return new Vektor4f(x/length, y/length, z/length, w/length);
    }

    private float length() {
        return (float)Math.sqrt(x*x + y*y + z*z + w*w);
    }
    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public float getZ() {
        return z;
    }

    public float getW() {
        return w;
    }
}
