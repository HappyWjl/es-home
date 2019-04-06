package com.es.stone.util;

public class CamelCaseUtils {

    /**
     * 下划线转驼峰
     * @param str
     * @return
     */
    public static String toUnderlineName(String str) {
        if (str == null || "".equals(str.trim())) {
            return "";
        } else {
            StringBuilder sb = new StringBuilder();
            boolean upperCase = false;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                boolean nextUpperCase = true;
                if (i < (str.length() - 1)) {
                    nextUpperCase = Character.isUpperCase(str.charAt(i + 1));
                }
                if ((i >= 0) && Character.isUpperCase(c)) {
                    if (!upperCase || !nextUpperCase) {
                        if (i > 0)
                            sb.append("_");
                    }
                    upperCase = true;
                } else {
                    upperCase = false;
                }
                sb.append(Character.toLowerCase(c));
            }
            return sb.toString();
        }
    }

    /**
     * 驼峰转下划线
     * @param str
     * @return
     */
    public static String toCamelCase(String str) {
        if (str == null || "".equals(str.trim())) {
            return null;
        } else {
            str = str.toLowerCase();
            StringBuilder sb = new StringBuilder(str.length());
            boolean upperCase = false;
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '_') {
                    if (i == 0) {
                        sb.append(c);
                    } else {
                        upperCase = true;
                    }
                } else if (upperCase) {
                    sb.append(Character.toUpperCase(c));
                    upperCase = false;
                } else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) {
        System.out.println(CamelCaseUtils.toUnderlineName("ISOCertifiedStaff"));
        System.out.println(CamelCaseUtils.toUnderlineName("CertifiedStaff"));
        System.out.println(CamelCaseUtils.toUnderlineName("UserID"));
        System.out.println(CamelCaseUtils.toCamelCase("iso_certified_staff"));
        System.out.println(CamelCaseUtils.toCamelCase("certified_staff_"));
        System.out.println(CamelCaseUtils.toCamelCase("_i_s_o_certified_staff"));
    }

}
