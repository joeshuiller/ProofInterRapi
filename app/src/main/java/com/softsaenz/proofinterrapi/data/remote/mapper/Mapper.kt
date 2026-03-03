package com.softsaenz.proofinterrapi.data.remote.mapper

interface Mapper<D, E, T, R> {
    fun fromEntity(data: E): D
    fun toEntity(data: D): E
    fun toResponse(data: R): T
}