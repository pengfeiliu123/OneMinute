package com.lpf.oneminute.modules.recordnote;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.lpf.common.interfaces.AlertDialogInterface;
import com.lpf.common.util.AlertDialogUtil;
import com.lpf.common.util.Base64Util;
import com.lpf.common.util.PreferenceUtil;
import com.lpf.common.util.TimeUtil;
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.MainActivity;
import com.lpf.oneminute.R;
import com.lpf.oneminute.base.BaseFragment;
import com.lpf.oneminute.greendao.db.DbUtil;
import com.lpf.oneminute.greendao.db.LocalNoteHelper;
import com.lpf.oneminute.greendao.localBean.LocalNote;
import com.lpf.oneminute.modules.login.view.FragmentLoginOrRegister;
import com.lpf.oneminute.util.AccountUtil;
import com.lpf.oneminute.util.NavigatorUtil;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.R.id.message;

public class FragmentRecordNoteUpdate extends BaseFragment {

    Context mContext;
    View rootView;
    @BindView(R.id.note_title_update)
    EditText noteTitle;
    @BindView(R.id.note_content_update)
    EditText noteContent;
    @BindView(R.id.btn_note_reset_update)
    ImageView btnNoteReset;
    @BindView(R.id.btn_note_ok_update)
    ImageView btnNoteOk;
    @BindView(R.id.btn_note_layout_update)
    LinearLayout btnNoteLayout;
    @BindView(R.id.activity_record_note_update)
    RelativeLayout activityRecordNote;

    private static LocalNote localNote = null;
    LocalNoteHelper noteDao = DbUtil.getlocalNoteHelper();

    public static FragmentRecordNoteUpdate getInstance(LocalNote updateNote) {
        FragmentRecordNoteUpdate mInstance = new FragmentRecordNoteUpdate();
        localNote = updateNote;
        return mInstance;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.f_record_note_update, null, false);
        mContext = getActivity();
        ButterKnife.bind(this, rootView);
        initViews();
        return rootView;
    }

    private void initViews() {


//        BmobUser bmobUser = BmobUser.getCurrentUser();
//        if (null == bmobUser) {
//            ((MainActivity)getActivity()).switchToFragment(FragmentLoginOrRegister.newInstance());
//        }

        // if log out
        if (!AccountUtil.isLogin(mContext)) {
            ((MainActivity) getActivity()).switchToFragment(FragmentLoginOrRegister.newInstance());
        } else {
            NavigatorUtil.changeToolTitle(mContext, "Write your story");
            if (localNote != null) {
                noteTitle.setText(localNote.getTitle());
                noteContent.setText(Base64Util.decode(localNote.getContent()));
            }
        }
    }

    @OnClick({R.id.btn_note_ok_update, R.id.btn_note_reset_update})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_note_ok_update:
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
                note.setId(localNote.getId());
                note.setUserId(localNote.getUserId());
                note.setTitle(title);
                note.setContent(content);
                note.setTime(TimeUtil.formatDate(new Date()));

//                LocalNoteDao noteDao = App.newInstance().getDaoSession().getLocalNoteDao();

                try {
                    noteDao.update(note);
                    ToastUtil.shortShow(mContext, "update note success");
                } catch (Exception e) {
                    e.printStackTrace();
                    ToastUtil.shortShow(mContext, "update failed, try it later");
                }
                break;
            case R.id.btn_note_reset_update:
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Sure to Delete ?");
                builder.setMessage("This cannot be recovered.");
                builder.setPositiveButton(R.string.dialog_ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        noteDao.delete(localNote);
                        FragmentRecordNoteShow fragmentRecordNoteShow = FragmentRecordNoteShow.getInstance();
                        NavigatorUtil.switchToFragment(mContext, fragmentRecordNoteShow);
                    }
                });

                builder.setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.setCancelable(true);
                dialog.setCanceledOnTouchOutside(false);
                dialog.show();
                break;
        }
    }

    @Override
    public boolean interceptBackPressed() {
        FragmentRecordNoteShow fragmentRecordNoteShow = new FragmentRecordNoteShow();
        NavigatorUtil.switchToFragment(getActivity(), fragmentRecordNoteShow);
        return true;
    }
}