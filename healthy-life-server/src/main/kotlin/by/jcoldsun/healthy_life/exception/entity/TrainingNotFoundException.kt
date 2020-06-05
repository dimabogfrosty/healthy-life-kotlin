package by.jcoldsun.healthy_life.exception.entity

import javax.persistence.EntityNotFoundException

class TrainingNotFoundException(message: String?) : EntityNotFoundException(message)