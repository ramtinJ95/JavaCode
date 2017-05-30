import java.util.Arrays;

/**
 * Created by Ramtin on 2016-07-22.
 *
 * This is adding the concept of images to our "engine".
 *
 */
public class Bitmap {

    private final int m_width;
    private final int m_height;
    private final byte m_components[]; // väljer char för att det är max 256 som är allt som behövs för all färg kombioantioner

    public Bitmap(int width, int height){
        m_width = width;
        m_height = height;
        m_components = new byte[width * height *4];


    }

    /**
     * Sets every component of every pixel in the array to particular color
     * ie to a particular number between 0-256, so if all of them are 0
     * the canvas should be colored in black, if all are 255 it will be white
     *
     * @param color a number which dictates the value of all components in the pixel array
     */
    public void clear(byte color){
        Arrays.fill(m_components, color);
    }

    /**
     * Use this method to draw a singel pixel.
     * Notice that we have ABGR and not ARBG. This is because java has in its
     * bufferedImage class a method that allows me to format how my bitmap is used
     * but that method when working with bytes takes in B G R and not RBG.
     *
     * @param x position of the pixel
     * @param y position of the pixel
     * @param a alpha value of the pixel
     * @param r red value of the pixel
     * @param g green value of the pixel
     * @param b blue value of the pixel
     */
    public void drawPixel(int x, int y, byte a, byte b, byte g, byte r){
        int index = (x+ y * m_width) * 4; // multiply with 4 because every pixel has 4 components
        m_components[index] = a;
        m_components[index + 1] = b;
        m_components[index + 2] = g;
        m_components[index + 3] = r;
    }

    /**
     * copies the Bitmap into a BGR byte array
     * @param destination the byte array we want to copy into
     */
    public void copyToByteArray(byte[] destination){
        for(int i = 0; i < m_width * m_height; i++) {
            destination[i * 3] = m_components[i * 4 + 1]; // multiply with 3 on the array that gets rendered because we are discarding the alpha for now
            destination[i * 3 + 1] = m_components[i * 4 + 2];
            destination[i * 3 + 2] = m_components[i * 4 + 3];
        }
    }

    public int getWidth(){ return m_width; }
    public int getHeight(){ return m_height ;}

}
