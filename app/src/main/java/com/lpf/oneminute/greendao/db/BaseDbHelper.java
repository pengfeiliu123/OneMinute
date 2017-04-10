package com.lpf.oneminute.greendao.db;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by liupengfei on 17/3/6 16:40.
 * GreenDao 基础操作类
 */

public class BaseDbHelper<T, K> {

    private AbstractDao<T, K> mDao;

    public BaseDbHelper(AbstractDao<T, K> mDao) {
        this.mDao = mDao;
    }

    //***************** insert *******************
    public long insert(T item) {
        return mDao.insert(item);
    }

    public void insert(T... items) {
        mDao.insertInTx(items);
    }

    public void insert(List<T> items) {
        mDao.insertInTx(items);
    }

    public long insertOrUpdate(T item) {
        return mDao.insertOrReplace(item);
    }

    public void insertOrUpdate(T... items) {
        mDao.insertOrReplaceInTx(items);
    }

    public void insertOrUpdate(List<T> items) {
        mDao.insertOrReplaceInTx(items);
    }

    //***************** delete *******************

    public void deleteByKey(K key) {
        mDao.deleteByKey(key);
    }

    public void delete(T item) {
        mDao.delete(item);
    }

    public void delete(T... items) {
        mDao.deleteInTx(items);
    }

    public void delete(List<T> items) {
        mDao.deleteInTx(items);
    }

    public void deleteAll() {
        mDao.deleteAll();
    }

    //***************** update *******************

    public void update(T item) {
        mDao.update(item);
    }

    public void update(T... items) {
        mDao.updateInTx(items);
    }

    public void update(List<T> items) {
        mDao.updateInTx(items);
    }


    //***************** query *******************

    public T load(K key) {
        return mDao.load(key);
    }

    public List<T> loadAll() {
        return mDao.loadAll();
    }

    public List<T> queryRaw(String where, String... params) {

        return mDao.queryRaw(where, params);
    }

    public QueryBuilder<T> queryBuilder() {

        return mDao.queryBuilder();
    }

    //***************** count *******************

    public long count() {
        return mDao.count();
    }


    //***************** refresh *******************

    public void refresh(T item) {
        mDao.refresh(item);
    }

    //***************** detach *******************

    public void detach(T item) {
        mDao.detach(item);
    }

}
