package com.softsaenz.proofinterrapi.data.local.mapper

interface MapperDao<D, E> {
    fun fromEntity(data: E): D
    fun toEntity(data: D): E
}