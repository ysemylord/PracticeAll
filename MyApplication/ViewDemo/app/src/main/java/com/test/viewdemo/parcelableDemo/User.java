package com.test.viewdemo.parcelableDemo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by xuyabo on 2018/7/6.
 * 实现Parceable接口的类除了要实现
 * describeContents（）
 * 与writeToParcel（）外
 * 还要提供名为CREATOR的静态变量
 * 和一个使用Parcel构造对象的构造函数
 */

public class User implements Parcelable {
    public int userId;
    public String userName;
    public boolean isMale;

    //反序列化
    protected User(Parcel in) {
        userId = in.readInt();
        userName = in.readString();
        isMale = in.readInt() == 1 ? true : false;
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    //end 反序列化
    @Override
    public int describeContents() {
        return 0;
    }

    //序列化
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(userId);
        dest.writeString(userName);
        dest.writeInt(isMale ? 1 : 0);
    }
}
