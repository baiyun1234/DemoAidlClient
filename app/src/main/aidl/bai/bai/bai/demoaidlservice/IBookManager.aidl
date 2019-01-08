// BookManager.aidl
//第二类AIDL文件
//作用是定义方法接口
package bai.bai.bai.demoaidlservice;

//导入所需要使用的非默认支持数据类型的包
import bai.bai.bai.demoaidlservice.BookBean;

interface IBookManager {
    //所有的返回值前都不需要加任何东西，不管是什么数据类型
    List<BookBean> getBooks();

    //Java基本类型以及String，CharSequence的 tag 默认且只能是 in
    //传参时除了Java基本类型以及String，CharSequence之外的类型
    //都需要在前面加上定向tag，具体加什么量需而定
    void addBook(in BookBean book);
}
