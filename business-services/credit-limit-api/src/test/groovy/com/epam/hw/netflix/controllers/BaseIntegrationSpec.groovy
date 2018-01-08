package com.epam.hw.netflix.controllers

import com.mongodb.MongoClient
import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.IMongodConfig
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Shared
import spock.lang.Specification

class BaseIntegrationSpec extends Specification {

    static final String LOCALHOST = "localhost"
    static final int MONGO_TEST_PORT = 27017

    @Autowired
    WebApplicationContext context

    @Shared
    MongodExecutable mongodExecutable

    @Shared
    MongoClient mongoClient

    MockMvc mockMvc

    def setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build()
    }

    def setupSpec() {
        IMongodConfig mongodConfig = new MongodConfigBuilder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(LOCALHOST, MONGO_TEST_PORT, Network.localhostIsIPv6()))
                .build()

        MongodStarter mongodStarter = MongodStarter.defaultInstance
        mongodExecutable = mongodStarter.prepare mongodConfig
        mongodExecutable.start()
        mongoClient = new MongoClient(LOCALHOST, MONGO_TEST_PORT)
    }

    def cleanupSpec() {
        if (mongodExecutable) {
            mongodExecutable.stop()
        }
    }
}
