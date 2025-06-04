package com.home.java.utils.file;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamUtil
{
// -------------------------- STATIC METHODS --------------------------

    public static String getString(InputStream p_inputStream)
    {
        return new String(getBytes(p_inputStream));
    }

    public static byte[] getBytes(InputStream p_inputStream)
    {
        return getBytes(p_inputStream, Integer.MAX_VALUE);
    }

    public static byte[] getBytes(InputStream p_inputStream, int p_maximumBytes)
    {
        try
        {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1000];
            int bytesReadCount = 0;
            while ((bytesReadCount = p_inputStream.read(buffer, 0, buffer.length)) != -1)
            {
                baos.write(buffer, 0, bytesReadCount);
                if (baos.size() >= p_maximumBytes)
                {
                    break;
                }
            }
            p_inputStream.close();

            if (baos.size() >= p_maximumBytes)
            {
                byte[] bytes = new byte[p_maximumBytes];
                System.arraycopy(baos.toByteArray(), 0, bytes, 0, p_maximumBytes);
                return bytes;
            }
            else
            {
                return baos.toByteArray();
            }
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public static void copy(InputStream p_inputStream, OutputStream p_outputStream) throws IOException
    {
        p_outputStream.write(StreamUtil.getBytes(p_inputStream));
    }
}