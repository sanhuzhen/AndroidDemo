package com.sanhuzhen.sideslip.helper

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.sanhuzhen.sideslip.RvAdapter

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
 * @description: 为了实现Rv的侧滑置顶，删除功能
 * @author: sanhuzhen
 * @date: 2024/8/8 16:50
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */
class ItemTouchHelperCallback(private val adapter: RvAdapter): ItemTouchHelper.Callback() {

    //是否可以侧滑
    override fun isItemViewSwipeEnabled(): Boolean {
        return true
    }

    /**
     * Callback回调监听时先调用的，用来判断当前是什么动作，比如判断方向
     */
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {

        //拖拽
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        //侧滑
        val swipeFlags = ItemTouchHelper.START
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    /**
     * 是否可以长按拖拽
     */
    override fun isLongPressDragEnabled(): Boolean {
        return true
    }

    /**
     * 当上下移动的时候回调的方法
     */
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        val currentList = adapter.currentList.toMutableList()
        val fromPosition = viewHolder.adapterPosition
        val toPosition = target.adapterPosition

        // 移动项目
        val movedItem = currentList.removeAt(fromPosition)
        currentList.add(toPosition, movedItem)

        adapter.submitList(currentList)
//        adapter.notifyItemMoved(fromPosition, toPosition)
        return true
    }

    /**
     * 当侧滑删除的时候回调的方法
     */
    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        val currentList = adapter.currentList.toMutableList().apply {
            removeAt(position)
        }
        adapter.submitList(currentList)

    }
}