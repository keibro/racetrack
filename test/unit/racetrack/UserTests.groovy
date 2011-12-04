package racetrack

import grails.test.GrailsUnitTestCase
import org.codehaus.groovy.grails.plugins.codecs.Base64Codec

class UserTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
        String.metaClass.encodeAsBase64 = {->
            Base64Codec.encode(delegate)
        }
        String.metaClass.encodeAsSHA = {->
            racetrack.SHACodec.encode(delegate)
        }
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSimpleConstraints() {
        mockForConstraintsTests(User)
        def user = new User(login: "someone",
                password: "blah",
                role: "SuperUser")

        assertFalse(user.validate())
        assertEquals "inList", user.errors["role"]

    }

    void testUniqueConstraint() {
        def jdoe = new User(login: "jdoe")
        def admin = new User(login: "admin")
        mockDomain(User, [jdoe, admin])
        def badUser = new User(login: "jdoe")
        badUser.save()
        assertEquals 2, User.count()
        assertEquals "unique", badUser.errors["login"]
        def goodUser = new User(login: "good",
                password: "password",
                role: "user")
        goodUser.save()
        assertEquals 3, User.count()
        assertNotNull User.findByLoginAndPassword(
                "good", "password".encodeAsSHA())
    }
}
