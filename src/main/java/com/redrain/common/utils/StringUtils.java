package com.redrain.common.utils;


public class StringUtils {

    public static boolean isEmpty(String input) {
        if (input == null || input.length() == 0) {
            return true;
        }
        int length = input.length();

        for (int i = 0; i < length; i++) {
            if (!isWhitespace(input.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static boolean hasText( String str) {
        return str != null && !str.isEmpty() && containsText(str);
    }

    public static boolean isNotEmpty(String input) {
        return !isEmpty(input);
    }

    private static boolean isWhitespace(int c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\f' || c == '\r';
    }

    /**
     * @param code
     * @param num  长度
     * @return
     */
    public static String leftPaddingZero(String code, int num) {
        String result = "";
        if(isEmpty(code)){
            result = "0000";
        }else{
            // num 代表长度为4
            // d 代表参数为正数型
            result = String.format("%0" + num + "d", Integer.parseInt(code));
        }
        return result;
    }

    /**
     * 括号拼接两个字符串
     * @param outerValue
     * @param innerValue
     * @return
     */
    public static String joinWithBracket(String outerValue, String innerValue){
        if(isEmpty(innerValue)){
            return outerValue;
        }
        return outerValue.concat("(").concat(innerValue).concat(")");
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();

        for(int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }

    public static boolean isNumeric(String str){
        for (int i = str.length();--i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
