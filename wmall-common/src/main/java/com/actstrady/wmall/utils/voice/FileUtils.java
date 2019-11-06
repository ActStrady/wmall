package com.actstrady.wmall.utils.voice;

import java.io.File;

public class FileUtils {
	public static void deleteFile(File... files) {  
        for (File file : files) {  
            if (file.exists()) {  
                file.delete();  
            }
        }  
    }
}
