package com.example.entity.entities

import com.example.entity.dao.ResourceDAO
import java.io.File
import javax.persistence.*

@Entity
@Table(name = "resources")
class ResourceRef(): IntEntity() {

    object DAO: ResourceDAO()

    constructor(file: File): this() {
        this.path = file.absolutePath
    }

    @Basic(fetch = FetchType.LAZY)
    @Column(name = "resource_path", nullable = false, unique = false)
    lateinit var path: String
}