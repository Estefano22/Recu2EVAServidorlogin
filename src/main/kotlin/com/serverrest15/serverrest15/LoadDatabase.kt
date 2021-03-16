package com.serverrest15.serverrest15

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LoadDatabase {
    companion object {
        val logger = LoggerFactory.getLogger(LoadDatabase.javaClass)
    }

    @Bean
    fun initDatabase(usersRepository : UsersRepository) : CommandLineRunner {
        return CommandLineRunner { args: Array<String?>? ->
            logger.info(" El User" + usersRepository.save(Users("Juan", "Abc.,")))

        }

    }

}