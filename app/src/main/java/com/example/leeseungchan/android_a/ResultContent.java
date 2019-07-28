package com.example.leeseungchan.android_a;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ResultContent extends LinearLayout {

    ImageView imageView;
    TextView time;

    public ResultContent(Context context){
        super(context);
    }

    public ResultContent(Context context, @Nullable AttributeSet attrs){
        super(context, attrs);
        init();
        setProperties(attrs);
    }

    public ResultContent(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
        setProperties(attrs);
    }

    private void init(){
        LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(
                Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.result_content, this, false);
        addView(v);

        imageView = findViewById(R.id.result_image);
        time = findViewById(R.id.result_time);
    }

    private void setProperties(AttributeSet attrs){
        TypedArray properties = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.ResultContent, 0, 0);

        int image_id = properties.getResourceId(R.styleable.ResultContent_image,
                R.drawable.sky);
        imageView.setImageResource(image_id);

        String new_time = properties.getString(R.styleable.ResultContent_time);
        time.setText(new_time);

        properties.recycle();
    }
}
