package ar.edu.um.network.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductRequest(
    val description: String,
    val price: Double
)