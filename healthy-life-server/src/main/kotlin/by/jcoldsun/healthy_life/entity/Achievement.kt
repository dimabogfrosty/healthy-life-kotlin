package by.jcoldsun.healthy_life.entity

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import javax.persistence.*

@Entity
@Table(name = "achievements")
@JsonIgnoreProperties(value = ["users"])
data class Achievement(
        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "achievement_id_seq")
        @SequenceGenerator(name = "achievement_id_seq", sequenceName = "achievements_id_seq", allocationSize = 1)
        var id: Long? = null,
        var name: String = "",
        var description: String = "",
        @Column(name = "image_src") var imageSource: String = "",
        @ManyToMany(mappedBy = "achievements", targetEntity = User::class) var users: MutableList<User> = arrayListOf())