package com.sbouhaddi.model

import jakarta.persistence.*

@Entity
@Table(name = "BOOK")
data class Book(
    @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long? = null,
    var title: String? = null,
    var description: String? = null,
    var price: Double? = null
)