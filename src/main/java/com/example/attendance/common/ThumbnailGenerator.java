package com.example.attendance.common;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


@Component
public class ThumbnailGenerator {

    public static void generatePdfThumbnail(String pdfFilePath, String thumbnailFilePath) throws IOException {
        try (PDDocument document = PDDocument.load(new File(pdfFilePath))) {
            PDFRenderer pdfRenderer = new PDFRenderer(document);
            BufferedImage image = pdfRenderer.renderImageWithDPI(0, 300);
            resizeAndSaveImage(image, thumbnailFilePath);
        }
    }

    private static void resizeAndSaveImage(BufferedImage image, String thumbnailFilePath) throws IOException {
        int targetWidth = 210;
        int targetHeight = 300;
        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        resizedImage.getGraphics().drawImage(image.getScaledInstance(targetWidth, targetHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);
        ImageIO.write(resizedImage, "png", new File(thumbnailFilePath));
    }
}
