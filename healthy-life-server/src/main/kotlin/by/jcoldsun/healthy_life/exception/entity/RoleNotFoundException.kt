package by.jcoldsun.healthy_life.exception.entity

import javax.persistence.EntityNotFoundException

class RoleNotFoundException(message: String?) : EntityNotFoundException(message)