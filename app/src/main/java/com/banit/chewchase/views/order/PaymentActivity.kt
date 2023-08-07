package com.banit.chewchase.views.order

import android.annotation.SuppressLint
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.banit.chewchase.databinding.ActivityPaymentBinding
import com.banit.chewchase.utils.DialogUtils
import com.banit.chewchase.utils.formatCurrency

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding:ActivityPaymentBinding
    private lateinit var mContext:Context

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        mContext = this
        setContentView(binding.root)

        val amount = intent.getStringExtra("amount")!!
        binding.btnPay.text = "Pay Now $${formatCurrency(amount)}"
        binding.btnPay.setOnClickListener {
            pay()
        }
    }

    private fun pay() {

        if (binding.edCardNumber.text.toString().isEmpty()){
            binding.edCardNumberLayout.error="Card number required!"
            return
        }

        if (binding.edExpiryDate.text.toString().isEmpty()){
            binding.edExpiryDateLayout.error="Expiry date required!"
            return
        }

        if (binding.edCvc.text.toString().isEmpty()){
            binding.edCvcLayout.error="CVC required!"
            return
        }

        DialogUtils.isPayed = true
        finish()
    }
}