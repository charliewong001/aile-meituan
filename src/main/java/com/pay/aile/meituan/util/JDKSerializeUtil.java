package com.pay.aile.meituan.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class JDKSerializeUtil {

    public static Object deserialize(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        try (ByteArrayInputStream bais = new ByteArrayInputStream(data);) {
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Throwable e) {
            throw new RuntimeException("Failed to deserialize payload. "
                    + "Is the byte array a result of corresponding serialization for Class ?", e);
        }
    }

    public static byte[] serialize(Object object) {
        if (object == null) {
            return new byte[0];
        }
        if (!(object instanceof Serializable)) {
            throw new IllegalArgumentException(object.getClass().getSimpleName() + " requires a Serializable payload "
                    + "but received an object of type [" + object.getClass().getName() + "]");
        }
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream(128)) {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
            return baos.toByteArray();
        } catch (Throwable e) {
            throw new RuntimeException("Failed to serialize object using " + object.getClass().getSimpleName(), e);
        }
    }

}
