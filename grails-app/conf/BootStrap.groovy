import racetrack.Race
import racetrack.Registration
import racetrack.Runner
import grails.util.GrailsUtil
import racetrack.User

class BootStrap {

    def init = { servletContext ->
        println "BootStrap init ..."

        switch (GrailsUtil.environment) {
            case "development":

                // Users
                def admin = new User(login:"admin", password:"wordpass", role:"admin")
                admin.save()
                if(admin.hasErrors()) {
                    println admin.errors
                }

                def jdoe = new User(login:"jdoe", password:"password", role:"user")
                jdoe.save()
                if(jdoe.hasErrors()) {
                    println jdoe.errors
                }

                // Runners
                def jane = new Runner(
                        firstName: "Jane",
                        lastName: "Doe",
                        dateOfBirth: (new Date() - 365 * 30),
                        gender: "F",
                        address: "123 Main St",
                        city: "Goose",
                        state: "NC",
                        zipcode: "12345",
                        email: "jane@whereever.com"
                )
                jane.save()
                if (jane.hasErrors()) {
                    println jane.errors
                }
                else {
                    println jane.lastName + " saved"
                }

                // Races
                def trot = new Race(
                        name: "Turkey Trot",
                        startDate: (new Date() + 90),
                        city: "Duck",
                        state: "NC",
                        distance: 5.0,
                        cost: 20.0,
                        maxRunners: 350
                )
                trot.save()
                if (trot.hasErrors()) {
                    println trot.errors
                }

                // Registrations
                def reg = new Registration(
                        paid: false,
                        runner: jane,
                        race: trot
                )
                reg.save()
                if (reg.hasErrors()) {
                    println reg.errors
                }

                break

            case "production": break
        }

        println "BootStrap completed."


    }
    def destroy = {
    }
}
