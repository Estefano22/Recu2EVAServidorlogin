package com.serverrest15.serverrest15

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Users (var name : String, var password : String){
    @Id
    @GeneratedValue
    private val id: Long? = null

    override fun equals(other: Any?): Boolean {
        if (other is Users){
            return other.id == id && id != null
        } else {
            return false
        }
    }

    override fun hashCode(): Int {
        return Objects.hash(id, name, password)
    }

    override fun toString(): String {
        return " $id $name con su password $password "
    }

}