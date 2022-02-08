package com.example.entity.query

import com.example.entity.entities.IntEntity
import com.example.entityManagerFactory
import javax.persistence.criteria.CriteriaBuilder
import javax.persistence.criteria.CriteriaQuery
import javax.persistence.criteria.Predicate
import javax.persistence.criteria.Root

class SelectBuilder<Entity>(jClass: Class<Entity>) where Entity : IntEntity {

    private val entityManager = entityManagerFactory.createEntityManager()

    private val cb: CriteriaBuilder = entityManager.criteriaBuilder
    private val cq = cb.createQuery(jClass)
    private val rootEntry = cq.from(jClass)
    private val predicates = mutableListOf<Predicate>()

    fun filter(f: (cb: CriteriaBuilder, root: Root<Entity>) -> Predicate) {
        predicates.add(f.invoke(cb, rootEntry))
    }

    fun clear() = predicates.clear()

    fun build(): List<Entity> {
        cq.where(*(predicates.toTypedArray()))
        return entityManager.createQuery(cq).resultList
    }
}

typealias BSorter<Entity> = (CriteriaBuilder, CriteriaQuery<Entity>, Root<Entity>) -> Unit