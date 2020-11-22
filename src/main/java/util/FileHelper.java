package util;

import java.io.*;

/**
 * Created by ch on 2019/5/17.
 */
public class FileHelper {
    /**
     * 追加模式写入文件
     *
     * @param content
     * @param file
     */
    public static void WriteFile(String content, String file) {
        BufferedWriter out = null;
        try {
            out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file, true)));
            out.write(content + "\r\n");
        } catch (Exception e) {
            e.printStackTrace();
            //TODO 更换log4j
            System.out.println("写入文件失败");
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 路径检测，自动创建文件
     * @param path
     */
    public static void CheckPath(String path){
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
    }
}
