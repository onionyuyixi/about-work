package com.example.aboutwork.txhandle;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BeanSelfAware<T extends BeanSelfAware> {
    T self;


    //在复写了equals hashcode 方法后 由于自身递归会出现栈溢出错误

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof BeanSelfAware)) return false;
//        BeanSelfAware<?> that = (BeanSelfAware<?>) o;
//        return Objects.equals(self, that.self);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(self);
//    }
}
