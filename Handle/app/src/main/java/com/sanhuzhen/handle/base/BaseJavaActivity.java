package com.sanhuzhen.handle.base;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

/**
 * description:
 * author: sanhuzhen
 * date: 2024/12/1 20:53
 */
public abstract class BaseJavaActivity<VB extends ViewBinding> extends AppCompatActivity {
    protected VB binding;

    public abstract VB getViewBinding();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = getViewBinding();
        setContentView(binding.getRoot());
    }
}
