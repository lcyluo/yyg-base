package com.yyg.common.widget;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

public interface OnCustomItemClickListener {

    void onItemClick(BaseQuickAdapter adapter, View view, int position, int itemType);

    void onItemChildClick(BaseQuickAdapter adapter, View view, int position, int itemType);
}
