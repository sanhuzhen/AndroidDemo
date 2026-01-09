package com.sanhuzhen.sideslip

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
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
 * @date: 2024/8/8 18:21
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */
class Rv2Adapter: ListAdapter<String, Rv2Adapter.RvViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class RvViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
        private val tv : TextView = itemView.findViewById(R.id.item_tv2)
        private val delete: AppCompatImageButton = itemView.findViewById(R.id.item_btn_delete2)
        private val pin: AppCompatImageButton = itemView.findViewById(R.id.item_btn_pin2)

        fun bind(data: String) {
            tv.text = data
            delete.setOnClickListener {
                val currentList = currentList.toMutableList()
                val position = adapterPosition
                currentList.removeAt(position)
                submitList(currentList)
            }
            pin.setOnClickListener {
                val currentList = currentList.toMutableList()
                val position = adapterPosition
                currentList.add(0, currentList.removeAt(position))
                submitList(currentList)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        return RvViewHolder(View.inflate(parent.context, R.layout.item_main2_rv, null))
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}