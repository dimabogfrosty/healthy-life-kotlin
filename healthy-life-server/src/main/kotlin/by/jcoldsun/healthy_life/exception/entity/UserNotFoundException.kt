package by.jcoldsun.healthy_life.exception.entity

import javax.persistence.EntityNotFoundException

class UserNotFoundException(message: String?) : EntityNotFoundException(message)