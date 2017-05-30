/**
 * Created by Ramtin on 2016-07-24.
 */
public class RandomTriangelGenerator {

    private final float m_speed;

    private final float m_triX[];
    private final float m_triY[];
    private final float m_triZ[];

    public RandomTriangelGenerator(float speed, int numVertices){
        m_speed = speed;

        m_triX = new float[numVertices];
        m_triY = new float[numVertices];
        m_triZ = new float[numVertices];

        for(int i = 0; i < m_triY.length; i++){
           // InitTriangle(i);
        }
    }

    private void InitTriangle(int i) {
        m_triX[i] = 2 * ((float)Math.random() - 0.5f); // för att få mellan -1 och 1 som i den här 3D världen är längst till vänser och höger
        m_triX[i] = 2 * ((float)Math.random() - 0.5f);
        m_triZ[i] = ((float)Math.random() + 0.00001f); // så vi inte får exakt 0, ifall det ger division med 0 eller något
    }

    public void RenderAndUpdate(RenderContext target, float delta){
        //target.clear((byte) 0x00);

        int triangleBuilderCounter = 0;
        int x1 = 0;
        int y1 = 0;
        int x2 = 0;
        int y2 = 0;

        for(int i = 0; i < m_triZ.length; i++){
            m_triZ[i] -= delta * m_speed;

            if(m_triZ[i] <= 0){
                //InitTriangle(i);
            }

            int x = (int)(Math.random() *500 +1);
            int y = (int)(Math.random() *500 +1);

            if(x < 0 || x>= target.getWidth() ||(y < 0 || y>= target.getHeight())){
               // InitTriangle(i);
                //continue;
            }

            triangleBuilderCounter++;
            if(triangleBuilderCounter == 1)
            {
                x1 = x;
                y1 = y;
            }
            else if(triangleBuilderCounter == 2)
            {
                x2 = x;
                y2 = y;
            }
            else if(triangleBuilderCounter == 3)
            {
                triangleBuilderCounter = 0;
                Vertex v1 = new Vertex(x1, y1);
                Vertex v2 = new Vertex(x2, y2);
                Vertex v3 = new Vertex(x, y);

               target.fillTriangle(v1, v2, v3);
            }

        }

    }
}
