package by.jcoldsun.healthy_life.service

interface BaseService<T> {
    fun getAll(): List<T>
    fun getById(id: Long): T?
    fun save(entity: T): T
    fun delete(id: Long)
}