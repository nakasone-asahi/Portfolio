package abridge.example.vocabularybooks.viewmodel.flashcard;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import abridge.example.vocabularybooks.db.flashcard_table.FlashCard;

public class FlashCardAdapter extends ListAdapter<FlashCard, FlashCardViewHolder> {

    private View.OnClickListener m_listener;
    private int m_itemid;

    public FlashCardAdapter(@NonNull DiffUtil.ItemCallback<FlashCard> diffCallback) {
        super(diffCallback);
    }

    @Override
    public FlashCardViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        return FlashCardViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(FlashCardViewHolder holder,int position) {
        FlashCard current = getItem(position);//選択した単語カードのポジションを取得する
        //FlashCardViewHolderのbindメソッドを呼出し、選択したカードのタイトルと備考を渡す
        holder.bind(current.getTitle(),current.getRemarks());
        final int pos = current.getFc_id();//変数posに選択したカードのFc_idを保存する

        //プレイボタンが押下されると以下を実行する
        holder.fc_play_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                m_itemid = pos;
                m_listener.onClick(view);
            }
        });

        holder.fc_conf_btn.setOnClickListener(new View.OnClickListener() {
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

    public static class FlashCardDiff extends DiffUtil.ItemCallback<FlashCard> {

        @Override
        public boolean areItemsTheSame(@NonNull FlashCard oldItem,@NonNull FlashCard newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull FlashCard oldItem,@NonNull FlashCard newItem){
            return oldItem.getTitle().equals(newItem.getTitle());
        }
    }
}
