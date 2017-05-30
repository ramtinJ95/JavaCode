public class Main {

    public static void main(String[] args) {
        Display display = new Display(800, 600, "3D software test");
        RenderContext target = display.getFrameBuffer();
        RandomTriangelGenerator test = new RandomTriangelGenerator(0.00002f, 3);

        Vertex minYVert = new Vertex(100, 100);
        Vertex midYVert = new Vertex(150, 200);
        Vertex maxYVert = new Vertex(80, 300);


        while(true){
            target.clear((byte)0x00);

            test.RenderAndUpdate(target, 0.00000001f);
            //target.fillTriangle(minYVert, midYVert, maxYVert);
            display.swapBuffers();
        }
    }
}
