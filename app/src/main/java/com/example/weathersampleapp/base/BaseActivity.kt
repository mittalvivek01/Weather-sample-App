package com.example.weathersampleapp.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.layout_toolbar.*

abstract class BaseActivity<VM : BaseViewModel, VDB : ViewDataBinding>: AppCompatActivity() {

    protected lateinit var viewModel: VM

    protected lateinit var dataBinding: VDB


    @get:LayoutRes
    abstract val layoutRes: Int

    abstract val bindingVariable: Int

    abstract val viewModelClass: Class<VM>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dataBinding = DataBindingUtil.setContentView(this, layoutRes)
        dataBinding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this).get(viewModelClass)
        dataBinding.setVariable(bindingVariable, viewModel)
        dataBinding.executePendingBindings()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        observeTitleEvent()


    }

    private fun observeTitleEvent() {
        (viewModel as BaseViewModel).title.observe(this, Observer {
            setTitle(it)
        })
    }

   private fun setTitle(title: String) {
        supportActionBar?.let {
          //  it.setDisplayShowHomeEnabled(false)
           // it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowTitleEnabled(true)
            it.setLogo(null)
            it.title = title
        }
    }
}