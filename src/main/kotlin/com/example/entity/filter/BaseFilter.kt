package com.example.entity.filter

open class BaseFilter(
    open var search: String = "",
    open var pagination: Boolean = false,
    open var page: Int = -(if (pagination) 1 else -1),
    open var perPage: Int = (if (pagination) 2 else -1),
) {
    companion object {
        val base = BaseFilter()
    }
}