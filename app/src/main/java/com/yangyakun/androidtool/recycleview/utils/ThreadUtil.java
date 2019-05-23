package com.yangyakun.androidtool.recycleview.utils;

interface ThreadUtil<T> {

    interface MainThreadCallback<T> {

        void updateItemCount(int generation, int itemCount);

        void addTile(int generation, TileList.Tile<T> tile);

        void removeTile(int generation, int position);
    }

    interface BackgroundCallback<T> {

        void refresh(int generation);

        void updateRange(int rangeStart, int rangeEnd, int extRangeStart, int extRangeEnd,
                         int scrollHint);

        void loadTile(int position, int scrollHint);

        void recycleTile(TileList.Tile<T> tile);
    }

    ThreadUtil.MainThreadCallback<T> getMainThreadProxy(ThreadUtil.MainThreadCallback<T> callback);

    ThreadUtil.BackgroundCallback<T> getBackgroundProxy(ThreadUtil.BackgroundCallback<T> callback);
}