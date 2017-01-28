package net.kukinet.qr;

/**
 * Created by chen on 1/27/17.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRGen {

    /**
     * @param args
     * @throws WriterException
     * @throws IOException
     */
    public static void main(String[] args) throws WriterException, IOException {

        if (args.length != 2){
            System.out.println(args.length);

            System.out.println("usage: ");
            System.out.println("java -cp [jarfile] [pkg_name] -Durl=http://1.2.3 -Dfilename=111.png");
        }

        String url = System.getProperty("url");
        String filename = System.getProperty("filename");

        System.out.println(url);
        System.out.println(filename);

        String qrCodeText = url;
        //String filePath = "D:\\Pankaj\\JD.png";
        String filePath = filename;
        int size = 125;
        String fileType = "png";
        File qrFile = new File(filePath);
        createQRImage(qrFile, qrCodeText, size, fileType);
        System.out.println();
        System.out.println("DONE");

        // get a terminal representation
        QRterminal terminal = new QRterminal(qrFile);
        System.out.println(terminal.toAsciiBitmap());

    }

    private static void createQRImage(File qrFile, String qrCodeText, int size,
                                      String fileType) throws WriterException, IOException {
        // Create the ByteMatrix for the QR-Code that encodes the given String
        Hashtable hintMap = new Hashtable();
        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix byteMatrix = qrCodeWriter.encode(qrCodeText,
                BarcodeFormat.QR_CODE, size, size, hintMap);
        // Make the BufferedImage that are to hold the QRCode
        int matrixWidth = byteMatrix.getWidth();
        BufferedImage image = new BufferedImage(matrixWidth, matrixWidth,
                BufferedImage.TYPE_INT_RGB);
        image.createGraphics();

        Graphics2D graphics = (Graphics2D) image.getGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, matrixWidth, matrixWidth);
        // Paint and save the image using the ByteMatrix
        graphics.setColor(Color.BLACK);

        for (int i = 0; i < matrixWidth; i++) {
            for (int j = 0; j < matrixWidth; j++) {
                if (byteMatrix.get(i, j)) {
                    graphics.fillRect(i, j, 1, 1);
                }
            }
        }
        ImageIO.write(image, fileType, qrFile);
    }
}
