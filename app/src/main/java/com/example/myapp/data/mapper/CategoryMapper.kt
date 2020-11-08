package com.example.myapp.data.mapper

import com.example.myapp.data.response.category.CategoryResponse
import com.example.myapp.domain.model.Category

fun CategoryResponse.toDomain() = Category(
    idcategoria = idcategoria ?: 0,
    nombre = nombre ?: ""
)