package com.lpf.oneminute.greendao.db;

import com.lpf.oneminute.greendao.localBean.LocalNote;

import org.greenrobot.greendao.AbstractDao;

/**
 * Created by liupengfei on 17/3/6 17:11.
 */

public class LocalNoteHelper extends BaseDbHelper<LocalNote,Long> {

    public LocalNoteHelper(AbstractDao<LocalNote, Long> mDao) {
        super(mDao);
    }
}
