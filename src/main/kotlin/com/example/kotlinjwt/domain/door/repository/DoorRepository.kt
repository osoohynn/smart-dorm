package com.example.kotlinjwt.domain.door.repository

import com.example.kotlinjwt.domain.door.domain.entity.Door
import org.springframework.data.jpa.repository.JpaRepository

interface DoorRepository : JpaRepository<Door, Long> {
}