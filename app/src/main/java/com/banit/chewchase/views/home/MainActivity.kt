package com.banit.chewchase.views.home

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.banit.chewchase.adapters.OrderAdapter
import com.banit.chewchase.data.models.UserOrdersWithFoods
import com.banit.chewchase.databinding.ActivityMainBinding
import com.banit.chewchase.utils.PrefManager
import com.banit.chewchase.utils.loadActivity
import com.banit.chewchase.views.NewOrderActivity
import com.banit.chewchase.views.ViewOrderActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var orderAdapter: OrderAdapter
    private lateinit var mContext: Context
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        mContext = this
        setContentView(binding.root)

        orderAdapter = OrderAdapter(arrayListOf())
        orderAdapter.onOrderClickListener = object : OrderAdapter.OnOrderClickListener {
            override fun onOrderClick(userOrdersWithFoods: UserOrdersWithFoods) {
                val data = HashMap<String, String>()
                data["id"] = userOrdersWithFoods.order.orderId.toString()
                loadActivity(mContext, ViewOrderActivity::class.java, dataString = data)
            }
        }
        binding.recyclerView.adapter = orderAdapter

        binding.fabNewOrder.setOnClickListener {
            loadActivity(mContext, NewOrderActivity::class.java)
        }

        viewModel.fetchOrdersForUser(PrefManager().getUserID()).observe(this, Observer { orders ->
            if (orders.isNotEmpty()) {
                binding.emptyLayout.visibility = View.GONE
                orderAdapter.updateData(orders)
            }
        })
    }
}