// Book.aidl
//第一类AIDL文件
//这个文件的作用是引入了一个序列化对象 Book 供其他的AIDL文件使用
//注意：BookBean.aidl与BookBean.java的包名应当是一样的
package bai.bai.bai.demoaidlservice;

//注意 parcelable 必须是小写
parcelable BookBean;
