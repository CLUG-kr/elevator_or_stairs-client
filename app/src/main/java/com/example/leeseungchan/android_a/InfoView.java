package com.example.leeseungchan.android_a;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InfoView extends LinearLayout {
    TextView infoName;
    EditText infoExample;

    public InfoView(Context context){
        super(context);
        init();
    }

    public InfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
        setProperties(attrs);
    }

    public InfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setProperties(attrs);
    }


    /**
     * initialize layout.
     */
    private void init(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.get_info, this, false);
        addView(v);

        infoName = findViewById(R.id.info_name);
        infoExample = findViewById(R.id.info_example);
    }

    private void setProperties(AttributeSet attrs){
        TypedArray properties = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.InfoView, 0, 0);

        String newInfoName = properties.getString(R.styleable.InfoView_info_name);
        infoName.setText(newInfoName);

        String newInfoEx = properties.getString(R.styleable.InfoView_info_example);
        infoExample.setText(newInfoEx);

        properties.recycle();
    }
}
