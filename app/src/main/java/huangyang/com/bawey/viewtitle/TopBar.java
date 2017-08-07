package huangyang.com.bawey.viewtitle;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * 创建人:hy
 * 日期:  2017/8/7
 */

public class TopBar extends RelativeLayout{

    /**
     * 左边按钮的文字颜色
     */
    private int mLeftTextColor;

    /**
     * 左边按钮的背景
     */
    private Drawable mLeftBackground;

    /**
     * 左边按钮的文字
     */
    private String mLeftText;

    /**
     * 右边按钮的文字颜色
     */
    private int mRightTextColor;

    /**
     * 右边按钮的背景
     */
    private Drawable mRightBackground;

    /**
     * 右边按钮的文字
     */
    private String mRightText;

    /**
     * 标题文字的颜色
     */
    private int mTitleTextColor;

    /**
     * 标题文字的大小
     */
    private float mTitleTextSize;

    /**
     * 标题文字
     */
    private String mTitleText;

    /**
     * 左边按钮
     */
    private Button mLeftButton;

    /**
     * 右边按钮
     */
    private Button mRightButton;

    /**
     * 标题
     */
    private TextView mTitleView;

    private LayoutParams mLeftParams;
    private LayoutParams mRightParams;
    private LayoutParams mTitleParams;

    private OnTopBarClickListener mListener;

    //添加点击接口
    public void setOnTopBarClickListener(OnTopBarClickListener listener) {
        mListener = listener;
    }

    //定义接口
    public interface OnTopBarClickListener {
        // 左按钮点击事件
        void leftClick();

        // 右按钮点击事件
        void rightClick();
    }

    public TopBar(Context context) {
        super(context);
    }

    public TopBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TopBar);

        // 从 TypedArray 中取出各属性对应的值
        // 取出的值是我们在 layout_topbar.xml 中给各属性赋的值
        mLeftTextColor = typedArray.getColor(R.styleable.TopBar_left_text_color, 0);
        mLeftBackground = typedArray.getDrawable(R.styleable.TopBar_left_bg);
        mLeftText = typedArray.getString(R.styleable.TopBar_left_text);

        //给右边的自定义控件设置字体颜色和背景色
        mRightTextColor = typedArray.getColor(R.styleable.TopBar_right_text_color, 0);
        mRightBackground = typedArray.getDrawable(R.styleable.TopBar_right_bg);
        mRightText = typedArray.getString(R.styleable.TopBar_right_text);

        //给中间的自定义标题设置字体颜色和背景色
        mTitleTextColor = typedArray.getColor(R.styleable.TopBar_title_text_color, 0);
        mTitleTextSize = typedArray.getDimension(R.styleable.TopBar_title_text_size, 10);
        mTitleText = typedArray.getString(R.styleable.TopBar_title_text);

        // 获取完 TypedArray 的值后，
        // 一般要调用 recycle 方法来避免重新创建的时候出错
        typedArray.recycle();

        //创建控件
        mLeftButton = new Button(context);
        mRightButton = new Button(context);
        mTitleView = new TextView(context);

        // 为创建的组件元素赋值
        mLeftButton.setBackgroundDrawable(mLeftBackground);
        mLeftButton.setText(mLeftText);
        mLeftButton.setTextColor(mLeftTextColor);

        mRightButton.setBackgroundDrawable(mRightBackground);
        mRightButton.setText(mRightText);
        mRightButton.setTextColor(mRightTextColor);

        mTitleView.setTextColor(mTitleTextColor);
        mTitleView.setTextSize(mTitleTextSize);
        mTitleView.setText(mTitleText);

        // 标题文字在 TextView 中心
        mTitleView.setGravity(Gravity.CENTER);

        // 左边按钮的布局参数
        mLeftParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        // 左边按钮在父布局的左边
        mLeftParams.addRule(ALIGN_PARENT_LEFT, TRUE);
        // 添加到父布局
        addView(mLeftButton, mLeftParams);

        // 右边按钮的布局参数
        mRightParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        // 右边按钮在父布局的右边
        mRightParams.addRule(ALIGN_PARENT_RIGHT, TRUE);
        // 添加到父布局
        addView(mRightButton, mRightParams);

        // 标题的布局参数
        mTitleParams = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT);
        // 标题在父布局的中心
        mTitleParams.addRule(CENTER_IN_PARENT, TRUE);
        // 添加到父布局
        addView(mTitleView, mTitleParams);

        //左边控件的监听事件
        mLeftButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.leftClick();
                }
            }
        });

        //右边控件的监听事件
        mRightButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.rightClick();
                }
            }
        });
    }

    public TopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
