package ar.edu.um.network

import ar.edu.um.network.model.Product
import ar.edu.um.network.model.ProductRequest
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class ProductApiService(private val baseUrl: String = "http://10.0.2.2:8080") {
    private val httpClient = NetworkUtils.httpClient
    suspend fun getAllProducts(): List<Product>{
        return httpClient.get("$baseUrl/products").body()
    }

    suspend fun getProductById(id: Long): Product? {
        return try {
            httpClient.get("$baseUrl/products/$id").body()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun createProduct(request: ProductRequest): Product {
        return httpClient.post("$baseUrl/products") {
            contentType(ContentType.Application.Json)
            setBody(request)
        }.body()
    }

    suspend fun updateProduct(id: Long, request: ProductRequest): Product? {
        return try {
            httpClient.put("$baseUrl/products/$id") {
                contentType(ContentType.Application.Json)
                setBody(request)
            }.body()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun deleteProduct(id: Long): Boolean {
        return try {
            httpClient.delete("$baseUrl/products/$id")
            true
        } catch (e: Exception) {
            false
        }
    }
}