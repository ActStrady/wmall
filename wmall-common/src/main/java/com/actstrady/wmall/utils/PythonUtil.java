package com.actstrady.wmall.utils;

import java.io.DataInputStream;
import java.io.InputStream;

public class PythonUtil {

    public static void executePython(Integer userID, String command){
        try {
            // 进行一个python的调用
            String exe = "python";
            String userId = String.valueOf(userID);
            String[] cmdArr = new String[]{exe, command, userId};
            Process process = Runtime.getRuntime().exec(cmdArr);
            InputStream is = process.getInputStream();
            DataInputStream dis = new DataInputStream(is);
            String str = dis.readLine();
            process.waitFor();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Integer userId = 12;
        String command = "classpath:personal_lbr.py";
        executePython(userId, command);
    }
}
