package com.unprg.alquilafacil.data.mapper

import com.unprg.alquilafacil.data.response.category.CategoryResponse
import com.unprg.alquilafacil.domain.model.Category

fun CategoryResponse.toDomain() = Category(
    idcategoria = idcategoria ?: 0,
    nombre = nombre ?: ""
)