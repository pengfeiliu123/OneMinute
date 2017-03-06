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

    //***************** save *******************
    public void save(T item) {
        mDao.insert(item);
    }

    public void save(T... items) {
        mDao.insertInTx(items);
    }

    public void save(List<T> items) {
        mDao.insertInTx(items);
    }

    public void saveOrUpdate(T item) {
        mDao.insertOrReplace(item);
    }

    public void saveOrUpdate(T... items) {
        mDao.insertOrReplaceInTx(items);
    }

    public void saveOrUpdate(List<T> items) {
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

    public T query(K key) {
        return mDao.load(key);
    }

    public List<T> queryAll() {
        return mDao.loadAll();
    }

    public List<T> query(String where, String... params) {

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
