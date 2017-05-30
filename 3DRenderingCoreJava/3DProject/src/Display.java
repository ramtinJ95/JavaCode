
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;


/**
 * Created by Ramtin on 2016-07-22.
 */
public class Display extends Canvas{
    /** Window used for display*/
    private final JFrame m_frame;
    /** The bitmap representing the final image to display */
    private final RenderContext m_frameBuffer;
    /** Used to display the framebuffer in the window */
    private final BufferedImage m_displayImage;
    /** The pixels of the dispaly image, as an array of byte components */
    private final byte[] m_displayComponents;
    /** The buffers in the Canvas */
    private final BufferStrategy m_bufferStrategy;
    /** A Graphics object that can draw into the canvas's buffers */
    private final Graphics m_graphics;



    public Display(int width, int height, String title){

        Dimension size = new Dimension(width, height);
        setPreferredSize(size);

        m_frameBuffer = new RenderContext(width, height);
        m_displayImage = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        m_displayComponents = ((DataBufferByte)m_displayImage.getRaster().getDataBuffer()).getData();

        m_frameBuffer.clear((byte) 0x40);
        for(int i = 0; i < 575; i++){
            m_frameBuffer.drawPixel(i, i+1, (byte)0x00, (byte)0x00, (byte)0x00, (byte)0xFF);

        }

        m_frame = new JFrame();
        m_frame.add(this);
        m_frame.pack(); // resize Jframe so it displays entire canvas
        m_frame.setResizable(false);
        m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m_frame.setTitle(title);
        m_frame.setLocationRelativeTo(null);
        m_frame.setVisible(true);

        createBufferStrategy(1);
        m_bufferStrategy = getBufferStrategy();
        m_graphics = m_bufferStrategy.getDrawGraphics();
    }

    /**
     * Framebuffer is the entire bitmap, and we are copying it to the
     * displaycomponents so that we can actually display stuff.
     */
    public void swapBuffers(){
        m_frameBuffer.copyToByteArray(m_displayComponents);
        m_graphics.drawImage(m_displayImage, 0, 0, m_frameBuffer.getWidth(), m_frameBuffer.getHeight(), null);
        m_bufferStrategy.show(); // this method shows our buffer to our canvas
    }

    public RenderContext getFrameBuffer() {
        return m_frameBuffer;
    }
}
