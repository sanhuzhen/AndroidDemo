package com.sanhuzhen.calendar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.text.util.LocalePreferences.FirstDayOfWeek
import java.util.Calendar

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
 */
/**
 * @description:
 * @author: sanhuzhen
 * @date: 2024/8/8 22:28
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */
class CustomCalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : View(context, attrs) {

    /**
     * 创建 Calendar 对象实例
     */
    private val calendar: Calendar = Calendar.getInstance()
    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private var selectedDay: Int = day

    private val WeekString = arrayOf("一", "二", "三", "四", "五", "六", "日")

    // 创建画笔
    private val headerPaint = Paint().apply {
        color = Color.BLACK
        textSize = 30f
        style = Paint.Style.FILL
        isAntiAlias = true//抗锯齿
    }

    private val buttonPaint = Paint().apply {
        color = Color.GRAY
        textSize = 35f
        style = Paint.Style.FILL
        isAntiAlias = true//抗锯齿
    }

    private val weekPaint = Paint().apply {
        color = Color.GRAY
        textSize = 25f
        style = Paint.Style.FILL
        isAntiAlias = true//抗锯齿
    }

    private val preDayPaint = Paint().apply {
        color = Color.GRAY
        textSize = 30f
        style = Paint.Style.FILL
        isAntiAlias = true//抗锯齿
    }

    private val currentDayPaint = Paint().apply {
        color = Color.BLUE
        textSize = 30f
        style = Paint.Style.FILL
        isAntiAlias = true//抗锯齿
    }

    private val nextDayPaint = Paint().apply {
        color = Color.BLACK
        textSize = 30f
        style = Paint.Style.FILL
        isAntiAlias = true//抗锯齿
    }

    private val selectPaint = Paint().apply {
        style = Paint.Style.STROKE //只描边
        color = Color.BLUE
        strokeWidth = 2f
        isAntiAlias = true
    }

    @SuppressLint("DefaultLocale")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //绘制头部
        val headerText =
            "${calendar.get(Calendar.YEAR)}/${
                String.format(
                    "%02d",
                    calendar.get(Calendar.MONTH) + 1
                )
            }"
        val HeaderWidth = width / 2 - headerPaint.measureText(headerText) / 2
        val HeaderHeight = height * 0.1f - headerPaint.descent() / 2
        canvas.drawText(headerText, HeaderWidth, HeaderHeight, headerPaint)

        //绘制按钮
        val preButtonWidth = HeaderWidth - buttonPaint.measureText("<") * 3
        val nextButtonWidth =
            width / 2 + headerPaint.measureText(headerText) / 2 + buttonPaint.measureText(">") * 3
        val buttonHeight = HeaderHeight
        canvas.drawText("<", preButtonWidth, buttonHeight, buttonPaint)
        canvas.drawText(">", nextButtonWidth, buttonHeight, buttonPaint)

        //绘制星期

        val weekWidth = width / 7
        val weekHeight = HeaderHeight + 30
        for (i in 0..6) {
            val weekText = WeekString[i]
            val weekTextWidth = weekWidth / 2 - weekPaint.measureText(weekText) / 2
            canvas.drawText(
                weekText,
                weekTextWidth + i * weekWidth,
                weekHeight + weekPaint.measureText(weekText),
                weekPaint
            )
        }

        //绘制日期
        val totalDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        //获取当月第一天是星期几
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 2
        for (i in 1..totalDay) {
            val week = (firstDayOfWeek + i - 1) / 7
            val dayWidth =
                (firstDayOfWeek + i - 1) % 7 * weekWidth + weekWidth / 2 - preDayPaint.measureText(
                    i.toString()
                ) / 2
            val dayHeight = weekHeight + weekPaint.measureText(WeekString[0]) + (week + 1) * 70
            if (i == day && month == calendar.get(Calendar.MONTH) && year == calendar.get(Calendar.YEAR)) {
                canvas.drawText(
                    i.toString(),
                    dayWidth,
                    dayHeight,
                    currentDayPaint
                )
                canvas.drawCircle(
                    dayWidth + currentDayPaint.measureText(i.toString()) / 2f,
                    dayHeight - currentDayPaint.measureText(i.toString()) / 3f,
                    Math.min(
                        currentDayPaint.measureText(i.toString()),
                        currentDayPaint.measureText(i.toString())
                    ),
                    selectPaint
                )
            } else if (i < day) {
                canvas.drawText(
                    i.toString(),
                    dayWidth,
                    dayHeight,
                    preDayPaint
                )
            } else if (i > day) {
                canvas.drawText(
                    i.toString(),
                    dayWidth,
                    dayHeight,
                    nextDayPaint
                )
            }
        }

    }
}