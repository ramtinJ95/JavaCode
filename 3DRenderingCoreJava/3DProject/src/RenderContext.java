/**
 * Created by Ramtin on 2016-07-22.
 *
 * In this class we are just separating some stuff we could have done in Bitmap which is filling in shapes and
 * creating the scanbuffer, and implementing the ScanConversionRasterization algorithm.
 */
public class RenderContext extends Bitmap {

    /** This is the ScanBuffer array with the min and max values  */
    private final int m_scanBuffer[];

    public RenderContext(int width, int height){
        super(width, height);
        m_scanBuffer = new int[height *2];
    }

    /**
     *  basically accomodiates the scanbuffer to the image we want to draw.
     * @param yCoord of the pixel row in our bitmap
     * @param xMin where to start drawing
     * @param xMax where to stop drawing
     */
    public void drawScanBuffer(int yCoord, int xMin, int xMax){
        m_scanBuffer[yCoord * 2] = xMin;
        m_scanBuffer[yCoord * 2 + 1 ] = xMax;
    }

    /**
     * Fills in the shape that the scanbuffer specifies, although for now the color is
     * hardcoded because obviously most of this code will change in a near future.
     */
    public void fillShape(int yMin, int yMax){
        for(int j = yMin; j < yMax; j++){
            int xMin = m_scanBuffer[j * 2];
            int xMax = m_scanBuffer[j * 2 + 1];

            for(int i = xMin; i <xMax; i++){
                drawPixel(i, j, (byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF);
            }
        }
    }

    /**
     * draw a line between to points using Brisenhams line algorithm, although it is a simplified version
     * of that algorithm because the way the bitmap is made with the scanbuffer and all we only really need
     * to take into account the y_kooardinates.
     * @param minYVert
     * @param maxYVert
     * @param whichSide
     */
    private void scanConvertLine(Vertex minYVert, Vertex maxYVert, int whichSide){
        int yStart = (int)minYVert.getY();
        int yEnd = (int)maxYVert.getY();
        int xStart = (int)minYVert.getX();
        int xEnd = (int)maxYVert.getX();

        int yDist = yEnd - yStart;
        int xDist = xEnd - xStart;

        if(yDist <= 0){ // om vi inte har någon y så funkar ju inte ens sccanbuffer
            return;
        }
        float xStep = (float)xDist/(float)yDist; //basically slope, ie how much we move in x for every movement in y
        float currentX = (float) xStart;
        for(int j = yStart; j < yEnd; j++){
            m_scanBuffer[j *2 + whichSide] = (int) currentX;
            currentX += xStep;
        }
    }

    public void scanConvertTriangel(Vertex minYVert, Vertex midYVert, Vertex maxYVert, int whichSide){
        scanConvertLine(minYVert, maxYVert, 0 + whichSide);
        scanConvertLine(minYVert, midYVert, 1 - whichSide);
        scanConvertLine(midYVert, maxYVert, 1 - whichSide);
    }

    /**
     * Fills a triangel given 3 vertices
     * @param v1
     * @param v2
     * @param v3
     */
    public void fillTriangle(Vertex v1, Vertex v2, Vertex v3){
        Vertex minYVert = v1;
        Vertex midYVert = v2;
        Vertex maxYVert = v3;
        /** These 3 if statements are there to make sure that the order of vertices
         * match the input requirement for the scanConvertTriangel method.
         */
        if(maxYVert.getY() < midYVert.getY()){
            Vertex temp = maxYVert;
            maxYVert = midYVert;
            midYVert = temp;
        }
        if(midYVert.getY() < minYVert.getY()){
            Vertex temp = midYVert;
            midYVert = minYVert;
            minYVert = temp;
        }
        if(maxYVert.getY() < midYVert.getY()){
            Vertex temp = maxYVert;
            maxYVert = midYVert;
            midYVert = temp;
        }

        float area = minYVert.triangleArea(maxYVert, midYVert);
        int handedness = (int)area;
        if(handedness >= 0){
            handedness = 1;
        }else{
            handedness = 0;
        }

        scanConvertTriangel(minYVert, midYVert, maxYVert, handedness);
        fillShape((int)minYVert.getY(), (int)maxYVert.getY());
    }

}
