package com.lpf.oneminute.modules.recordnote;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lpf.common.util.Base64Util;
import com.lpf.common.util.PreferenceUtil;
import com.lpf.common.util.TimeUtil;
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.App;
import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.bean.Note;
import com.lpf.oneminute.greendao.gen.DaoMaster;
import com.lpf.oneminute.greendao.gen.LocalNoteDao;
import com.lpf.oneminute.greendao.localBean.LocalNote;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.util.AccountUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class FragmentRecordNote extends Fragment {

    Context mContext;
    View rootView;
    @BindView(R.id.note_title)
    EditText noteTitle;
    @BindView(R.id.note_content)
    EditText noteContent;
    @BindView(R.id.btn_note_reset)
    Button btnNoteReset;
    @BindView(R.id.btn_note_ok)
    Button btnNoteOk;
    @BindView(R.id.btn_note_layout)
    LinearLayout btnNoteLayout;
    @BindView(R.id.activity_record_note)
    RelativeLayout activityRecordNote;

    public static FragmentRecordNote getInstance() {
        FragmentRecordNote mInstance = new FragmentRecordNote();
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.f_record_note, null, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);
        initViews();
        return rootView;
    }

    private void initViews() {

//        BmobUser bmobUser = BmobUser.getCurrentUser();
//        if (null == bmobUser) {
//            ((MainActivity)getActivity()).switchToFragment(FragmentLoginOrRegister.getInstance());
//        }

        // if log out
        if(!AccountUtil.isLogin(mContext)){
            ((MainActivity) getActivity()).switchToFragment(FragmentLoginOrRegister.getInstance());
        }
    }

    @OnClick({R.id.btn_note_ok, R.id.btn_note_reset})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_note_ok:
                String title = noteTitle.getText().toString();
                String content = noteContent.getText().toString();
                if (TextUtils.isEmpty(title)) {
                    title = TimeUtil.formatDate(new Date());
                }
                if (TextUtils.isEmpty(content)) {
                    ToastUtil.shortShow(mContext, "content is null");
                    return;
                }
                //内容加密
                content = Base64Util.encode(content);
//                Note note = new Note();
//                note.setUserId(BmobUser.getCurrentUser().getObjectId());
//                note.setTitle(title);
//                note.setContent(content);
//                note.setTime(TimeUtil.formatDate(new Date()));
//                note.save(new SaveListener<String>() {
//                    @Override
//                    public void done(String s, BmobException e) {
//                        if (e == null) {
//                            ToastUtil.shortShow(mContext, "add content success");
//                        } else {
//                            ToastUtil.shortShow(mContext,e.getErrorCode()+"");
//                            ToastUtil.shortShow(mContext, "try this later");
//                        }
//                    }
//                });

                LocalNote note = new LocalNote();
                note.setId(System.currentTimeMillis());
                note.setUserId(PreferenceUtil.getStringValue(mContext, "userId"));
                note.setTitle(title);
                note.setContent(content);
                note.setTime(TimeUtil.formatDate(new Date()));

                LocalNoteDao noteDao = App.getInstance().getDaoSession().getLocalNoteDao();
                long resultCode = noteDao.insert(note);
                if (resultCode > 0) {
                    ToastUtil.shortShow(mContext,"add note success");
                    resetNote();
                }else{
                    ToastUtil.shortShow(mContext,"add failed, try it later");
                }
                break;
            case R.id.btn_note_reset:
                resetNote();
                break;
        }
    }

    private void resetNote() {
        noteTitle.setText("");
        noteContent.setText("");
    }
}
