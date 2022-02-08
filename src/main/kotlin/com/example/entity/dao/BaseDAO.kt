package arb.kordy.entity.dao

import com.example.entity.entities.IntEntity
import com.example.entity.query.SelectBuilder
import com.example.entityManagerFactory
import javax.persistence.EntityManager
import javax.persistence.criteria.CriteriaBuilder

open class BaseDAO<Entity>(private val jClass: Class<Entity>) where Entity: IntEntity {
    protected val entityManager: EntityManager = entityManagerFactory.createEntityManager()

    val query = SelectBuilder(jClass)

    fun get(id: Int): Entity? = entityManager.find(jClass, id).also { if (it != null) entityManager.refresh(it) }

    protected fun getAll(): List<Entity> {
        val cb: CriteriaBuilder = entityManager.criteriaBuilder
        val cq = cb.createQuery(jClass)
        val rootEntry = cq.from(jClass)
        val all = cq.select(rootEntry)

        val entities = entityManager.createQuery(all).resultList
        for (i in entities) if (i != null) entityManager.refresh(i)

        return entities
    }

    fun save(entity: Entity): Int {
        entityManager.transaction.begin()
        entityManager.persist(entity)
        entityManager.flush()
        entityManager.transaction.commit()
        return entity.id
    }

    fun delete(id: Int) {
        entityManager.transaction.begin()
        entityManager.remove(get(id))
        entityManager.transaction.commit()
    }

    fun alternate(id: Int, transformer: (entity: Entity) -> Unit) {
        entityManager.transaction.begin()
        val entity = get(id)
        if (entity != null) {
            transformer(entity)
            entityManager.merge(entity)
        }
        entityManager.transaction.commit()
    }

    fun random(): Entity = getAll().random()
}