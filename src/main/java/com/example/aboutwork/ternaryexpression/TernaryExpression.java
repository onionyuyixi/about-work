package com.example.aboutwork.ternaryexpression;

public class TernaryExpression {


    public static void main(String[] args) {
        // condition ？ express1 : express2
        //三元表达是会参照express1结果的数据类型 来确定express2结果的数据类型
        Integer i = null;
        //此时express1不是wrapper类型 所以会出发i的unbox操作 从而产生了NPE
        System.out.println(1 != 1 ? 0 : i);
    }
}

//
//    javap -c -l TernaryExpression.class
//    Compiled from "TernaryExpression.java"
//    public class com.example.aboutwork.ternaryexpression.TernaryExpression {
//  public com.example.aboutwork.ternaryexpression.TernaryExpression();
//        Code:
//        0: aload_0
//        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
//        4: return
//                LineNumberTable:
//        line 3: 0
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0       5     0  this   Lcom/example/aboutwork/ternaryexpression/TernaryExpression;
//
//        public static void main(java.lang.String[]);
//        Code:
//        0: aconst_null
//        1: astore_1
//        2: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
//        5: aload_1
//        6: invokevirtual #3                  // Method java/lang/Integer.intValue:()I   ------->这里会进行unbox操作
//        9: invokevirtual #4                  // Method java/io/PrintStream.println:(I)V
//        12: return
//                LineNumberTable:
//        line 9: 0
//        line 11: 2
//        line 12: 12
//        LocalVariableTable:
//        Start  Length  Slot  Name   Signature
//        0      13     0  args   [Ljava/lang/String;
//        2      11     1     i   Ljava/lang/Integer;



