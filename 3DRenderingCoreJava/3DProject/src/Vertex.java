/**
 * Created by Ramtin on 2016-07-22.
 */
public class Vertex {
    private float m_x;
    private float m_y;

    public Vertex(float x, float y){
        m_x = x;
        m_y = y;
    }
    public float getX(){ return m_x;}
    public float getY(){ return m_y;}

    public void setX(float x) {m_x = x;}
    public void setY(float y) {m_y = y;}

    /**
     * Returns a given triangles area using the cross product of vectors.
     * @param b
     * @param c
     * @return area
     */
    public float triangleArea(Vertex b, Vertex c){
        /** Basic vector substraction here */
        float x1 = b.getX() - m_x;
        float y1 = b.getY() - m_y;
        float x2 = c.getX() - m_x;
        float y2 = c.getY() - m_y;

        return((x1 * y2 - x2 *y1) /2.0f);
    }

}
