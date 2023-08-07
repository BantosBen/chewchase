package com.banit.chewchase.data.repository

import com.banit.chewchase.data.dao.OrderDAO
import javax.inject.Inject

class OrderRepository @Inject constructor(private val orderDAO: OrderDAO) {

    suspend fun getAllOrdersForUser(userId: Int) = orderDAO.getUserOrdersWithFoods(userId)

}