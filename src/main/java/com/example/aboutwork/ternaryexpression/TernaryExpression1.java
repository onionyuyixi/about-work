package com.example.aboutwork.ternaryexpression;

public class TernaryExpression1 {


    public static void main(String[] args) {
        // condition ？ express1 : express2
        //三元表达是会参照express1结果的数据类型 来确定express2结果的数据类型
        Integer i = null;
        //此时express1已经完全可以满足condition 故不用去执行express2
        System.out.println(1 == 1 ? 0 : i);
    }
}


//javap -c -l TernaryExpression1.class
//Compiled from "TernaryExpression1.java"
//public class com.example.aboutwork.ternaryexpression.TernaryExpression1 {
//public com.example.aboutwork.ternaryexpression.TernaryExpression1();
//        Code:
//        0: aload_0
//        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//        4: return
//        LineNumberTable:
//        line 3: 0
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0       5     0  this   Lcom/example/aboutwork/ternaryexpression/TernaryExpression1;
//
//public static void main(java.lang.String[]);
//        Code:
//        0: aconst_null
//        1: astore_1
//        2: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
//        5: iconst_0
//        6: invokevirtual #3                  // Method java/io/PrintStream.println:(I)V
//        9: return
//        LineNumberTable:
//        line 9: 0
//        line 11: 2
//        line 12: 9
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0      10     0  args   [Ljava/lang/String;
//        2       8     1     i   Ljava/lang/Integer;
//        }




