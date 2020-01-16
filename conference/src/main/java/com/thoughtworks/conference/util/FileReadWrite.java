package com.thoughtworks.conference.util;

import com.thoughtworks.conference.exception.TrackException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

public class FileReadWrite {

    /**
     * 读取文件内容
     * @param fileName
     * @return
     * @throws IOException
     */
    public static List<String> readTxtFile(String fileName) throws IOException {

        String filePath =  Class.class.getClass().getResource("/").getPath()  + fileName;

        File file = new File(filePath);
        if(!file.exists()){
            new TrackException(FileConstant.FILE_NOT_EXSIT+":"+file);
        }
        List<String> lines = new ArrayList<String>();
        FileInputStream fis = new FileInputStream(file);

        InputStreamReader reader = new InputStreamReader(fis,FileConstant.ENCODING);
        BufferedReader bufferdReader = new BufferedReader(reader);

        String lineTxt = "";
        while((lineTxt = bufferdReader.readLine()) != null){
            lines.add(lineTxt);
        }
        bufferdReader.close();
        return lines;
    }

    /**
     * 写入文件内容:NIO方式
     * @param lines
     * @param fileOutName
     * @throws IOException
     */
    public static void writeTxtFile(List<String> lines,String fileOutName) throws IOException {

        String filePath =  Class.class.getClass().getResource("/").getPath()  + "out_"+fileOutName;

        FileOutputStream fos = new FileOutputStream(filePath, false);
        FileChannel channel = fos.getChannel();
        int i = lines.size()-1;
        StringBuffer content = new StringBuffer();
        for(String line:lines) {
            content.append(line).append("\r\n");
        }
        ByteBuffer buf = ByteBuffer.wrap(content.toString().getBytes());
        buf.put(content.toString().getBytes());
        buf.flip();
        channel.write(buf);
        channel.close();
        fos.close();
    }

}
