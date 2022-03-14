package abridge.example.vocabularybooks.view.word;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import abridge.example.vocabularybooks.R;
import abridge.example.vocabularybooks.viewmodel.flashcard.FlashCardViewModel;
import abridge.example.vocabularybooks.viewmodel.word.WordViewModel;

public class DeleteWdDialogFragment extends DialogFragment {

    WordViewModel wordViewModel;
    int itemId;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle saveInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("削除確認")
                .setMessage("本当に削除しても大丈夫ですか？")
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        wordViewModel.delete(itemId);
                        getFragmentManager().popBackStack();
                    }
                })
                .setNegativeButton("キャンセル",null);
        return builder.create();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wordViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        //ActivityからItemIDの値を受け取る
        itemId = getArguments().getInt("ItemID");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_wd_dialog, container, false);
    }
}