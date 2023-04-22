package com.example.orders.domain.entities

data class Recipient(
    val firstName: String,
    val lastname: String,
    val address: String,
    val comment: String
)
