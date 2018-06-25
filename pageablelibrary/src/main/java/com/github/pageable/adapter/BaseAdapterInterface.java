package com.github.pageable.adapter;

import java.util.List;

public interface BaseAdapterInterface<T> {

    void updateList(List<T> data);
    T getItemData(int position);
    int getEmptyItemSize();

}
