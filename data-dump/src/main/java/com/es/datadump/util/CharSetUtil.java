package com.es.datadump.util;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class CharSetUtil {
    public static String bytesToString(byte[] input, String sourceCharSet,String destCharSet) throws CharacterCodingException {
        if(ArrayUtils.isEmpty(input)) {
            return StringUtils.EMPTY;
        }

        ByteBuffer buffer = ByteBuffer.allocate(input.length);
        
        buffer.put(input);
        buffer.flip();

        Charset defCharSet = null;
        CharsetDecoder defCharSetDecoder = null;
        CharBuffer defCharSetCharBuffer = null;
        
        Charset srcCharSet = null;
        ByteBuffer srcByteBuffer = null;
        
        Charset desCharSet = null;
        CharsetDecoder desDecoder = null;
        CharBuffer desCharBuffer = null;
        
        defCharSet = Charset.defaultCharset();
        defCharSetDecoder = defCharSet.newDecoder();
        defCharSetCharBuffer = defCharSetDecoder.decode(buffer.asReadOnlyBuffer());
        
        srcCharSet = Charset.forName(sourceCharSet);
        srcByteBuffer = srcCharSet.encode(defCharSetCharBuffer);
        
        
        desCharSet = Charset.forName(destCharSet);
        desDecoder = desCharSet.newDecoder();
        
        desCharBuffer = desDecoder.decode(srcByteBuffer);
        
        return desCharBuffer.toString();
    }
}
