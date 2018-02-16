package com.navan.alireza.devardevar.helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class HelperIO {

    public static void copyFile(InputStream inputStream, String stream) {
        OutputStream outputStream = null;
        byte[] buffer = new byte[8 * 1024];
        int lenRead = 0;
        try {
            outputStream = new FileOutputStream(stream);
            while ((lenRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, lenRead);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.flush();
                outputStream.close();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
