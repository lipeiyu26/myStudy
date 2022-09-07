package com.lpyu.effective;

import java.util.Objects;
//构造方法同其它方法一样，也可以用private修饰，私有的构造方法无法在本类外部使用，也就导致本类无法用new实例化，这样可以控制对象的生成。
public class NyPizza extends Pizza{
    public enum Size {SMALL,MEDIUM,LARGE}
    private final Size size;

    public static  class Builder extends Pizza.Builder<Builder>{
        private final Size size;

        public Builder(Size size){
            this.size= Objects.requireNonNull(size);
        }
    @Override
    public  NyPizza build(){
            return new NyPizza(this);
        }
    @Override
        protected  Builder self(){return this;}
    }
    private NyPizza(Builder builder){
        super(builder);
        size=builder.size;
    }
}
