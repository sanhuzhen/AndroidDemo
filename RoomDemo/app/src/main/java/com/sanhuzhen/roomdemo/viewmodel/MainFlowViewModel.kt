package com.sanhuzhen.roomdemo.viewmodel

import androidx.lifecycle.ViewModel
import com.sanhuzhen.roomdemo.bean.UserEntity
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * @description:
 * @author: sanhuzhen
 * @date: 2024/8/4 0:17
 * @email: sanhuzhen@foxmail.com
 * @version: 1.0
 */
class MainFlowViewModel : ViewModel() {

    private val _userFlow = MutableStateFlow(listOf<UserEntity>())
}