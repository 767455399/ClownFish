package com.example.administrator.clownfish;

/**
 * 项目名称：ClownFish
 * 类描述：
 * 创建人：WangQing
 * 创建时间：2016/5/23 14:18
 * 修改人：WangQing
 * 修改时间：2016/5/23 14:18
 * 修改备注：
 */
public class Car {
    private String carColor;

    private static Car car = new Car();
    private  Car(){};
    public static Car getCar(){
        return car;
    }


    public void setCarColor(String color){
        this.carColor=color;
    }
    public void getCarColor(){
        this.carColor=carColor;
    }


}
