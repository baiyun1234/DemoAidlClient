package bai.bai.bai.demoaidlservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 实现序列化的实体类
 * <p>
 * 序列化：由于不同的进程有着不同的内存区域，且相互之间不能直接访问，
 * 所以我们必须将要传输的数据转化为能够在内存之间流通的形式，这个转换的过程就是序列化和反序列化
 */
public class BookBean implements Parcelable {

    private String name;
    private int price;

    protected BookBean(Parcel in) {
        name = in.readString();
        price = in.readInt();
    }

    public BookBean() {
    }

    public BookBean(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static final Creator<BookBean> CREATOR = new Creator<BookBean>() {
        @Override
        public BookBean createFromParcel(Parcel in) {
            return new BookBean(in);
        }

        @Override
        public BookBean[] newArray(int size) {
            return new BookBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BookBean{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(price);
    }

    /**
     * 该方法不是Parcelable自动生成的，需要自己手动添加，
     * 如果不添加，则在使用AIDL时只支持 in 的定向tag
     * 如果添加了，则支持 in、out、inout
     *
     * @param dest 参数是一个Parcel,用它来存储与传输数据
     */
    public void readFromParcel(Parcel dest) {
        name = dest.readString();
        price = dest.readInt();
    }

}
