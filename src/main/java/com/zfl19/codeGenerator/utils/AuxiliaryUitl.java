package com.zfl19.codeGenerator.utils;

/**
 * 辅助代码自动生成工具类
 * @author 19zfl
 * @date 2023/5/18
 */
public class AuxiliaryUitl {

    //单词所有下划线去掉，并且驼峰转换
    public static String convert(String pre, String source) {
        StringBuffer sb = new StringBuffer();
        if (source.contains(pre))
        {
            // 按下划线来切割字符串为数组
            String[] split = source.split("_");
            // 循环数组操作其中的字符串
            for (int i = 1, index = split.length; i < index; i++){
                String temp = split[i];
                if(i == 1){
                    sb.append(temp);
                }else{
                    sb.append(temp.substring(0, 1).toUpperCase() + temp.substring(1));
                }
            }
        }else{
            sb.append(source);
        }
        return sb.toString();
    }

    public static String getEntityName(String source) {
        StringBuffer sb = new StringBuffer();
        // 按下划线来切割字符串为数组
        String[] split = source.split("_");
        // 循环数组操作其中的字符串
        for(int i=0; i<split.length; i++){
            String word_temp = split[i];
            if(i == 0){
                sb.append(word_temp);
            }else{
                sb.append(word_temp.substring(0, 1).toUpperCase() + word_temp.substring(1));
            }
        }
        return sb.toString();
    }

}
