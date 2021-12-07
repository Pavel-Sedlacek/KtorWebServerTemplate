package com.example.entity.dao

import com.example.entity.entities.IntEntity
import com.example.entity.filter.BaseFilter
import com.example.entity.query.SelectBuilder
import com.example.entityManagerFactory
import com.example.plugins.pagination.Pagination
import com.example.plugins.pagination.PaginationResult
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder
import kotlin.reflect.KFunction1

open class BaseDao<Entity, Filter>(
    private val jClass: Class<Entity>,
    private val listFilter: KFunction1<Filter, List<Entity>>,
) where Filter : BaseFilter, Entity : IntEntity {

    protected val entityManager: EntityManager = entityManagerFactory.createEntityManager()

    val filterBuilder = SelectBuilder(jClass)

    fun get(id: Int): Entity? = entityManager.find(jClass, id)

    protected fun getAll(): List<Entity> {
        val cb: CriteriaBuilder = entityManager.criteriaBuilder
        val cq = cb.createQuery(jClass)
        val rootEntry = cq.from(jClass)
        val all = cq.select(rootEntry)

        val allQuery = entityManager.createQuery(all)

        return allQuery.resultList
    }

    protected fun paginate(filter: Filter): PaginationResult<Entity> {
        return Pagination.create(listFilter.invoke(filter), filter)
    }

    protected fun save(entity: Entity) {
        entityManager.transaction.begin()
        entityManager.persist(entity)
        entityManager.transaction.commit()
    }

    protected fun change(entity: Entity) {
        entityManager.transaction.begin()
        entityManager.merge(entity)
        entityManager.transaction.commit()
    }

    fun remove(id: Int) {
        entityManager.transaction.begin()
        entityManager.remove(get(id))
        entityManager.transaction.commit()
    }
}