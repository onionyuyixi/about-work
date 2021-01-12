package com.example.aboutwork.errors;

public class RollBackOnlyError {


    //事务嵌套的时候
    //事务的默认传递 有则沿袭 无乃新求  所以在嵌套的时候 会出现被调用的方法事务出现已被标记回滚 然后原方法再去操作事务的时候 就会出现矛盾
    //解决这种的最好方法就是 在调用原方法的地方 进行 catch error
    //换句话说：service上的事务方法不要自己try catch（或者catch后throw new RuntimeException（）也成）这样程序异常时才能被aop捕获进而回滚。
    //另外一种方案：
    //在service层方法的catch语句中增加：TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();语句，
    // 手动回滚，这样上层就无需去处理异常（这也是比较推荐的做法）










}
