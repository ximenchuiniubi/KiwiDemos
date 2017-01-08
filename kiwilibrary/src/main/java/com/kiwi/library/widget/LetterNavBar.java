package com.kiwi.library.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.kiwi.library.R;
import com.kiwi.library.utils.DensityUtil;

/**
 * 字母索引条
 * <p>
 * Created by Kiwi on 2016/12/31.
 */
public class LetterNavBar extends View {
    private Context context;
    private OnTouchLetterChangeListenner onTouchLetterChangeListenner;
    private Paint paint=new Paint();
    /**
     * 是否画出背景
     */
    private boolean showBg = false;
    /**
     * 索引字母数组
     */
    private char[] letters;
    /**
     * 选中的项
     */
    private int chooseIndex;
    /**
     * 字母大小
     */
    private float letterSize;
    /**
     * 字母颜色
     */
    private int letterColor;

    public LetterNavBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.LetterNavBar);
        String letterStr = ta.getString(R.styleable.LetterNavBar_letter_array);
        letters = letterStr.toCharArray();
        letterSize = ta.getDimension(R.styleable.LetterNavBar_letter_size, 12f);
        letterColor = ta.getColor(R.styleable.LetterNavBar_letter_color, Color.BLACK);
    }

    public LetterNavBar(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (showBg) canvas.drawColor(Color.parseColor("#ffebedf2"));
        drawLetters(canvas);
    }

    /**
     * 画字母
     */
    private void drawLetters(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        //每个字母高度
        int singleHeight = height / letters.length;
        for (int i = 0; i < letters.length; i++) {
            paint.setColor(letterColor);
            //抗锯齿
            paint.setAntiAlias(true);
            paint.setTextSize(DensityUtil.dipTopx(context, letterSize));
            //要画字母的x,y坐标
            float posX = width / 2 - paint.measureText(letters[i] + "") / 2;
            float posY = i * singleHeight + singleHeight;
            //画出字母
            canvas.drawText(letters[i] + "", posX, posY, paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final float y = event.getY();
        // 算出点击的字母的索引
        final int index = (int) (y / getHeight() * letters.length);
        // 保存上次点击的字母的索引到oldChoose
        final int oldChoose = chooseIndex;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                showBg = true;
                if (oldChoose != index && onTouchLetterChangeListenner != null && index >= 0
                        && index < letters.length) {
                    chooseIndex = index;
                    onTouchLetterChangeListenner.onTouchLetterChange(showBg, letters[index] + "");
                    invalidate();
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (oldChoose != index && onTouchLetterChangeListenner != null && index >= 0
                        && index < letters.length) {
                    chooseIndex = index;
                    onTouchLetterChangeListenner.onTouchLetterChange(showBg, letters[index] + "");
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                showBg = false;
                chooseIndex = -1;
                if (onTouchLetterChangeListenner != null) {
                    if (index <= 0) {
                        onTouchLetterChangeListenner.onTouchLetterChange(showBg, letters[0] + "");
                    } else if (index > 0 && index < letters.length) {
                        onTouchLetterChangeListenner.onTouchLetterChange(showBg, letters[index] + "");
                    } else if (index >= letters.length) {
                        onTouchLetterChangeListenner.onTouchLetterChange(showBg, letters[letters.length - 1] + "");
                    }
                }
                invalidate();
                break;
        }
        return true;
    }

    public void setLetters(String letters) {
        this.letters = letters.toCharArray();
        this.postInvalidate();
    }

    public void setLetters(int strId) {
        String letters = context.getResources().getString(strId);
        setLetters(letters);
    }

    /**
     * 设置滑动监听
     *
     * @param onTouchLetterChangeListenner
     */
    public void setOnTouchLetterChangeListenner(OnTouchLetterChangeListenner onTouchLetterChangeListenner) {
        this.onTouchLetterChangeListenner = onTouchLetterChangeListenner;
    }

    /**
     * 滑动监听接口
     */
    public interface OnTouchLetterChangeListenner {
        void onTouchLetterChange(boolean isTouched, String s);
    }
}
