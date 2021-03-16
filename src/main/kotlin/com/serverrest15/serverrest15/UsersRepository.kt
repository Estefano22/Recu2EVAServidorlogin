package com.serverrest15.serverrest15

import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<Users, Long>