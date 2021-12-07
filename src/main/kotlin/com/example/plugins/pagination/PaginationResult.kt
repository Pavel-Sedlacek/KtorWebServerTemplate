package com.example.plugins.pagination

data class PaginationResult<Entity>(
    val totalEntityCount: Int,
    val pageCount: Int,
    var entities: List<Entity>,
    val page: Int,
    val perPage: Int,
)