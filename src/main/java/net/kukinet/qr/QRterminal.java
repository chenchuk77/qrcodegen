package net.kukinet.qr;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by chen on 1/28/17.
 */
public class QRterminal {
    private BufferedImage bim;

    // default ctor for testing
    public QRterminal(){
        this("/home/chen/dev/qrgen/example.png");
    }

    // instantiating with a simple filename
    public QRterminal(String filename){
        try {
            bim = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // instantiating with a file object
    public QRterminal(File file){
        try {
            bim = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toAsciiBitmap(){
        StringBuilder sb = new StringBuilder();
        for (int rows=0; rows<bim.getHeight(); rows++){
            for (int cols=0; cols<bim.getWidth(); cols++){
                int x = bim.getRGB(rows, cols);
                if (x == -1){
                    // white
                    sb.append(" ");
                    sb.append(" ");
                } else {
                    // black
                    sb.append((char)219);
                    sb.append((char)219);
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

//    public static void main(String[] args) {
//        try {
//            BufferedImage bim = ImageIO.read(new File("/home/chen/dev/qrgen/example.png"));
//            int p = 999;
//            // probably - white:[-1] black:[-16777216]
//            System.out.println();
//            int black_ascii=219;
//            for (int rows=0; rows<bim.getHeight(); rows++){
//                for (int cols=0; cols<bim.getWidth(); cols++){
//                    int x = bim.getRGB(rows, cols);
//                    if (x == -1){
//                        // white
//                        System.out.print(" ");
//                        System.out.print(" ");
//                    } else {
//                        // black
//                        System.out.print((char)black_ascii);
//                        System.out.print((char)black_ascii);
//                    }
//                }
//                System.out.println();
//            }
////            bim.getHeight();
////            bim.getWidth();
////            System.out.println(bim.getHeight());
////            System.out.println(bim.getWidth());
////
////            p = bim.getRGB(124,125);
////            System.out.println(p);
////            p = bim.getRGB(350,351);
////            System.out.println(p);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
}
