package com.example.entity.dao

import arb.kordy.entity.dao.BaseDAO
import com.example.entity.entities.ResourceRef

open class ResourceDAO : BaseDAO<ResourceRef>(ResourceRef::class.java) {
    fun new(resource: ResourceRef): Int = save(resource)
}