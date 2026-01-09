package com.sanhuzhen.sideslip

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.sideslip.helper.ItemTouchHelperCallback
import com.sanhuzhen.sideslip.helper.SwipeToDeleteCallback

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
 * @description: 实现Rv的按钮侧滑置顶，删除功能
 * @author: sanhuzhen
 * @date: 2024/8/8 16:39
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */
class Main2Activity : AppCompatActivity() {

    private val DataList = mutableListOf<String>()
    private val rv by lazy {
        findViewById<RecyclerView>(R.id.rv_main2)
    }

    private val rvAdapter by lazy { Rv2Adapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        initList()
        rv.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(this@Main2Activity)
        }
        rvAdapter.submitList(DataList)
        val itemTouchHelperCallback = SwipeToDeleteCallback(
            rvAdapter
        )
        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(rv)
    }

    private fun initList() {
        repeat(20) {
            DataList.add("这是第${it}条数据")
        }
    }
}