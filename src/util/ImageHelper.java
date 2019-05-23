package util;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.*;

/**
 * Created by ch on 2019/4/27.
 */
public class ImageHelper {

    /**
     * 实现图像的等比缩放
     *
     * @param source
     * @param targetW
     * @param targetH
     * @return
     */
    public static BufferedImage resize(BufferedImage source, int targetW, int targetH) {
        BufferedImage target = null;
        double sx = (double) targetW / source.getWidth();
        double sy = (double) targetH / source.getHeight();
        //等比缩放
        if (sx < sy) {
            sx = sy;
            targetW = (int) (sx * source.getWidth());
        } else {
            sy = sx;
            targetH = (int) (sy * source.getHeight());
        }
        //
        int type = source.getType();
        if (type == BufferedImage.TYPE_CUSTOM) {
            ColorModel cm = source.getColorModel();
            WritableRaster raster = cm.createCompatibleWritableRaster(targetW, targetW);
            boolean alphaPremultiplied = cm.isAlphaPremultiplied();
            target = new BufferedImage(cm, raster, alphaPremultiplied, null);
        } else {
            target = new BufferedImage(targetW, targetH, type);
        }
        Graphics2D graphics2D = target.createGraphics();
        // smoother than exlax
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        graphics2D.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
        graphics2D.dispose();
        return target;
    }

    /**
     * 实现图像压缩
     *
     * @param inFilePath
     * @param outFilePath
     * @param width
     * @param hight
     * @return
     */
    public static boolean compress(String inFilePath, String outFilePath, int width, int hight) {
        boolean ret = false;
        File file = new File(inFilePath);
        File saveFile = new File(outFilePath);
        InputStream in = null;
        try {
            in = new FileInputStream(file);
//            ret = compress(in,saveFile,width,hight);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            ret = false;
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return ret;
    }

}
