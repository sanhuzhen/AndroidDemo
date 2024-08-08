package com.sanhuzhen.calendar

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
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
     * 日历对象
     */
    private val calendar: Calendar = Calendar.getInstance()
    private var selectedDay: Int = calendar.get(Calendar.DAY_OF_MONTH)


    private val WeekStringList = listOf("一", "二", "三", "四", "五", "六", "日")

    /**
     * 画笔，第一个用来画出选中日期的圆圈
     */
    private val paint = Paint().apply {
        style = Paint.Style.STROKE //只描边
        color = Color.BLUE
        strokeWidth = 5f
    }

    /**
     *
     */
    private val textDayPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.BLACK
        textSize = 40f
        isAntiAlias = true
    }

    private val textWeekPaint = Paint().apply {
        style = Paint.Style.FILL
        color = Color.GRAY
        textSize = 40f
        isAntiAlias = true
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        /**
         * 获取当前月份的总天数
         */
        val totalDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

        /**
         * 获取每个日期的宽度和高度
         */
        val widthPerDay = width / 7f
        val heightPerWeek = height / 6f
        val horizontalPadding = (width - widthPerDay * 7) / 2f // 计算水平间距
        val verticalPadding = (height - heightPerWeek * 6) / 2f // 计算垂直间距

        for (day in 1..totalDays) {
            val week = (day - 1) / 7
            val dayOfWeek = (day - 1) % 7

            val cx = horizontalPadding + (dayOfWeek + 0.5f) * widthPerDay
            val cy = verticalPadding + (week + 0.5f) * heightPerWeek

            if (day == selectedDay) {
                canvas.drawCircle(cx, cy + textDayPaint.textSize * 1.5f, Math.min(widthPerDay, heightPerWeek) / 2 - 10, paint)
            }
            if (day <= 7){
                canvas.drawText(WeekStringList[day-1], cx - textWeekPaint.measureText(day.toString()) / 2, cy + textWeekPaint.textSize / 4, textWeekPaint)
            }

            canvas.drawText(day.toString(), cx - textDayPaint.measureText(day.toString()) / 2, cy + textDayPaint.textSize * 2, textDayPaint)
        }
    }

    fun setSelectedDay(day: Int) {
        selectedDay = day
        invalidate()
    }

    fun nextMonth() {
        calendar.add(Calendar.MONTH, 1)
        invalidate()
    }

    fun previousMonth() {
        calendar.add(Calendar.MONTH, -1)
        invalidate()
    }
}