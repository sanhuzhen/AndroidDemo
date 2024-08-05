package com.sanhuzhen.calendar

import android.content.Context
import android.icu.util.Calendar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

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
 * @date: 2024/8/5 23:27
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */
class CalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    //创建 Calendar 对象实例
    private val calendar = Calendar.getInstance()

    private var year = calendar.get(Calendar.YEAR)
    private var month = calendar.get(Calendar.MONTH)
    private var day = calendar.get(Calendar.DAY_OF_MONTH)
    private val preButton: androidx.appcompat.widget.AppCompatButton
    private val nextButton: androidx.appcompat.widget.AppCompatButton
    private val month_text: TextView
    private val week_rv: RecyclerView
    private val day_gl: GridLayout

    init {
        //加载布局
        LayoutInflater.from(context).inflate(R.layout.bottom_calendar_dialog, this, true)

        preButton = findViewById(R.id.per_month)
        nextButton = findViewById(R.id.next_month)
        month_text = findViewById(R.id.date_month)
        week_rv = findViewById(R.id.data_week_rv)
        day_gl = findViewById(R.id.data_day_gl)
        //初始化日历
        setupCalendar()
        initClick()
    }

    private fun initClick() {
        preButton.setOnClickListener {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1)
            setupCalendar()
        }
    }

    private fun setupCalendar() {
        // 获取当月的第一天和最后一天
        val firstDayOfMonth = calendar.getActualMinimum(Calendar.DAY_OF_MONTH)
        val lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        // 获取当月第一天是星期几
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1 // 调整为零基索引

        var day = firstDayOfMonth
        // 遍历GridLayout的所有子视图
        for (i in 0 until day_gl.childCount) {
            val cell = day_gl.getChildAt(i) as TextView
            // 如果当前索引在当月第一天的星期几之后且小于等于当月的最后一天
            if (i >= dayOfWeek && day <= lastDayOfMonth) {
                // 设置日期
                cell.text = day.toString()
                day++
            } else {
                // 清空日期
                cell.visibility = GONE
            }
        }
    }
}