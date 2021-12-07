package com.example.plugins.pagination

import com.example.entity.filter.BaseFilter

object Pagination {
    fun <Entity> create(entities: List<Entity>, filter: BaseFilter = BaseFilter.base): PaginationResult<Entity> {
        val pagedEntities = entities.chunked(filter.perPage)
        return PaginationResult(
            entities = pagedEntities[filter.page - 1],
            totalEntityCount = entities.size,
            pageCount = pagedEntities.size,
            page = filter.page,
            perPage = pagedEntities[filter.page - 1].size
        )
    }
}