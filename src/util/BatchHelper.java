package util;

import app.GifConvert;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by ch on 2019/5/28.
 */
public class BatchHelper {

    public static void ImgBatch(String inpath, String outpath) {

    }

    /**
     * GIF批处理
     *
     * @param inpath  输入文件夹路径
     * @param outpath 输出文件夹路径
     */
    public static void GifBatch(String inpath, String outpath, int speed) {
        try {
            GifConvert gifConvert = new GifConvert(speed);
            ArrayList<File> arrayList = getAllFiles(inpath);
            for (File file : arrayList) {
                gifConvert.Gif2Gif(file.getPath(), outpath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 递归获取路径下的所有文件集合
     *
     * @param path
     * @return
     * @throws Exception
     */
    public static ArrayList<File> getAllFiles(String path) throws Exception {
        //目标集合fileList
        ArrayList<File> fileList = new ArrayList<File>();
        File file = new File(path);
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File fileIndex : files) {
                //如果这个文件是目录，则进行递归搜索
                if (fileIndex.isDirectory()) {
                    getAllFiles(fileIndex.getPath());
                } else {
                    //如果文件是普通文件，则将文件句柄放入集合中
                    fileList.add(fileIndex);
                }
            }
        }
        return fileList;
    }
}
