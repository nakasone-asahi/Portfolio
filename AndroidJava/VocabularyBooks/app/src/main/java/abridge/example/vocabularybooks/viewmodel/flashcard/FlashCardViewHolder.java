package abridge.example.vocabularybooks.viewmodel.flashcard;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import abridge.example.vocabularybooks.R;

public class FlashCardViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleView;
    private final TextView remarksView;
    public final ImageButton fc_play_btn;
    public final ImageButton fc_conf_btn;

    private FlashCardViewHolder(View itemView) {
        super(itemView);
        titleView = itemView.findViewById(R.id.text_title);
        remarksView = itemView.findViewById(R.id.text_remarks);
        fc_play_btn = itemView.findViewById(R.id.fc_play_btn);
        fc_conf_btn = itemView.findViewById(R.id.fc_conf_btn);
    }

    public void bind(String title,String remarks){
        titleView.setText(title);
        remarksView.setText(remarks);
    }

    static FlashCardViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fc_list_items,parent,false);
        return new FlashCardViewHolder(view);
    }

}
