package abridge.example.vocabularybooks.view.login;

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

public class LoginSuccessDialogFragment extends DialogFragment {
    String loginName;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle saveInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle("ログインに成功しました！")
                .setMessage(loginName+"でログインしています。")
                .setPositiveButton("OK",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {

                        getFragmentManager().popBackStack();
                    }
                });
        return builder.create();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //ActivityからItemIDの値を受け取る
        loginName = getArguments().getString("LoginName");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_delete_fc_dialog, container, false);
    }
}