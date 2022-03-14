package abridge.example.vocabularybooks.viewmodel.word;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import abridge.example.vocabularybooks.R;

public class WordViewHolder extends RecyclerView.ViewHolder {
    private final TextView text_show;
    public final ImageButton wd_conf_btn;
    public final ImageButton wd_cange_btn;

    private WordViewHolder(View itemView) {
        super(itemView);
        text_show = itemView.findViewById(R.id.text_show);
        wd_conf_btn = itemView.findViewById(R.id.wd_conf_btn);
        wd_cange_btn = itemView.findViewById(R.id.wd_cange_btn);
    }

    public void bind(String text){
        //表面の文字をセット
        text_show.setText(text);
    }

    static WordViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.wd_list_items,parent,false);
        return new WordViewHolder(view);
    }

}