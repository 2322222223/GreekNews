package com.renlz.jiyun.greeknews.myview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.OvershootInterpolator;

import com.renlz.jiyun.greeknews.R;
import com.renlz.jiyun.greeknews.utils.GeometryUtil;
import com.renlz.jiyun.greeknews.utils.SizeUtils;

/**
 * Created by Administrator on 2018/12/31.
 */

public class UnReadRedPoint extends View {

    private static final int RADIUS_UNREAD = 15;
    private static final int RADIUS_FIXED = 12;
    private static final int RADIUS_MIN = 6;
    private static final int RADIUS_MAX = 120;
    private static final int SIZE_TEXT = 16;

    private Context mContext;
    //屏幕中心点的XY坐标
    private float mCenterX, mCenterY;
    //未读消息的圆点的圆心坐标
    private PointF mUnReadCenter;
    //未读消息的圆点的半径
    private float mUnReadRadius;
    //未读消息数目
    private int mUnReadCount;
    //画笔
    private Paint mPaint;
    //按下的点的坐标
    private float mDownX, mDownY;
    //固定圆的圆心
    private PointF mFixedCenter;
    //固定圆的半径
    private float mFixedRadius;
    //移动点距离固定点（屏幕中心）的距离
    private float mDistanceBetweenMoveAndFixed;
    //有拖拽效果的最大范围
    private float mMaxRadius;
    //抬起点的坐标
    private PointF mUpPointF;
    //抬起点和固定圆的距离
    private float mDistanceBetweenUPAndFixed;
    //爆炸动画相关
    private int mExplodeIndex;
    private int[] mExplodes = {R.mipmap.explode1, R.mipmap.explode2, R.mipmap.explode3, R.mipmap.explode4, R.mipmap
            .explode5};
    private Bitmap[] mExplodeBitmaps;
    //是否超出过范围
    private boolean isBeyond = false;

    //回调监听接口
    private OnUnReadRedPointListener mOnUnReadRedPointListener;


    public UnReadRedPoint(Context context) {
        this(context, null);
    }

    public UnReadRedPoint(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnReadRedPoint(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initResource();
    }

    /**
     * <p>初始化相关的资源参数</p>
     */
    private void initResource() {

        mUnReadRadius = SizeUtils.dp2px(RADIUS_UNREAD);
        mFixedRadius = SizeUtils.dp2px(RADIUS_FIXED);
        mMaxRadius = SizeUtils.dp2px(RADIUS_MAX);

        mExplodeBitmaps = new Bitmap[mExplodes.length];
        for (int i = 0; i < mExplodes.length; i++) {
            mExplodeBitmaps[i] = BitmapFactory.decodeResource(getResources(), mExplodes[i]);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        mCenterX = width / 2;
        mCenterY = height / 2;
        PointF centerPointF = new PointF(mCenterX, mCenterY);
        setUnReadCenter(centerPointF);
        setFixedCenter(centerPointF);

    }

    public int getUnReadCount() {
        return mUnReadCount;
    }

    public void setUnReadCount(int unReadCount) {
        mUnReadCount = unReadCount;
    }

    public PointF getUnReadCenter() {
        return mUnReadCenter;
    }

    public void setUnReadCenter(PointF unReadCenter) {
        mUnReadCenter = unReadCenter;
    }

    public PointF getFixedCenter() {
        return mFixedCenter;
    }

    public void setFixedCenter(PointF fixedCenter) {
        mFixedCenter = fixedCenter;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设定画笔抗锯齿
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //画笔设置为黑色
        mPaint.setColor(Color.BLACK);
        //设置画笔的填充方式
        mPaint.setStyle(Paint.Style.STROKE);
        //辅助线
        canvas.drawLine(0, mCenterY, mCenterX * 2, mCenterY, mPaint);
        canvas.drawLine(mCenterX, 0, mCenterX, mCenterY * 2, mPaint);
        canvas.drawCircle(mCenterX, mCenterY, mMaxRadius, mPaint);
        //设置画笔的填充方式
        mPaint.setStyle(Paint.Style.FILL);
        //设置画笔颜色
        mPaint.setColor(Color.RED);
        //画固定圆
        //固定圆的半径
        //如果手指和固定圆圆心的距离大于最大半径（范围外），则不需要画出固定圆
        if (mDistanceBetweenMoveAndFixed - mUnReadRadius > mMaxRadius) {
            mFixedRadius = 0;
        } else {

            mFixedRadius = SizeUtils.dp2px(RADIUS_FIXED) - SizeUtils.dp2px(RADIUS_MIN) * mDistanceBetweenMoveAndFixed
                    / mMaxRadius;
            //绘制贝塞尔曲线
            if (!isBeyond) {

                drawBezier(canvas);
            }
            //画固定圆
            if (!isBeyond) {

                canvas.drawCircle(mFixedCenter.x, mFixedCenter.y, mFixedRadius, mPaint);
            }
        }

        if (mDistanceBetweenUPAndFixed - mUnReadRadius > mMaxRadius) {

            if (mExplodeIndex < mExplodes.length) {
                canvas.drawBitmap(mExplodeBitmaps[mExplodeIndex], mUnReadCenter.x, mUnReadCenter.y, mPaint);
            }

        } else {
            //画移动圆
            canvas.drawCircle(mUnReadCenter.x, mUnReadCenter.y, mUnReadRadius, mPaint);
            //设置文字大小
            mPaint.setTextSize(SizeUtils.sp2px(SIZE_TEXT));
            //设置画笔颜色
            mPaint.setColor(Color.BLACK);
            //设置文字居中
            String s = String.valueOf(mUnReadCount);
            mPaint.setTextAlign(Paint.Align.CENTER);
            mPaint.setStyle(Paint.Style.FILL);
            Rect bounds = new Rect();
            mPaint.getTextBounds(s, 0, s.length(), bounds);
            //绘制文字
            canvas.drawText(s, mUnReadCenter.x - bounds.left / 2f, mUnReadCenter.y - bounds.centerY(), mPaint);

        }

    }

    /**
     * <p>绘制贝塞尔曲线</p>
     *
     * @param canvas
     */
    private void drawBezier(Canvas canvas) {
        //设置画笔属性
        mPaint.setColor(Color.RED);
        //获取两点连线的中点
        PointF middlePoint = GeometryUtil.getMiddlePoint(mUnReadCenter, mFixedCenter);
        //计算两圆圆心连线的斜率
        float offSetY = mUnReadCenter.y - mFixedCenter.y;
        float offSetX = mUnReadCenter.x - mFixedCenter.x;
        Double lineK = null;
        if (offSetX != 0) {
            lineK = Double.valueOf(offSetY / offSetX);
        } else {

        }

        //得到固定圆的附着点
        PointF[] fixedPoints = GeometryUtil.getIntersectionPoints(mFixedCenter, mFixedRadius, lineK);
        PointF[] unReaderPoints = GeometryUtil.getIntersectionPoints(mUnReadCenter, mUnReadRadius, lineK);
        //绘制路径
        //两圆连线的中点为曲线的控制点，同侧的附着点为固定点
        Path path = new Path();
        path.moveTo(fixedPoints[0].x, fixedPoints[0].y);
        //画出一半贝塞尔曲线
        path.quadTo(middlePoint.x, middlePoint.y, unReaderPoints[0].x, unReaderPoints[0].y);
        //连接两点
        path.lineTo(unReaderPoints[1].x, unReaderPoints[1].y);
        //画出另一半贝塞尔曲线
        path.quadTo(middlePoint.x, middlePoint.y, fixedPoints[1].x, fixedPoints[1].y);
        //闭合路径
        path.close();
        //根据设置的路径作画
        canvas.drawPath(path, mPaint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mDownY = event.getY();
                PointF downPoint = new PointF(mDownX, mDownY);
                //只有在按下的位置是移动圆的位置的时候，才继续执行
                if (GeometryUtil.getDistanceBetween2Points(downPoint, mUnReadCenter) < mUnReadRadius + 2f) {

                    setUnReadCenter(downPoint);
                    invalidate();
                } else {
                    return false;
                }

                break;
            case MotionEvent.ACTION_MOVE:
                mDownX = event.getX();
                mDownY = event.getY();
                PointF movePointF = new PointF(mDownX, mDownY);
                setUnReadCenter(movePointF);
                mDistanceBetweenMoveAndFixed = GeometryUtil.getDistanceBetween2Points(mFixedCenter, movePointF);
                if (mDistanceBetweenMoveAndFixed > mMaxRadius) {
                    isBeyond = true;
                }
                invalidate();

                break;
            case MotionEvent.ACTION_UP:
                float upX = event.getX();
                float upY = event.getY();
                mUpPointF = new PointF(upX, upY);
                mDistanceBetweenUPAndFixed = GeometryUtil.getDistanceBetween2Points(mFixedCenter, mUpPointF);
                //抬起点在最大范围之内
                if (mDistanceBetweenUPAndFixed - mUnReadRadius <= mMaxRadius) {
                    //开始动画
                    startBackAnim();
                    invalidate();

                } else {
                    // 抬起点在最大范围之外
                    // 开始爆炸效果动画
                    startDismissAnim();
                    invalidate();
                    isBeyond = false;
                    setDismiss();
                }

                break;
        }
        return true;
    }

    /**
     * <p>消失动画效果</p>
     */
    private void startDismissAnim() {
        ValueAnimator animatorDismiss = ValueAnimator.ofInt(0, mExplodes.length);
        animatorDismiss.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mExplodeIndex = (int) animation.getAnimatedValue();
                invalidate();
            }
        });
        animatorDismiss.setDuration(300);
        animatorDismiss.start();
    }

    /**
     * <p>回弹动画</p>
     */
    private void startBackAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1.0f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float animatedFraction = animation.getAnimatedFraction();
                //获取手指离开的距离
                PointF pointByPercent = GeometryUtil.getPointByPercent(mUpPointF, mFixedCenter, animatedFraction);
                //动态设定距离
                setUnReadCenter(pointByPercent);
                invalidate();
            }
        });

        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                isBeyond = false;
            }
        });

        if (!isBeyond) {
            valueAnimator.setInterpolator(new TimeInterpolator() {
                @Override
                public float getInterpolation(float input) {
                    float f = 0.571429f;
                    return (float) (Math.pow(2, -4 * input) * Math.sin((input - f / 4) * (2 * Math.PI) / f) + 1);
                }
            });

            valueAnimator.setDuration(300);
        } else {
            valueAnimator.setInterpolator(new OvershootInterpolator(1));
            valueAnimator.setDuration(50);
        }

        valueAnimator.start();

    }

    /**
     * <p>定义接口</p>
     */
    public interface OnUnReadRedPointListener {
        void onDismiss();
    }

    public void setOnUnReadRedPointListener(OnUnReadRedPointListener onUnReadRedPointListener) {
        mOnUnReadRedPointListener = onUnReadRedPointListener;
    }

    public void setDismiss() {
        if (mOnUnReadRedPointListener != null) {
            mOnUnReadRedPointListener.onDismiss();
        }
    }
}




