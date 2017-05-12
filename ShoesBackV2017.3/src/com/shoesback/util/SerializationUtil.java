package com.shoesback.util;

/**
 *  ���л����ǽ�һ������ת��Ϊ�����Ƶ��������������Ϳ��Խ��д��䣬���߱��浽�ļ��С����һ����Ķ���Ҫ��ʵ�����л����ͱ���ʵ��serializable�ӿڡ��ڴ˽ӿ���û���κεķ������˽ӿ�ֻ����Ϊһ����ʶ����ʾ����Ķ���߱������л����������ѡ�
 *  �����л�:��������������ת������Ӧ�Ķ���
 *  �����Ҫ��ɶ�������л�����Ҫ����ObjectOutputStream��ObjectInputStream,ǰ���������л����������������ڷ����л�������
 *  
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerializationUtil {
    /**
     * ���л�
     * 
     * @param object
     * @return
     */
    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * �����л�
     * 
     * @param bytes
     * @return
     */
    public static Object deserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {

        }
        return null;
    }

}
