package com.example.entity.dao

import arb.kordy.entity.dao.BaseDAO
import com.example.entity.entities.IntEntity
import com.example.entity.filter.BaseFilter
import com.example.entity.query.BSorter
import com.example.plugins.pagination.Pagination
import com.example.plugins.pagination.PaginationResult
import com.example.utils.asInt
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.defaultType
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.withNullability

abstract class DAOExtension<Entity, Filter, EditModel : Any>(private val jClass: Class<Entity>): BaseDAO<Entity>(jClass) where Filter : BaseFilter, Entity : IntEntity {

    protected fun paginate(filter: Filter): PaginationResult<Entity> {
        val x = filter(filter)
        return Pagination.create(x, filter)
    }

    abstract fun filter(filter: Filter): List<Entity>

    protected fun sFilter(filter: Filter, sorter: BSorter<Entity>? = null, block: () -> Unit = {}): List<Entity> {
        query.clear()
        block()
        if (sorter != null)
            query.order(sorter)
        val x = query.build()
        query.clear()
        return x
    }

    fun edit(updateModel: EditModel, applyAfter: (entity: Entity) -> Unit = {}) {
        val entity = get(
            updateModel::class.memberProperties.find { it.name == "id" && it.returnType == Int::class.defaultType }?.getter?.call(
                updateModel
            ).asInt() ?: -1
        ) ?: return

        updateModel::class.memberProperties.filter { it.name != "id" && it.getter.call(updateModel) != null }
            .forEach { im ->
                entity::class.memberProperties.filterIsInstance<KMutableProperty<*>>()
                    .find { it.name == im.name && it.returnType.withNullability(true) == im.returnType }
                    .also { it?.setter?.call(entity, im.getter.call(updateModel) ?: it.getter.call(entity)) }
            }


        applyAfter(entity)

        entityManager.transaction.begin()
        entityManager.merge(entity)
        entityManager.transaction.commit()
    }

}