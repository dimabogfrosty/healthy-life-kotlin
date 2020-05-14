package by.jcoldsun.healthy_life.exception.entity

import javax.persistence.EntityNotFoundException

class RecordNotFoundException(message: String?) : EntityNotFoundException(message)