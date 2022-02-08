package com.example.entity.filter.sort

import com.example.entity.query.BSorter
import javax.persistence.criteria.*
import javax.persistence.metamodel.EmbeddableType
import javax.persistence.metamodel.SingularAttribute
import kotlin.reflect.KFunction2

class Sorter<T>(
    private val f: KFunction2<CriteriaBuilder, Expression<*>, Order>,
    private val field: SingularAttribute<T, *>,
    private val path: List<SingularAttribute<out Any, *>> = listOf()
) {
    private fun sorter(): BSorter<T> = { criteriaBuilder, criteriaQuery, root ->
        criteriaQuery.orderBy(
            f.invoke(
                criteriaBuilder,
                root.get(field as SingularAttribute<in T, *>).next(path)
            )
        )
    }

    operator fun invoke(): BSorter<T> = this.sorter()
}

private tailrec fun Path<*>.next(path: List<SingularAttribute<out Any, *>>): Path<*> =
    if (path.isEmpty()) this
    else this.get(path.first() as SingularAttribute<in Any, *>).next(path.drop(1))

