package abridge.example.vocabularybooks.viewmodel.word;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.List;

import abridge.example.vocabularybooks.db.word_table.Word;

public class WordAdapter extends ListAdapter<Word, WordViewHolder> {

    private View.OnClickListener m_listener;
    private int m_itemid;
    private boolean showflag;

    public WordAdapter(@NonNull DiffUtil.ItemCallback<Word> diffCallback) {
        super(diffCallback);
    }

    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return WordViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder,int position) {
        Word current = getItem(position);
        holder.bind("表："+current.getFront());
        showflag = true;
        final int pos = current.getWord_id();

        holder.wd_cange_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (showflag){
                    holder.bind("裏："+current.getRear());
                    showflag = false;
                }else {
                    holder.bind("表："+current.getFront());
                    showflag = true;
                }
            }
        });

        holder.wd_conf_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_itemid = pos;
                m_listener.onClick(view);
            }
        });
    }

    public int getItemId(){
        return m_itemid; //行数を取得
    }
    public void setOnItemClickListener(View.OnClickListener listener) {
        this.m_listener = listener;
    }

    public static class WordDiff extends DiffUtil.ItemCallback<Word> {

        @Override
        public boolean areItemsTheSame(@NonNull Word oldItem,@NonNull Word newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Word oldItem,@NonNull Word newItem){
            return oldItem.getFront().equals(newItem.getFront());
        }
    }
}
