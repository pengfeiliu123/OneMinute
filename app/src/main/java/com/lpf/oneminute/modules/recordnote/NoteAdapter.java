package com.lpf.oneminute.modules.recordnote;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lpf.common.util.Base64Util;
import com.lpf.common.util.ToastUtil;
import com.lpf.oneminute.R;
import com.lpf.oneminute.greendao.localBean.LocalNote;
import com.lpf.oneminute.util.NavigatorUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liupengfei on 17/2/25 14:12.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    public List<LocalNote> datas = new ArrayList<LocalNote>();
    public Context mContext;

    public NoteAdapter(Context context, List<LocalNote> datas) {
        this.datas = datas;
        this.mContext = context;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 0) {
            view = View.inflate(mContext, R.layout.i_note_rv_left, null);
        } else {
            view = View.inflate(mContext, R.layout.i_note_rv_right, null);
        }
        NoteViewHolder holder = new NoteViewHolder(view);
        return holder;
    }

    @Override
    public int getItemViewType(int position) {

        if (position % 2 == 0) {
            return 0;
        } else {
            return 1;
        }
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, final int position) {
        LocalNote note = datas.get(position);
        holder.title.setText(note.getTitle());
        String content = Base64Util.decode(note.getContent());
        holder.content.setText(content);
        holder.time.setText(note.getTime());

        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LocalNote updateNote = datas.get(position);
                NavigatorUtil.switchToFragment(mContext,FragmentRecordNoteUpdate.getInstance(updateNote));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        public View rootView;
        public TextView title;
        public TextView content;
        public TextView time;

        public NoteViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
            title = (TextView) itemView.findViewById(R.id.item_note_title);
            content = (TextView) itemView.findViewById(R.id.item_note_content);
            time = (TextView) itemView.findViewById(R.id.item_note_time);

        }
    }
}
