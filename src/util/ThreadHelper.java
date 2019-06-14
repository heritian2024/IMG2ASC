package util;

import app.GifConvert;
import app.ImageConvert;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ch on 2019/6/14.
 */
public class ThreadHelper {

    /**
     * Image批处理
     *
     * @param inpath
     * @param outpath
     */
    public static void ImageThread(String inpath, String outpath, int speed) {
        try {
            FileHelper.CheckPath(outpath);
            ImageConvert imageConvert = new ImageConvert(speed);
            ArrayList<File> arrayList = BatchHelper.getAllFiles(inpath);
            for (File file : arrayList) {
                ImageRunnable imageRunnable = new ImageRunnable(imageConvert, file.getPath(), outpath);
                Thread thread = new Thread(imageRunnable);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class ImageRunnable implements Runnable {
        private String inImagePath;
        private String outDirectoryPath;
        private ImageConvert imageConvert;

        ImageRunnable(ImageConvert imageConvert, String inImagePath, String outDirectoryPath) {
            this.imageConvert = imageConvert;
            this.inImagePath = inImagePath;
            this.outDirectoryPath = outDirectoryPath;
        }

        @Override
        public void run() {
            imageConvert.Image2Image(inImagePath, outDirectoryPath);
        }
    }

    /**
     * GIF批处理
     *
     * @param inpath  输入文件夹路径
     * @param outpath 输出文件夹路径
     */
    public static void GifThread(String inpath, String outpath, int speed) {
        try {
            FileHelper.CheckPath(outpath);
            GifConvert gifConvert = new GifConvert(speed);
            ArrayList<File> arrayList = BatchHelper.getAllFiles(inpath);
            for (File file : arrayList) {
                GifRunnable gifRunnable = new GifRunnable(gifConvert, file.getPath(), outpath);
                Thread thread = new Thread(gifRunnable);
                thread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class GifRunnable implements Runnable {
        private String inImagePath;
        private String outDirectoryPath;
        private GifConvert gifConvert;

        GifRunnable(GifConvert gifConvert, String inImagePath, String outDirectoryPath) {
            this.gifConvert = gifConvert;
            this.inImagePath = inImagePath;
            this.outDirectoryPath = outDirectoryPath;
        }

        @Override
        public void run() {
            gifConvert.Gif2Gif(inImagePath, outDirectoryPath);
        }
    }
}
