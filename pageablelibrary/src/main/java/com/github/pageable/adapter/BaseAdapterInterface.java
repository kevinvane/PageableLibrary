package com.github.pageable.adapter;

import java.util.List;

public interface BaseAdapterInterface<T> {

    /**
     * 全部更新数据
     * @param data
     */
    void updateList(List<T> data);

    /**
     * 删除某个位置的一个元素
     * @param position
     */
    void removeItem(int position);

    /**
     * 在尾部添加一个元素
     * @param item
     */
    void addItem(T item);

    /**
     * 更新某个位置的一个元素
     * @param position
     */
    void updateItem(int position,T item);

    /**
     * 查找一个元素
     * @param item
     * @return position
     */
    int findItem(T item);

    T getItemData(int position);

    /**
     * 定义当列表中的元素数量是多少时，空视图显示，默认是0，当有Header视图需要设置此值
     * @return
     */
    int getEmptyItemSize();

    void clearList();
}
