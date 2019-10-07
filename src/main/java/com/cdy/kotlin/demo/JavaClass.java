package com.cdy.kotlin.demo;

import kotlin.io.FilesKt;

import java.io.File;
import java.nio.charset.Charset;

/**
 * todo
 * Created by 陈东一
 * 2019/10/2 0002 17:13
 */
public class JavaClass {

    public static String format(String s) {
        return s.isEmpty() ? null : s;
    }


    public static void main(String[] args) {
        File file = new File("kotlin-spring5.iml");

        String content = FilesKt.readText(file, Charset.defaultCharset());

    }

}
