package com.example.morphtin.dishes.ui.view;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.example.morphtin.dishes.R;
import com.example.morphtin.dishes.bean.User;


public class EditMineDialog extends Dialog {
    public DialogListener dialogListener;
    Context context;
    User user;




    public EditMineDialog (Context context, final DialogListener dialogListener) {
        super(context);
        this.context = context;
        this.dialogListener = dialogListener;

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_mine_edit);
        TextView ok = (TextView) findViewById(R.id.btn_ok);
        TextView cancel = (TextView) findViewById(R.id.btn_cancel);

        // final EditText input = (EditText) findViewById(R.id.edt_input);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    EditText name=(EditText)findViewById(R.id.name_input);
                    EditText age= (EditText)findViewById(R.id.age_input);
                    EditText description= (EditText)findViewById(R.id.description_input);

                    user=new User(description.getText().toString(),name.getText().toString(),age.getText().toString(),"null");
                    dialogListener.refreshActivity(user);  //在这里调用
                    dismiss(); //Dialog销毁
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface DialogListener {

        public void refreshActivity(Object object);

    }


}
