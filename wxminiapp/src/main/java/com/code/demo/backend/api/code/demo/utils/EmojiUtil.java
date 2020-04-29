package com.code.demo.backend.api.code.demo.utils;


import com.github.binarywang.java.emoji.EmojiConverter;

/**
 * 表情处理类
 *
 * @author Administrator
 */
public final class EmojiUtil {

    private static EmojiConverter emojiConverter = EmojiConverter.getInstance();

    /**
     * 将emojiStr转为 带有表情的字符
     *
     * @param emojiStr
     * @return
     */
    public static String emojiConverterUnicodeStr(String emojiStr) {
        String result = emojiConverter.toUnicode(emojiStr);
        return result;
    }

    /**
     * 带有表情的字符串转换为编码
     *
     * @param str
     * @return
     */
    public static String emojiConverterToAlias(String str) {
        String result = emojiConverter.toAlias(str);
        return result;
    }


}
