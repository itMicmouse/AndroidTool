package com.yangyakun.androidtool.recycleview.utils;


import android.util.SparseArray;

import java.lang.reflect.Array;

/**
 * A sparse collection of tiles sorted for efficient access.
 */
class TileList<T> {

    final int mTileSize;

    // Keyed by start position.
    private final SparseArray<TileList.Tile<T>> mTiles = new SparseArray<TileList.Tile<T>>(10);

    TileList.Tile<T> mLastAccessedTile;

    public TileList(int tileSize) {
        mTileSize = tileSize;
    }

    public T getItemAt(int pos) {
        if (mLastAccessedTile == null || !mLastAccessedTile.containsPosition(pos)) {
            final int startPosition = pos - (pos % mTileSize);
            final int index = mTiles.indexOfKey(startPosition);
            if (index < 0) {
                return null;
            }
            mLastAccessedTile = mTiles.valueAt(index);
        }
        return mLastAccessedTile.getByPosition(pos);
    }

    public int size() {
        return mTiles.size();
    }

    public void clear() {
        mTiles.clear();
    }

    public TileList.Tile<T> getAtIndex(int index) {
        return mTiles.valueAt(index);
    }

    public TileList.Tile<T> addOrReplace(TileList.Tile<T> newTile) {
        final int index = mTiles.indexOfKey(newTile.mStartPosition);
        if (index < 0) {
            mTiles.put(newTile.mStartPosition, newTile);
            return null;
        }
        TileList.Tile<T> oldTile = mTiles.valueAt(index);
        mTiles.setValueAt(index, newTile);
        if (mLastAccessedTile == oldTile) {
            mLastAccessedTile = newTile;
        }
        return oldTile;
    }

    public TileList.Tile<T> removeAtPos(int startPosition) {
        TileList.Tile<T> tile = mTiles.get(startPosition);
        if (mLastAccessedTile == tile) {
            mLastAccessedTile = null;
        }
        mTiles.delete(startPosition);
        return tile;
    }

    public static class Tile<T> {
        public final T[] mItems;
        public int mStartPosition;
        public int mItemCount;
        TileList.Tile<T> mNext;  // Used only for pooling recycled tiles.

        public Tile(Class<T> klass, int size) {
            //noinspection unchecked
            mItems = (T[]) Array.newInstance(klass, size);
        }

        boolean containsPosition(int pos) {
            return mStartPosition <= pos && pos < mStartPosition + mItemCount;
        }

        T getByPosition(int pos) {
            return mItems[pos - mStartPosition];
        }
    }
}